import java.util.*;

public class Combat{
    //The initiative order, points to the node with the highest initiative
    private combatNode initiative;

    //Always points to the player node
    private combatNode player;

    //The number of entities in combat
    private int numEntities;

    //Names of all entities in combat for quick reference
    private List<String> names;

    //Takes inputs
    private Scanner inputs;

    //Counts the number of rounds that have passed in combat
    //Increases once every entity has taken a turn
    private int rounds;

    //True if combat is over
    private boolean combatOver;

    private boolean isBossFight;


    //The constructor, given a RPG_Player
    public Combat(RPG_Player player) {
        this.player = new combatNode(player);
        initiative = this.player;
        initiative.setNext(initiative);

        numEntities = 1;
        names = new ArrayList<>();
        names.add(player.getName());

        inputs = new Scanner(System.in);

        rounds = 0;
        combatOver = false;
        isBossFight = false;
    }

    /*Adds new enemies to the initiative. If there are multiple 
    entities with the same initiative, then sorts them by their
    dex modifier.
    Also adds their names to the list of names, changing them
    as appropriate.*/
    public void add(RPG_Enemy entity) {
        combatNode enemy = new combatNode(entity);
        numEntities++;
        
        //Checks the list of names for duplicates
        //If so, then changes the name to "Name" + 
        //the number of total enemies with that same name
        //Regardless, adds the new name to the list.
        if(names.contains(enemy.getName())) {
            int num = 1;
            String newName = enemy.getName();

            while(names.contains(newName)) {
                num++;
                newName = enemy.getName() + num;
            }

            enemy.setName(newName);
        }
        names.add(enemy.getName());


        combatNode curr = initiative;
        combatNode after = initiative.getNext();

        //Checks for the case where the new node's initiative is greater than 
        //the current greatest initiative
        if(enemy.getInit() > initiative.getInit()) {
            while(curr.getInit() > after.getInit()) {
                curr = curr.getNext();
                after = after.getNext();
            }

            curr.setNext(enemy);
            enemy.setNext(after);
            initiative = enemy;
        } else {
            //Loops through the order to find the right place to put it
            while((curr.getInit() > enemy.getInit()) && (enemy.getInit() > after.getInit())) {
                curr = curr.getNext();
                after = after.getNext();
            }

            //Checks for higher dex mod
            while((curr.getInit() == after.getInit()) && 
                (curr.getEntity().dexModifier() > enemy.getEntity().dexModifier())) {

                curr = curr.getNext();
                after = after.getNext();
            }

            curr.setNext(enemy);
            enemy.setNext(after);
        }

        if(enemy.getEnemy().getIfBoss()) {
            isBossFight = true;
        }
    }

    /*Given the name of an entity, removes the entity with
    that name from combat.

    Prints out a message if no matching name is found.
    */
    public void remove(String target) {
        if(!names.contains(target)) {
            System.out.println("No such entity with that name!");
        } else {
            combatNode curr = initiative;
            numEntities--;
            names.remove(target);

            while(!curr.getNext().getName().equals(target)) {
                curr = curr.getNext();
            }

            curr.setNext(curr.getNext().getNext());
        }
    }

    /*Given the name of an entity in the initiative, returns the
    RPG_Character representing that character.
    If there is no such entity in the initiative, throws an
    IllegalArgumentException.
    */
    public RPG_Character getEntity(String target) {
        if(!names.contains(target)) {
            throw new IllegalArgumentException("No such entity with that name!");
        } else {
            combatNode curr = initiative;

            while(!curr.getName().equals(target)) {
                curr = curr.getNext();
            }

            return curr.getEntity();
        }
    }

    /*Given the name of an enemy in the initiative, returns the
    RPG_Enemy representing that character.
    If there is no such entity in the initiative or if the target
    is the player, throws an IllegalArgumentException.
    */
    public RPG_Character getEnemy(String target) {
        if(!names.contains(target)) {
            throw new IllegalArgumentException("No such entity with that name!");
        } else {
            combatNode curr = initiative;

            while(!curr.getName().equals(target)) {
                curr = curr.getNext();
            }

            if(!curr.getIsPlayer()){
                return curr.getEnemy();
            } else {
                throw new IllegalArgumentException("Cannot use this method to access the player!");
            }
        }
    }

    //Attempts to end combat by running
    public void flee() {
        //Insert other conditions here
        //For now, temp condition of passing basic Dex Check

        int roll = player.getEntity().dexSave();

        //If pass
        if(roll >= 10) {
            combatOver = true;
            System.out.println("Successfully ran away!");
        } else {
            //If fail
            System.out.println("Couldn't get away!");
        }
    }

    //True if combat is over
    public boolean isCombatOver() {
        return combatOver;
    }

    //Checks battles conditions to see if combat should end or not
    //If so, then updates combatOver
    //Can add conditions as necessary
    public boolean checkWinCondition() {

        //If the player has been removed from combat
        if(!names.contains(player.getName())) {
            combatOver = true;
        }

        return combatOver;
    }

    public int getRounds() {
        return rounds;
    }

    //Assumes valid or null inputs
    public void processTurn(RPG_Character actor, RPG_Character target, 
        RPG_Action action) {
        if(target == null) {
            System.out.println("No valid target in input.");
        }

        if(action == null) {
            System.out.println("No valid action in input");
        }

        action.act(actor, target);
    }

    //Look into fuzzywuzzy
    //Given a string, matches entities and actions for valid inputs and 
    //passes it to another method
    public void processAttack(String str) {
        RPG_Character target = null;
        RPG_Action action = null;

        boolean hasTarget = false;

        //Checks if input contains an entity name
        for(String s : names) {
            if(str.toLowerCase().contains(s.toLowerCase()) && !hasTarget) {
                target = getEntity(s);
                hasTarget = true;
            }
        }

        //Checks for self-referential terms
        //Small redundancy if player name is the target
        String[] meMyselfAndI = {"myself", "me", player.getName()};
        for(int i = 0; i < meMyselfAndI.length; i++) {
            if(str.toLowerCase().contains(meMyselfAndI[i].toLowerCase())) {
                target = player.getPlayer();
                hasTarget = true;
            }
        }

        //If it is a one v. one battle, and no target is given,
        //then automatically assumes that the enemy is the target
        if(!hasTarget && (numEntities == 2)) {
            combatNode curr = player.getNext();

            target = curr.getEntity();
            hasTarget = true;
        }

        //If there is more than one enemy, and no enemy was detected
        //Selects a random enemy as a target
        if(!hasTarget && (numEntities > 2)) {
            Random rand = new Random();
            
            while(!hasTarget) {
                int randTarget = rand.nextInt(numEntities);

                //If the random target chose is the player, pick a new target, 
                //Otherwise select the new target
                if(!player.getName().equals(names.get(randTarget))) {
                    target = getEnemy(names.get(randTarget));
                    hasTarget = true;
                }
            }
        }

        //Checks for valid player actions
        ArrayList<RPG_Action> actions = player.getPlayer().getActions();
        for(RPG_Action act : actions) {
            if(str.toLowerCase().contains(act.getName().toLowerCase())) {
                action = act;
            }
        }

        processTurn(player.getPlayer(), target, action);
    }

    public void processInteraction() {

    }

    /*While combat is ongoing, loops through all turns in the
    initiative order
    */
    public void startCombat() throws InterruptedException {
        combatNode curr = initiative;
        combatNode after = initiative.getNext();

        while(!combatOver) {
            //If current node is the top of the initiative, then iterates round count
            if(curr == initiative) {
                rounds++;
                System.out.println("Round: " + rounds);
            }

            //Checks if current entity meets conditions
            //If so, removes them from combat, then checks the
            //next entity
            //Conditions: 
            //1. At 0 hp or less
            while(curr.getEntity().getCurrentHP() <= 0) {
                if(curr.getEntity().getCurrentHP() <= 0) {
                    System.out.println(curr.getName() + " has died!");
                }

                remove(curr.getName());

                if(curr == initiative) {
                    initiative = after;
                }
                curr = after;
                after = after.getNext();
            }

            if(curr.getIsPlayer()) {
                boolean validAction = false;
                String currInput = "";

                System.out.println("It's your turn!");

                while(!validAction) {
                    System.out.println("What would you like to do?");
                    System.out.println("You may: '\n' Attack '\n' Flee '\n'" + 
                        "Check Inventory '\n' Check Status '\n' Interact '\n'");

                    currInput = inputs.next();

                    //Attacks
                    if(currInput.toLowerCase().contains("attack")) {
                        System.out.println("Your current attacks are:");
                        player.getPlayer().printActions();
                        System.out.println("Type \"Help\" to get a description for any attack");

                        while(!validAction) {
                            currInput = inputs.next();
                            RPG_Action action = null;

                            for(RPG_Action act : player.getPlayer().getActions()) {
                                if(currInput.toLowerCase().contains(act.getName().toLowerCase())) {
                                    action = act;
                                }
                            }

                            if(action != null) {
                                if(currInput.toLowerCase().contains("help")) {
                                    //If help is requested, print out the action's description
                                    System.out.println(action.getDesc());

                                } else {
                                    //Perform the attack
                                    processAttack(currInput);
                                    validAction = true;
                                }

                            } else {
                                //No valid attack is detected in the input
                                System.out.println("Not a valid input!");
                            }
                        }

                    } else if(currInput.toLowerCase().contains("flee")) {
                        //Flee

                        //Conditions that make running impossible
                        /*Right Now, cannot run...:
                        1. when it is a boss fight
                        */

                        System.out.println("Attempting to run away...");

                        if(isBossFight) {
                            System.out.println("Fleeing from this powerful creature is impossible!");

                        } else {
                            flee();
                            validAction = true;
                        }

                    } else if(currInput.toLowerCase().contains("inventory")) {
                        //Checking Inventory
                        System.out.println("Your inventory contains:");
                        player.getPlayer().printInventory();

                    } else if(currInput.toLowerCase().contains("status")) {
                        //Checking Status
                        System.out.println(player.getName());
                        System.out.println("Class: " + player.getPlayer().getClass());
                        System.out.println("HP: " + player.getPlayer().getCurrentHP() + "/"
                            + player.getPlayer().getMaxHP());
                        System.out.println("AC: " + player.getPlayer().getAC());
                        System.out.println("Stats: " + player.getPlayer().getStats());
                        System.out.println("XP: " + player.getPlayer().getTotalXP());

                    } else if(currInput.toLowerCase().contains("interact")) {
                        //Interacting with enemies
                        //Implement later?

                        processInteraction();
                        validAction = true;
                    } else {
                        System.out.println("Not a valid input!");
                    }
                }
            } else {
                //Enemy AI
            }   
        }
    }
}

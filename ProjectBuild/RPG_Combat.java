/**
@author Justin Seo
@file RPG_Combat.java
Integration edits by lucinder
**/
import java.util.*;
public class RPG_Combat{
   public static final int TEXTDELAY = 10;
   public static final int LINEDELAY = 0;
   private class CombatNode{
      private RPG_Character chara; // stored entity
      private int init; // initiative count
      private CombatNode next;
      public CombatNode(RPG_Character entity){ // constructor using only an entity
         this.chara = entity;
         init = entity.initiative();
      }
      public CombatNode(RPG_Character entity, CombatNode next){ // constructor using only an entity
         this.chara = entity;
         init = entity.initiative();
         this.next = next;
      }
      public CombatNode(CombatNode other){ // copy constructor
         this.chara = other.getEntity();
         this.init = other.getInit();
         this.next = other.getNext();
      }
      // getters
      public RPG_Character getEntity(){ return chara; }
      public int getInit(){ return init; }
      public CombatNode getNext(){ return next; }
      // setters
      public void setEntity(RPG_Character newEntity){ chara = newEntity; }
      public void setInit(int newInit){ init = newInit; }
      public void setNext(CombatNode newNext){ next = newNext; }
      public int compareTo(CombatNode other){
         if(other.getInit() > this.init){ return -1; } // higher initiative wins comparison
         if(this.init > other.getInit()){ return 1; }
         return 0;
      }
      public boolean equals(CombatNode other){ return compareTo(other) == 0; }
      public boolean greaterThan(CombatNode other){ return compareTo(other) > 0; }
      public boolean lessThan(CombatNode other){ return compareTo(other) < 0; }
   }
   
    //The initiative order, points to the node with the highest initiative
    private CombatNode initiative;
    //Always points to the player
    private RPG_Player player;
    //The number of entities in combat
    private int numEntities = 0;
    // Did the player flee the battle?
    private boolean fled = false;
    //Names of all entities in combat for quick reference
    private ArrayList<String> names = new ArrayList<String>();
   // keeps track of how much xp is earned throughout the battle
   int xpSum = 0;
   // universal input scanner
   Scanner input = new Scanner(System.in);

    //Counts the number of rounds that have passed in combat
    //Increases once every entity has taken a turn
    private int rounds;

    //True if combat is over + the player is not dead
    private boolean victory = false;
    // Are we fighting a boss?
    private boolean bossFight = false;

    //The constructor, given a RPG_Player
    public RPG_Combat(RPG_Player player) {
      this.player = player;
      this.initiative = new CombatNode(player);
      numEntities = 1;
      //names = new ArrayList<>();
      //names.add(player.getName());
      Scanner input = new Scanner(System.in);
      rounds = 0;
    }

    /*Adds new enemies to the initiative. If there are multiple 
    entities with the same initiative, then sorts them by their
    dex modifier.
    Also adds their names to the list of names, changing them
    as appropriate.*/
    public void add(RPG_Character entity) {
        CombatNode enemy = new CombatNode(entity);
        numEntities++;
        // Combat will go in DECENDING order based on init
        // Ex: 20, 16, 10, 9, 5
        
        //Checks the list of names for duplicates
        //If so, then changes the name to "Name" + 
        //the number of total enemies with that same name
        //Regardless, adds the new name to the list.
        if(names.contains(enemy.getEntity().getName())) {
            int num = 1;
            String newName = enemy.getEntity().getName();
            while(names.contains(newName)) {
                num++;
                newName = enemy.getEntity().getName() + num;
            }
            enemy.getEntity().setName(newName);
        }
        names.add(enemy.getEntity().getName());

        CombatNode cur = initiative;
        if(enemy.greaterThan(cur) || (enemy.equals(cur) && enemy.getEntity().dexModifier() > cur.getEntity().dexModifier())){ // enemy fits in head slot?
         enemy.setNext(cur);
         initiative = new CombatNode(enemy); // set head to enemy
        } else {
           CombatNode prev = initiative;
           cur = cur.getNext();
           //Continuously checks for the case where the new node's initiative is greater than 
           //the current greatest initiative
           while(cur != null && enemy.lessThan(cur)) { // break loop if we find the right insertion place or reach the end
            cur = cur.getNext();
            prev = prev.getNext();
           }
           // Insert enemy between prev and cur
           if(cur != null && enemy.equals(cur) && enemy.getEntity().dexModifier() < cur.getEntity().dexModifier()){ // on a tie, shift the enemy after the current entity if it loses the dex match
            cur = cur.getNext();
            prev = prev.getNext();
           }
           enemy.setNext(cur);
           prev.setNext(enemy);
        }
        
        // set bossfight to true if we're fighting a boss!
        if(enemy.getEntity() instanceof RPG_Enemy && ((RPG_Enemy)(enemy.getEntity())).getIfBoss()) {
            bossFight = true;
        }
    }

    /*Given the name of an entity, removes the entity with
    that name from combat.
    Prints out a message if no matching name is found.
    */
    public void remove(String target) throws InterruptedException{
        if(!names.contains(target)) {
            printStaggered("No such entity with that name!");
        } else {
            CombatNode cur = initiative;
            CombatNode prev = initiative;
            numEntities--;
            names.remove(target);
            if(target.equals(initiative.getEntity().getName())){ // remove from head of initiative
               initiative = cur.getNext();
            }
            cur = cur.getNext();
            while(cur != null && !cur.getEntity().getName().equals(target)) {
                cur = cur.getNext();
                prev = prev.getNext();
            }
            if(cur != null){
               prev.setNext(cur.getNext());
            } else {
               prev.setNext(cur);
            }
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
            CombatNode cur = initiative;
            while(!cur.getEntity().getName().equals(target)) {
                cur = cur.getNext();
            }
            return cur.getEntity();
        }
    }
    
    //Attempts to end combat by running
    public boolean flee() {
        if(bossFight){ return false; } // cannot flee a boss battle
        return player.dexSave(10); // DC 10 dex save to flee
    }

    //True if combat is over
    public boolean isCombatOver() {
        return victory || fled;
    }

    //Checks battles conditions to see if combat should end or not
    //If so, then updates combatOver
    //Can add conditions as necessary
    public boolean checkWinCondition() {
        //If the player has been removed from combat
        if(!names.contains(player.getName())) {
            victory = true;
        }
        if(numEntities == 0){ // player is the last one standing
            victory = true;
        }
        return victory;
    }

    public int getRounds() {
        return rounds;
    }

    //Look into fuzzywuzzy
    // Enemy attack method
    public void attack(RPG_Enemy actor) throws Exception,InterruptedException{
        if(!actor.isPacified() && actor.getHPPercent() <= 30.0){ // is the enemy at 30% or less HP?
            printStaggered("The " + actor.getName() + " is trembling with fear.");
            actor.canSpare = true; // enemies at low HP can be spared
        }
        RPG_Character target = player;
        actor.doAction(player); // easy, just let the enemy class handle choosing its own actions!
    }
    // Player attack method (simple)
    public void attack(RPG_Player actor, RPG_Character target, RPG_Attack action) throws Exception{ // Assumes null or valid inputs
      if(target == null) { // changed the printlns to exceptions so that the try loop in target selection can catch them
         throw new Exception("No valid target in input.");
        }
        if(action == null) {
         throw new Exception("No valid action in input");
        }
      action.act(actor, target);
      if(action.getName().equals("Dragon Breath")){ actor.useBreath(); } // expend breath weapons
    }
    // Player attack method (target selection)
    public void attack(RPG_Player actor, RPG_Attack action) throws Exception{
      if(numEntities > 2){ // multiple viable targets (horde combat)
         RPG_Character target;
         boolean targetSelected = false;
         while(!targetSelected){
            printStaggered("Select a target!");
            int i = 0;
            CombatNode cur = initiative;
            RPG_Character[] targets = new RPG_Character[numEntities-1]; // array to hold all viable targets
            while(!(cur == null)){
               if(!(cur.getEntity() instanceof RPG_Player)){ // find and list all targets
                  targets[i] = cur.getEntity();
                  printStaggered(" ["+i+"] " + targets[i].getName());
                  i++;
               }
               cur = cur.getNext();
            }
            printStaggered(" [" + i + "] BACK");
            String select = input.nextLine();
            if(select.toUpperCase().equals("Q")){ System.exit(0); } // Quit game
            if(select.equals(""+i)){ attack(actor); break; } // Go back to action selection
            try {
               attack(player, targets[Integer.parseInt(select)], action); // attempt target selection using input
               targetSelected = true;
            } catch (Exception e){
               printStaggered("Invalid selection!"); // bad input or target not found
               targetSelected = false;
            }
         }
        } else {
            CombatNode cur = initiative;
            if(cur.getEntity() instanceof RPG_Player && cur.getNext() != null){
               attack(player, cur.getNext().getEntity(), action);
            } else {
               attack(player, cur.getEntity(), action);
            }
        }
   }
   public void spare(RPG_Player actor, RPG_Enemy target){
      target.pacify();
      return; // maybe?
   }
   // Player spare method (target selection)
    public void spare(RPG_Player actor) throws Exception{
      if(numEntities < 2){ // multiple viable targets (horde combat)
         throw new Exception("No enemies can be spared yet!");
      } else {
         RPG_Character target;
         boolean targetSelected = false;
         Scanner input = new Scanner(System.in);
         while(!targetSelected){
            printStaggered("Select a target!");
            int i = 0;
            CombatNode cur = initiative;
            ArrayList<RPG_Enemy> targets = new ArrayList<RPG_Enemy>(); // arraylist to hold all viable targets
            while(!(cur == null)){
               if(cur.getEntity() instanceof RPG_Enemy && ((RPG_Enemy)cur.getEntity()).canSpare){ // find and list all targets
                  targets.add((RPG_Enemy)cur.getEntity());
                  printStaggered(" ["+i+"] " + targets.get(i).getName());
                  i++;
               }
               cur = cur.getNext();
            }
            printStaggered(" [" + i + "] BACK");
            String select = input.nextLine();
            
            if(select.toUpperCase().equals("Q")){ System.exit(0); } // Quit game
            if(select.equals(""+i)){ attack(actor); break; } // Go back to action selection
            try {
               spare(player, targets.get(Integer.parseInt(select))); // attempt target selection using input
               targetSelected = true;
            } catch (Exception e){
               targetSelected = false;
               printStaggered("Invalid selection!"); // bad input or target not found
            }
         }
     } 
   }

   public void attack(RPG_Player actor) throws Exception,InterruptedException{
      boolean validAction = false;
      int selection = -1;
      printStaggered("It's your turn!");
      printStaggered("[" + player.getName()+ ": HP " + player.getHP() + "/" + player.getMaxHP() + "]");
      while(!validAction) {
        printStaggered("What will you do?");
        printStaggered(" [0] ATTACK\n [1] ITEM\n [2] FLEE");
        boolean spareOn3 = true;
        if(actor.getPlayerClass() instanceof RPG_Spellcaster){
         printStaggered(" [3] MAGIC");
         spareOn3 = false;
        }
        if(!spareOn3){
         printStaggered(" [4] SPARE");
        } else {
         printStaggered(" [3] SPARE");
        }
        String[] select = input.nextLine().split(" ");
        // Attack
        if(contains(select,"attack") || contains(select, "0")) {
         selection = 0;
         validAction = true;
        } else if(contains(select,"item") || contains(select,"1")){
         selection = 1;
         validAction = true;
        } else if(contains(select,"flee") || contains(select,"2")){
         selection = 2;
         validAction = true;
        } else if(contains(select,"magic")) {
         if(!spareOn3){
            selection = 3;
            validAction = true;
         }
        } else if (contains(select,"3")){ // magic OR spare
            if(!spareOn3){
               selection = 3;
               validAction = true;
            } else {
               selection = 4;
               validAction = true;
            }
        } else if (contains(select,"spare") ||contains(select,"4")){
         selection = 4;
         validAction = true;
        }
      }
      if(selection == 0){ // attack
         ArrayList<RPG_Action> actable = new ArrayList<RPG_Action>(); // handles all the actions we CAN execute right now
         int i = 0;
         printStaggered("Which weapon will you use?");
         for(RPG_Action a : actor.getActions()){
            if(!(a.getName().equals("Dragon Breath") && actor.isBreathUsed())){ // skip expended breath attacks
               printStaggered(" [" + i+"] " + a.getName());
               actable.add(a);
               i++;
            }
         }
         printStaggered(" [" + i + "] BACK");
         boolean attackSelected = false;
         RPG_Attack atk = new RPG_Attack();
         while(!attackSelected){
            try{
               attackSelected = true;
               String inp = input.nextLine();
               if(inp.equals("Q")){ System.exit(0); } // QUIT option
               if(inp.equals(""+i)){ attack(actor); attackSelected = false; break; } // Go back to action selection
               atk = (RPG_Attack)(actable.get(Integer.parseInt(inp)));
            } catch(Exception e){ // should catch all non-int input except quit
               printStaggered("Invalid selection!");
               attackSelected = false; // deselect
               continue; // keep looping until a valid selection is made 
            }
         }
         if(attackSelected){ // early breaks from going back skip this action
            if(atk.getType().equals("Healing")){ // healing heals self
               attack(actor, actor, atk);
            } else {
               attack(actor, atk); // pass our selection on to target selection
            }
         }
      } else if (selection == 1){
         ArrayList<RPG_Action> actable = new ArrayList<RPG_Action>(); // handles all the actions we CAN execute right now
         int i = 0;
         printStaggered("Which item?");
         for(RPG_Action a : actor.getItemActions()){
            printStaggered(" [" + i+"] " + a.getName());
            actable.add(a);
            i++;
         }
         printStaggered(" [" + i + "] BACK");
         boolean itemSelected = false;
         RPG_Attack itemAction = new RPG_Attack();
         while(!itemSelected){
            try{
               itemSelected = true;
               String inp = input.nextLine();
               if(inp.equals("Q")){ System.exit(0); } // QUIT option
               if(inp.equals(""+i)){ attack(actor); itemSelected = false; break; } // Go back to action selection
               itemAction = (RPG_Attack)(actable.get(Integer.parseInt(inp)));
            } catch(Exception e){ // should catch all non-int input except quit
               printStaggered("Invalid selection!");
               itemSelected = false; // deselect
               continue; // keep looping until a valid selection is made 
            }
         }
         if(itemSelected){ // early breaks from going back skip this action
            if(itemAction.getType().equals("Healing")){ // healing heals self
               attack(actor, actor, itemAction);
            } else {
               attack(actor, itemAction); // pass our selection on directly to action usage; target is automatically self
            }
         }
      } else if(selection == 2) { //Flee
         //Conditions that make running impossible
         /*Right Now, cannot run...:
         1. when it is a boss fight
         */
         printStaggered("You tried to run...");
         boolean success = flee();
         if(!success){ // flee fails
            if(bossFight){
               printStaggered("Fleeing from this powerful creature is impossible!");
            } else {
               printStaggered("Couldn\'t get away!");
            }
         } else {
            printStaggered("Got away safely!");
            fled = true;
         }
      } else if(selection == 3) { // Spellcasting
         RPG_Spellcaster caster = (RPG_Spellcaster)player.getPlayerClass();
         ArrayList<RPG_Spell> actable = new ArrayList<RPG_Spell>(); // handles all the actions we CAN execute right now
         int i = 0;
         printStaggered("Which spell will you use?");
         for(RPG_Spell a : caster.getSpellList()){
            if(a.canAfford(player.getMP())){ // add only spells that we can afford
               printStaggered(" [" + i+"] " + a.getSpell().getName() + " --- " + a.getCost());
               actable.add(a);
               i++;
            }
         }
         printStaggered(" [" + i + "] BACK");
         boolean attackSelected = false;
         RPG_Attack atk = new RPG_Attack();
         while(!attackSelected){
            try{
               attackSelected = true;
               String inp = input.nextLine();
               if(inp.equals("Q")){ System.exit(0); } // QUIT option
               if(inp.equals(""+i)){ attack(actor); attackSelected = false; break; } // Go back to action selection
               RPG_Spell selectedSpell = actable.get(Integer.parseInt(inp));
               atk = (RPG_Attack)(selectedSpell.getSpell());
               atk.setToHit(player.getProficiencyBonus()); // set spell prof bonus to player prof
               atk.setModifier(RPG_Dice.getModifier(player.getStat(caster.getCastingAbility()))); // set spell atk mod
               player.useMP(selectedSpell.getCost());
            } catch(Exception e){ // should catch all non-int input except quit
               printStaggered("Invalid selection!");
               attackSelected = false; // deselect
               continue; // keep looping until a valid selection is made 
            }
         }
         if(attackSelected){ // early breaks from going back skip this action
            if(atk.getType().equals("Healing")){ // healing heals self
               attack(actor, actor, atk);
            } else {
               attack(actor, atk); // pass our selection on to target selection
            }
         }
      } else if(selection == 4) {
         try{
            spare(actor); // send to spare selector
         } catch (Exception e){
            printStaggered(e.getMessage());
         }
      }
    }


    /*While combat is ongoing, loops through all turns in the
    initiative order
    */
    public void start() throws Exception,InterruptedException{
        int[] preCombatStats = player.getStats().clone(); // pass player stats by value
        CombatNode cur = initiative;
        // CombatNode after = initiative.getNext();
        while(!victory && !fled) {
         if(cur.getEntity() instanceof RPG_Player){
            attack((RPG_Player)cur.getEntity()); // let either the player or the enemy act
         } else {
            attack((RPG_Enemy)cur.getEntity());
         }
         //Checks if current entity meets removal conditions
         //If so, removes them from combat, then checks the
         //next entity
         for(CombatNode c = initiative; c != null; c = c.getNext()){
            // Conditions: 
            if(c.getEntity().getHP() <= 0){ // 1. At 0 hp or less
               if(c.getEntity() instanceof RPG_Player){ // player death
                  player.die();
               } else {
                  ((RPG_Enemy)c.getEntity()).die();
                  xpSum += ((RPG_Enemy)c.getEntity()).getXP();
                  remove(c.getEntity().getName());
                  player.sin(); // murder: player is no longer a pacifist
               }
            } else if (c.getEntity() instanceof RPG_Enemy && ((RPG_Enemy)c.getEntity()).isPacified()){ // 2. Enemy has been pacified. 
               xpSum += ((RPG_Enemy)c.getEntity()).getXP();
               remove(c.getEntity().getName());
            }
         }
         if(numEntities == 1){ // player is the last one standing
            victory = true;
         }
         cur = cur.getNext();
         if(cur == null){ cur = initiative; } // reset if we tick over
        }
        if(victory){
            printStaggered("YOU WON!");
            player.setStats(preCombatStats); // revert any temporary stat changes
            player.rechargeRacialAbilities(); // recharge any expended racial abilities after battle
            printStaggered(player.getName() + " gained " + xpSum + " XP.");
            player.gainXP(xpSum);
        }
    }
    
    // HELPER METHODS
    private boolean contains(String[] array, String key){ // helper to check if a string array contains a given string- not case sensitive
      for(String s : array){
         if(s.toLowerCase().equals(key.toLowerCase())){ return true; }
      }
      return false;
   }
   private boolean contains(RPG_Interactable[] array, String key){
      for(RPG_Interactable s : array){
         if(s.getName().toLowerCase().equals(key.toLowerCase())){ return true; }
      }
      return false;
   }
   public static void printStaggered(String input) throws InterruptedException{
      printStaggered(input, TEXTDELAY);
   }
   public static void printStaggered(String input, int delayMS) throws InterruptedException{ // ONLY works for single-line commands
      String[] toPrint = input.split("");
      for(String s : toPrint){
         System.out.print(s);
         Thread.sleep(delayMS);
      }
      System.out.println();
      Thread.sleep(LINEDELAY);
   }
}
public class combatNode {
    //The entity
    private RPG_Player player;

    private RPG_Enemy enemy;

    //True if the player
    private boolean isPlayer;

    //Pointer to the next node
    private combatNode next;

    //Initiative
    private int init;

    //The name of the entity, can be different than its official name
    //Used to indicate duplicates in combat
    //E.g. If there's already an enemy with the same name, this one's
    //name may be changed to "enemyName" + "2"
    private String name;

    //Constructors

    //Creates a node with the given player
    public combatNode(RPG_Player player) {
        this.player = player;
        enemy = null;
        isPlayer = true;
        next = null;
        init = player.dexModifier() + RPG_Dice.XdY(1, 20);
        name = player.getName();
    }

    //Creates a node with the given player and next node
    public combatNode(RPG_Player player, combatNode next) {
        this(player);
        this.next = next;
    }

    //Creates a node with the given enemy
    public combatNode(RPG_Enemy enemy) {
        this.enemy = enemy;
        player = null;
        isPlayer = false;
        next = null;
        init = enemy.dexModifier() + RPG_Dice.XdY(1, 20);
        name = enemy.getName();
    }

    //Creates a node with the given enemy and next node
    public combatNode(RPG_Enemy enemy, combatNode next) {
        this(enemy);
        this.next = next;
    }


    //Getters

    //Returns an RPG_Character, regardless of being either
    //an Enemy or a Player
    public RPG_Character getEntity() {
        if(isPlayer) {
            return player;
        } else {
            return enemy;
        }
    }

    //Returns the stored player, if it is a player.
    //Throws an IllegalArgumentException otherwise
    public RPG_Player getPlayer() {
        if(!isPlayer) {
            throw new IllegalArgumentException();
        }  

        return player;
    }

    //Returns the stored enemy, if it is a enemy.
    //Throws an IllegalArgumentException otherwise
    public RPG_Enemy getEnemy() {
        if(isPlayer) {
            throw new IllegalArgumentException();
        }  

        return enemy;
    }

    //True if it is a player
    public boolean getIsPlayer() {
        return isPlayer;
    }

    //Returns the node that this one points to
    public combatNode getNext() {
        return next;
    }

    //Returns the name of the entity
    public String getName() {
        return name;
    }

    //Returns the initiative of the entity
    public int getInit() {
        return init;
    }


    //Setters

    //Sets the next for this node
    public void setNext(combatNode next) {
        this.next = next;
    }

    //Sets the name for this node
    public void setName(String newName) {
        name = newName;
    }
}

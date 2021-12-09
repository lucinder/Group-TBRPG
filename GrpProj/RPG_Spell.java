public class RPG_Spell extends RPG_Attack{
    //Mana cost of the spell
    private int manaCost = 1;

    //True if it forces a save, false if dmg
    //False by default
    private boolean isSave = false;

    //0-5, for Str, Dex, Con, Int, Wis, and Cha saves respectively
    //Strength Save as default
    private int saveType = 0;

    //Save DC
    private int DC = 8;

    //True if dmg is halved on success
    private boolean halfOnSuccess = false;

    //Constructors
    //Basic
    public RPG_Spell(String name, String type) {
        super(name, type);
    }

    
    public RPG_Spell(String name, String type, int[] dice, int manaCost) {
        super(name, type, dice);
        this.manaCost = manaCost;
    }

    //For Spell Atks
    public RPG_Spell(String name, String type, int[] dice, int mod, int prof, 
        int manaCost) {

        super(name, type, dice, mod, prof);
        this.manaCost = manaCost;
    }

    //For Spell Saves
    public RPG_Spell(String name, String type, int[] dice, int manaCost, 
        boolean isSave, int saveType, int DC, boolean halfOnSuccess) {

        super(name, type, dice);
        this.manaCost = manaCost;
        this.isSave = isSave;
        this.DC = DC;
        this.halfOnSuccess = halfOnSuccess;
    }


    //Getters
    public int getManaCost() {
        return manaCost;
    }

    public String getName() {
        return super.getName();
    }

    public String getDesc() {
        return super.getDesc();
    }

    public String getType() {
        return super.getType();
    }

    //Other
    public boolean canAfford(int currentMana) {
        return currentMana >= manaCost;
    }

    //Copied from RPG_SaveAttack
    boolean makesSave(RPG_Character target){
        if(saveType == 0){
           return target.strSave(DC);
        } else if (saveType == 1){
           return target.dexSave(DC);
        } else if (saveType == 2){
           return target.conSave(DC);
        } else if (saveType == 3){
           return target.intSave(DC);
        } else if (saveType == 4){
           return target.wisSave(DC);
        }
        return target.chaSave(DC);
    }

    public void act(RPG_Character user, RPG_Character target){
        if(isSave) {
            int damage = rollDamage();

            if(target.getResistances().contains(this.getType())){ // is the damage resisted?
                damage /= 2;
            } else if(target.getWeaknesses().contains(this.getType())){ // is the target weak to the damage?
                damage *= 2;
            }

            //Full damage
            if(!makesSave(target)){
                say(user.getName(), target.getName(), damage, true, false, false); // save attacks cannot crit
                target.takeDamage(damage);

            } else {
                //Still does half damage on successful save
                if(halfOnSuccess) {
                    damage /= 2;

                    say(user.getName(), target.getName(), damage, true, false, false); // save attacks cannot crit
                    target.takeDamage(damage);
                } else {
                    //A save makes the spell do nothing
                    say(user.getName(), target.getName(), 0, false, false, false);
                }
            }  
        } else {
            super.act(user, target);
        }
    }

    public void act(String actor, RPG_Character target, boolean isTrap) {
        if(isSave) {
            int damage = rollDamage();

            if(target.getResistances().contains(this.getType())){ // is the damage resisted?
                damage /= 2;
            } else if(target.getWeaknesses().contains(this.getType())){ // is the target weak to the damage?
                damage *= 2;
            }

            if(!makesSave(target)){
                say(actor, target.getName(), damage, true, false, isTrap); // save attacks cannot crit
                target.takeDamage(damage);
            } else {
                if(halfOnSuccess) {
                    damage /= 2;
                    
                    say(actor, target.getName(), damage, true, false, isTrap); // save attacks cannot crit
                    target.takeDamage(damage);
                } else {
                    say(actor, target.getName(), 0, false, false, isTrap);
                }
            }
        } else {
            super.act(actor, target, isTrap);
        }
    }

}

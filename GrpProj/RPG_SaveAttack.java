public class RPG_SaveAttack extends RPG_Attack{
   private int DC = 10; // default dc is 10
   private int saveStat = 0; // default save type is str
   private boolean halfOnFail = false; // is damaged halved on failure (as opposed to no damage)? default false
   public RPG_SaveAttack(String name, String type, int[] dice){
      super(name, type, dice);
   }
   public RPG_SaveAttack(String name, String type, int[] dice, int DC){
      super(name, type, dice);
      this.DC = DC;
   }
   public RPG_SaveAttack(String name, String type, int[] dice, int DC, int saveStat){
      super(name, type, dice);
      this.DC = DC;
      this.saveStat = saveStat;
   }
   boolean makesSave(RPG_Character target){
      if(saveStat == 0){
         return target.strSave(DC);
      } else if (saveStat == 1){
         return target.dexSave(DC);
      } else if (saveStat == 2){
         return target.conSave(DC);
      } else if (saveStat == 3){
         return target.intSave(DC);
      } else if (saveStat == 4){
         return target.wisSave(DC);
      }
      return target.chaSave(DC);
   }
   public void act(RPG_Character user, RPG_Character target){
      if(!makesSave(target)){
         int damage = rollDamage();
         if(target.getResistances().contains(this.getType())){ // is the damage resisted?
            damage /= 2;
         } else if(target.getWeaknesses().contains(this.getType())){ // is the target weak to the damage?
            damage *= 2;
         }
         say(user.getName(), target.getName(), damage, true, false, false); // save attacks cannot crit
         target.takeDamage(damage);
      } else {
         say(user.getName(), target.getName(), 0, false, false, false);
      }
   }
   public void act(String actor, RPG_Character target, boolean isTrap){ // overload for traps and non-character entities
      if(!makesSave(target)){
         int damage = rollDamage();
         if(target.getResistances().contains(this.getType())){ // is the damage resisted?
            damage /= 2;
         } else if(target.getWeaknesses().contains(this.getType())){ // is the target weak to the damage?
            damage *= 2;
         }
         say(actor, target.getName(), damage, true, false, isTrap); // save attacks cannot crit
         target.takeDamage(damage);
      } else {
         say(actor, target.getName(), 0, false, false, isTrap);
      }
   }
}
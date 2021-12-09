/**
@author Lucinder
@file RPG_Attack.java
A basic attack action with a full attack roll and dialogue included.
**/
import java.util.Arrays;
public class RPG_Attack extends RPG_Action{
   private String type = "Damage";
   // Action Types:
   // Damage- standard (typeless) damage, used for all nonmagical weapons
   // Healing- healing (negative damage)
   // Fire Damage- fire elemental damage, used for fire spells / magical weapons
   // Ice Damage- ice elemental damage, used for ice spells / magical weapons
   // Lightning Damage- lightning elemental damage, used for lightning spells / magical weapons
   // Poison Damage- poison elemental damage, used for poison spells / poisoned weapons
   // Buff- raise a given stat by a certain value
   // Debuff- lower a given stat by a certain value
   private int[] dice = {1,1}; // Damage or Healing Dice stored in XdY format. Default = 1d1 = 1
   private int modifier = 0; // Damage modifier
   private int toHit = 2; // To-hit modifier (on top of the existing damage modifier). Default 2
   public RPG_Attack(){super("Unarmed Strike"," swung its fist.");}
   public RPG_Attack(RPG_Attack other){ super(other); this.type = other.getType(); this.modifier = other.getModifier(); this.dice = other.getDice(); } 
   public RPG_Attack(String name, String type){
      super(name, " used " + name);
      this.type = type;
   }
   public RPG_Attack(int[] dice){ // Default attack with different damage
      super("Unarmed Strike"," swung its fist.");
      this.dice = dice;
   }
   public RPG_Attack(String name, String type, int[] dice){
      super(name, " used " + name);
      this.type = type;
      this.dice = dice;
   }
   public RPG_Attack(String name, String type, int[] dice, int modifier){
      super(name, " used " + name);
      this.type = type;
      this.dice = dice;
      this.modifier = modifier;
   }
   public RPG_Attack(String name, String type, int[] dice, int modifier, int proficiency){
      super(name, " used " + name + ".");
      this.type = type;
      this.dice = dice;
      this.modifier = modifier;
      this.toHit = proficiency;
   }
   public RPG_Attack(RPG_Weapon weapon, int modifier, int proficiency){
      super(weapon.getName() + " Attack"," swung their " + weapon.getName() + ".");
      if(weapon instanceof RPG_Potion){
         setName(weapon.getName()); // remove "Attack"
      }
      // System.out.println("TEST- weapon name = " + weapon.getName());
      if(weapon.isRanged()){
         setDesc(" shot with their " + weapon.getName() + ".");
      }
      this.type = weapon.getDamageType();
      this.dice = weapon.getDamageDice();
      this.modifier = modifier + weapon.getMagicBonus();
      this.toHit = proficiency;
   }
   public String getType(){ return type; }
   public int getModifier(){ return modifier; }
   public int[] getDice(){ return dice; }
   public int getToHit(){ return toHit; }
   public void setToHit(int newProf){ toHit = newProf; }
   public void setModifier(int mod){ this.modifier = mod;}
   public int rollToHit(){
      int roll = RPG_Dice.XdY(1,20) + modifier + toHit;
      // System.out.print("Roll to hit: " + roll + " "); // testing purposes only
      return roll;
   }
   public boolean isCrit(int roll){ return (roll - (modifier + toHit)) == 20; }
   public int rollDamage(){
      int damage = RPG_Dice.XdY(dice[0], dice[1]) + modifier;
      if(modifier < 1){ damage = RPG_Dice.XdY(dice[0], dice[1]); } // discard negative modifiers
      if(damage < 0){ damage = 0; } // discard negative damage
      return damage;
   }
   
   /**
   public int fullRoll(int AC){
      int hitRoll = rollToHit();
      if(hitRoll > AC){
         int damage = rollDamage();
         if(isCrit(hitRoll)){ 
            System.out.print("Critical hit! ");
            damage *= 2; 
         } // double damage on a crit
         System.out.println(damage + " " + type + "!"); // X _ damage!
         return damage;
      } else {
         System.out.println("But it missed!");
      }
      return 0;
   } **/
   public boolean equals(Object otherItem){
      if(otherItem instanceof RPG_Attack){
         RPG_Attack other = (RPG_Attack)otherItem;
         return super.getName().equals(other.getName()) && type.equals(other.getType()) && Arrays.equals(dice, other.getDice());
      }
      return false;
   }
   public void act(RPG_Character user, RPG_Character target){
      if(type.equals("Healing")){
         int healing = rollDamage();
         say(user.getName(), target.getName(), healing, true, false, false);
         target.heal(healing);
         return; // cut off method early
      }
      int hitRoll = rollToHit();
      if(hitRoll > target.getAC()){
         int damage = rollDamage();
         if(target.getResistances().contains(this.type)){ // is the damage resisted?
            damage /= 2;
         } else if(target.getWeaknesses().contains(this.type)){ // is the target weak to the damage?
            damage *= 2;
         }
         if(isCrit(hitRoll)){ // double damage on a crit
            damage *= 2; 
            say(user.getName(), target.getName(), damage, true, true, false);
         } else { 
            say(user.getName(), target.getName(), damage, true, false, false);
         }
         target.takeDamage(damage);
      } else {
         say(user.getName(), target.getName(), 0, false, false, false);
      }
   }
   
   public void act(String actor, RPG_Character target, boolean isTrap){ // overload for traps or non-character entities
      int hitRoll = rollToHit();
      if(hitRoll > target.getAC()){
         int damage = rollDamage();
         if(target.getResistances().contains(this.type)){ // is the damage resisted?
            damage /= 2;
         } else if(target.getWeaknesses().contains(this.type)){ // is the target weak to the damage?
            damage *= 2;
         }
         if(isCrit(hitRoll)){ // double damage on a crit
            damage *= 2; 
            say(actor, target.getName(), damage, true, true, isTrap);
         } else { 
            say(actor, target.getName(), damage, true, false, isTrap);
         }
         target.takeDamage(damage);
      } else {
         say(actor, target.getName(), 0, false, false, isTrap);
      }
   }


   public void say(String user, String target, int dmgValue, boolean isHit, boolean isCrit, boolean isTrap){
      if (type.equals("Healing")){
         if(!user.equals(target)){
            System.out.print(user + " heals the " + target + "! ");
         }
         System.out.println(target + " regained " + dmgValue + " HP!");
      } else {
         if(!isTrap){
            System.out.println(user + super.getDesc());
         } else {
            System.out.println("A " + user + " triggered!"); // A TRAP triggered!
         }
         if(isHit){
            if(isCrit){
               System.out.println("Critical hit!");
            }
            System.out.println(dmgValue + " damage!"); // X _ damage!
         } else {
            System.out.println("But it missed...");
         }
      }
   }
}

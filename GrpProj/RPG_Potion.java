/**
@author Lucinder
@file RPG_Potion.java
**/
public class RPG_Potion extends RPG_Weapon{
   int[] tempStatBonuses = {0,0,0,0,0,0}; // some potions will temporarily change stats
   int[] tempStatOverrides = {0,0,0,0,0,0};
   // constructor overrides
   public RPG_Potion(String name, int quantity, int value, int[] healDice){
      super(name, quantity, value, healDice);
      this.setDamageType("Healing");
   }
   public RPG_Potion(String name, int quantity, int value, int[] healDice, int healMod){
      super(name, quantity, value, healDice, healMod);
      this.setDamageType("Healing");
   }
   public RPG_Potion(String name, int quantity, int value, boolean isOverride, int[] tempStatMods){
      super(name, quantity, value, new int[]{0,0}); // no healing
      if(!isOverride){
         this.tempStatBonuses = tempStatMods;
      } else {
         this.tempStatOverrides = tempStatMods;
      }
   }
   public RPG_Potion(RPG_Potion other){ // copy constr
      super(other.getName(), other.getQuantity(), other.getValue(), other.getDamageDice(), other.getMagicBonus());
      this.tempStatBonuses = other.getStatBonuses();
      this.tempStatOverrides = other.getStatOverrides();
      this.setDamageType(other.getDamageType());
   }
   
   // getters setters
   public int[] getStatBonuses(){ return tempStatBonuses; }
   public int[] getStatOverrides(){ return tempStatOverrides; }
   public void setHealingMod(int newMod){ this.setMagicBonus(newMod); }
   public void setStatBonuses(int[] newStatMods){ this.tempStatBonuses = newStatMods; }
   public void setStatOverrides(int[] newStatMods){ this.tempStatOverrides = newStatMods; }
   public int rollHealing(){
      return super.rollDamage();
   }
}
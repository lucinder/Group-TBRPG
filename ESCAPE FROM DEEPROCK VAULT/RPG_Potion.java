/**
@author Lucinder
@file RPG_Potion.java
**/
public class RPG_Potion extends RPG_Weapon{
   boolean isOverride; // does the stat modifier array override stats or just buff them?
   int[] tempStats = {0,0,0,0,0,0}; // some potions will temporarily change stats
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
      this.setDamageType("Buff");
      this.isOverride = isOverride;
      this.tempStats = tempStatMods;
   }
   public RPG_Potion(RPG_Potion other){ // copy constr
      super(other.getName(), other.getQuantity(), other.getValue(), other.getDamageDice(), other.getMagicBonus());
      this.tempStats = other.getStatMods();
      this.setDamageType(other.getDamageType());
   }
   
   // getters setters
   public boolean getIfOverride(){ return isOverride; }
   public int[] getStatMods(){ return tempStats; }
   public void setHealingMod(int newMod){ this.setMagicBonus(newMod); }
   public void setStatBonuses(int[] newStatMods){ this.tempStats = newStatMods; }
   public void toggleOverride(){ this.isOverride = !this.isOverride; }
   public int rollHealing(){
      return super.rollDamage();
   }
}
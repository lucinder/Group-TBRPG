/**
@author Lucinder
@file RPG_Armor.java
Armor class for handling armor values.
**/

public class RPG_Armor extends RPG_Item{
   private int baseArmor = 11; // base armor value- default = 11
   private int modifierCap = 100; // armor modifier cap- DNE by default (set to 100 because the modifier should never go that high)
   private int magicBonus = 0; // magical armor bonus- default 0
   public RPG_Armor(){
      super();
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor){
      super(name, quantity, value);
      this.baseArmor = baseArmor;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap){
      super(name, quantity, value);
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap, int magicBonus){
      super(name, quantity, value);
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
      this.magicBonus = magicBonus;
   }
   // getters/setters
   public int getAC(){
      return baseArmor + magicBonus;
   }
   public int getAC(int statMod){
      if(statMod > modifierCap){
         statMod = modifierCap; // max bonus = modifier cap
      }
      return baseArmor + magicBonus + statMod;
   }
   public int getMagicBonus(){ return magicBonus; }
   public void setMagicBonus(int newMagicBonus){ // for modifying weapons to be magical
      magicBonus = newMagicBonus;
   }
   // tostring
   public String toString(){
      String result = super.toString() + "\nArmor value: " + baseArmor + " plus DEX modifier";
      if(modifierCap < 100){
         result += " (max " + modifierCap + ")";
      }
      return result;
   }
   public int compareTo(RPG_Armor other){
      if(getAC() > other.getAC()){ return 1; }
      else if (getAC() == other.getAC()){ return 0; }
      return -1;
   }
}
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
      super("Dummy Armor",1,1,"Armor value: 11 plus DEX modifier");
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, String description){
      super(name, quantity, value, description);
      this.baseArmor = baseArmor;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor){
      super(name, quantity, value, "Armor value: " + baseArmor + " plus DEX modifier");
      this.baseArmor = baseArmor;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap, String description){
      super(name, quantity, value, description);
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap){
      super(name, quantity, value, "Armor value: " + baseArmor + " plus DEX modifier");
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap, int magicBonus, String description){
      super(name, quantity, value, description);
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
      this.magicBonus = magicBonus;
   }
   public RPG_Armor(String name, int quantity, int value, int baseArmor, int modifierCap, int magicBonus){
      super(name, quantity, value, "Armor value: " + baseArmor + " plus DEX modifier");
      this.baseArmor = baseArmor;
      this.modifierCap = modifierCap;
      this.magicBonus = magicBonus;
   }
   public RPG_Armor(RPG_Armor other){ // copy constructor
      super(other.getName(), other.getQuantity(), other.getValue(), other.getDescription());
      this.baseArmor = other.getBaseArmor();
      this.modifierCap = other.getModifierCap();
      this.magicBonus = other.getMagicBonus();
   }
   // getters/setters
   public int getBaseArmor(){ return baseArmor; }
   public int getModifierCap(){ return modifierCap; }
   public int getNoModAC(){ // AC without dex modifier
      return baseArmor + magicBonus;
   }
   public int getAC(int statMod){
      if(statMod > modifierCap){
         statMod = modifierCap; // max bonus = modifier cap
      }
      if(statMod < 0){ statMod = 0; } // no negative armor mods here!
      return baseArmor + magicBonus + statMod;
   }
   public int getMagicBonus(){ return magicBonus; }
   public void setMagicBonus(int newMagicBonus){ // for modifying weapons to be magical
      this.magicBonus = newMagicBonus;
   }
   // tostring
   public String toString(){
      String result = getName() + "\n" + getDescription();
      return result;
   }
   public int compareTo(RPG_Armor other){
      int thisAC = getNoModAC();
      int otherAC = other.getNoModAC();
      if(thisAC> otherAC){ return 1; }
      else if (thisAC == otherAC){ return 0; }
      return -1;
   }
}
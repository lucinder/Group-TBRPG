/**
@author Lucinder
@file RPG_Weapon.java
Weapon class for handling items with damage values.
**/

public class RPG_Weapon extends RPG_Item{
   private boolean finesse = false; // is weapon finesse ((or thrown)) (can use dex)? default false
   private boolean ranged = false; // is weapon ranged (MUST use dex)? default false
   private int[] damageDice = {1, 4}; // default damage dice 1d4
   private int magicBonus = 0; // magical to-hit + damage bonus- default 0
   private String damageType = "Damage"; // weapon damage type- default nonmagical damage ("Damage")
   public RPG_Weapon(){
      super();
   }
   public RPG_Weapon(RPG_Weapon other){
      super(other.getName(), other.getQuantity(), other.getValue());
      this.damageDice = other.getDamageDice();
      this.magicBonus = other.getMagicBonus();
      this.finesse = other.isFinesse();
      this.ranged = other.isRanged();
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice){
      super(name, quantity, value);
      this.damageDice = damageDice;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, int magicBonus){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.magicBonus = magicBonus;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, boolean finesse){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.finesse = finesse;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, boolean finesse, int magicBonus){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.finesse = finesse;
      this.magicBonus = magicBonus;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, boolean finesse, boolean ranged){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.finesse = finesse;
      this.ranged = ranged;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, boolean finesse, boolean ranged, int magicBonus){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.finesse = finesse;
      this.ranged = ranged;
      this.magicBonus = magicBonus;
   }
   public RPG_Weapon(String name, int quantity, int value, int[] damageDice, boolean finesse, boolean ranged, int magicBonus, String damageType){
      super(name, quantity, value);
      this.damageDice = damageDice;
      this.damageType = damageType;
      this.finesse = finesse;
      this.ranged = ranged;
      this.magicBonus = magicBonus;
   }
   // getters/setters
   public boolean isFinesse(){
      return finesse;
   }
   public boolean isRanged(){
      return ranged;
   }
   public int getMagicBonus(){
      return magicBonus;
   }
   public String getDamageType(){
      return damageType;
   }
   public int[] getDamageDice(){
      return damageDice;
   }
   public String getDamage(){
      return damageDice[0]+"d"+damageDice[1];
   }
   public int rollToHit(){ // base d20 roll to hit
      return RPG_Dice.XdY(1,20) + magicBonus;
   }
   public int rollToHit(int statMod){ // d20+stat roll to hit
      return RPG_Dice.XdY(1,20) + statMod + magicBonus;
   }
   public int rollToHit(int proficiency, int statMod){ // d20+stat+prof roll to hit
      return RPG_Dice.XdY(1,20) + statMod + proficiency + magicBonus;
   }
   public int rollDamage(){ // damage with no modifiers
      return RPG_Dice.XdY(damageDice[0], damageDice[1]) + magicBonus;
   }
   public int rollDamage(int statMod){ // damage with stat modifier 
      return RPG_Dice.XdY(damageDice[0], damageDice[1]) + statMod + magicBonus;
   }
   public double averageDamage(){
      return (damageDice[0] * (1 + (damageDice[1]-1)/2.0)) + magicBonus;
   }
   public double averageDamage(int statMod){
      return (damageDice[0] * (1 + (damageDice[1]-1)/2.0)) + magicBonus + statMod;
   }
   public void setMagicBonus(int newMagicBonus){ // for modifying weapons to be magical
      magicBonus = newMagicBonus;
   }
   public void setDamageType(String newDamageType){ // for modifying weapons to be magical
      damageType = newDamageType;
   }
   // tostring
   public String toString(){
      return super.toString() + "\nDeals " + getDamage() + " " + getDamageType() + " per hit.";
   }
   public int compareTo(RPG_Weapon other){
      double avgThis = averageDamage();
      double avgOther = other.averageDamage();
      if(avgThis > avgOther){ return 1; }
      else if (avgThis == avgOther){ return 0; }
      return -1;
   }
}
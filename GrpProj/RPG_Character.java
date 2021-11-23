/**
@author Lucinder
@file RPG_NPC
SUPERCLASS for Player Characters AND Enemies
Handles stat arrays, HP, and actions
**/
import java.util.ArrayList;
import java.util.Arrays;
public class RPG_Character extends RPG_Interactable{
   public final RPG_Action UNARMED_STRIKE = new RPG_Action();
   public class RPG_Action{
      private String name = "Attack"; // Action name
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
      private int toHit = getProficiencyBonus(); // To-hit modifier (on top of the existing damage modifier)
      public RPG_Action(){}
      public RPG_Action(String name, String type){
         this.name = name;
         this.type = type;
      }
      public RPG_Action(int[] dice){ // Default attack with different damage
         this.dice = dice;
      }
      public RPG_Action(String name, String type, int[] dice, int modifier){
         this.name = name;
         this.type = type;
         this.dice = dice;
         this.modifier = modifier;
      }
      public RPG_Action(String name, String type, int[] dice, int modifier, int proficiency){
         this.name = name;
         this.type = type;
         this.dice = dice;
         this.modifier = modifier;
         this.toHit = proficiency;
      }
      public RPG_Action(RPG_Weapon weapon, int modifier, int proficiency){
         this.name = weapon.getName() + " Attack";
         this.type = weapon.getDamageType();
         this.dice = weapon.getDamageDice();
         this.modifier = modifier + weapon.getMagicBonus();
         this.toHit = proficiency;
      }
      public String getName(){ return name; }
      public String getType(){ return type; }
      public int[] getDice(){ return dice; }
      public int rollToHit(){
         int roll = RPG_Dice.XdY(1,20) + modifier + toHit;
         System.out.print("Roll to hit: " + roll + " "); // testing purposes only
         return roll;
      }
      public boolean isCrit(int roll){ return (roll - (modifier + toHit)) == 20; }
      public int rollDamage(){
         return RPG_Dice.XdY(dice[0], dice[1]) + modifier;
      }
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
      }
      public boolean equals(Object otherItem){
         if(otherItem instanceof RPG_Action){
            RPG_Action other = (RPG_Action)otherItem;
            return name.equals(other.getName()) && type.equals(other.getType()) && Arrays.equals(dice, other.getDice());
         }
         return false;
      }
      public int say(String user, String target, int AC){
         int roll = 0;
         if (type.equals("Healing")){
            System.out.print(user + " heals the " + target + "! ");
            roll = rollDamage();
            System.out.println(target + " regained " + roll + " HP!");
         } else {
            System.out.print(user + " attacks the " + target + "! ");
            roll = fullRoll(AC);
         }
         return roll;
      }
   }
   
   public class RPG_Buff extends RPG_Action{
      private int[] dice = {0,0}; // Buffs/nerfs use no dice by default
      private int[] statModifiers = {0,0,0,0,0,0}; // no stat modifiers by default
      private String[] typesGained = {}; // type resistances gained (Nerf subclass treats these as weaknesses)
      public RPG_Buff(){ super("StandardBuff","Buff"); }
      public RPG_Buff(String name, String type){ super(name, type); }
      public RPG_Buff(String name, int[] statModifiers){
         super(name, "Buff");
         this.statModifiers = statModifiers;
      }
      public RPG_Buff(String name, String type, int[] statModifiers){
         super(name, type);
         this.statModifiers = statModifiers;
      }
      public RPG_Buff(String name, String[] resistancesGained){
         super(name, "Buff");
         this.typesGained = resistancesGained;
      }
      public RPG_Buff(String name, String type, String[] resistancesGained){
         super(name, type);
         this.typesGained = resistancesGained;
      }
      public RPG_Buff(String name, int[] statModifiers, String[] resistancesGained){
         super(name, "Buff");
         this.statModifiers = statModifiers;
         this.typesGained = resistancesGained;
      }
      public RPG_Buff(String name, String type, int[] statModifiers, String[] resistancesGained){
         super(name, type);
         this.statModifiers = statModifiers;
         this.typesGained = resistancesGained;
      }
      public int[] getStatModifiers(){ return statModifiers; }
      public String[] getTypes(){ return typesGained; }
      public boolean equals(Object otherItem){
         if(otherItem instanceof RPG_Buff){
            RPG_Buff other = (RPG_Buff)otherItem;
            return name.equals(other.getName()) && super.getType().equals(other.getType()) && Arrays.equals(statModifiers, other.getStatModifiers()) && Arrays.equals(typesGained, other.getTypes());
         }
         return false;
      }
      public int[] say(String user, String target){
         System.out.print(user + " used " + super.getName() + " on the " + target + "! ");
         if(statModifiers[0] != 0){
            System.out.print(target + "\'s STR increased by " + statModifiers[0] + "! ");
         } else if (statModifiers[1] != 0){
            System.out.print(target + "\'s DEX increased by " + statModifiers[1] + "! ");
         } else if(statModifiers[2] != 0){
            System.out.print(target + "\'s CON increased by " + statModifiers[2] + "! ");
         } else if(statModifiers[3] != 0){
            System.out.print(target + "\'s INT increased by " + statModifiers[3] + "! ");
         } else if(statModifiers[4] != 0){
            System.out.print(target + "\'s WIS increased by " + statModifiers[4] + "! ");
         } else if(statModifiers[5] != 0){
            System.out.print(target + "\'s CHA increased by " + statModifiers[5] + "! ");
         } else {
            System.out.print(target + "\'s maximum HP increased by " + statModifiers[6] + "! ");
         }
         if(typesGained.length > 0){
            System.out.print(target + " gained resistance to ");
            String toPrint = "";
            for(String s : typesGained){
               toPrint += s + ", ";
            }
            toPrint = toPrint.substring(0, toPrint.length()-2);
            System.out.println(toPrint + ".");
         }
         return statModifiers;
      }
   }
   
   public class RPG_Debuff extends RPG_Buff{ // debuff- soft-overrides constructors (passes different action type + replaces a parameter name), say() function (replacing gains with losses)
      public RPG_Debuff(){ super("StandardDebuff","Debuff"); }
      public RPG_Debuff(String name, int[] statModifiers){
         super(name, "Debuff", statModifiers);
      }
      public RPG_Debuff(String name, String[] weaknessesGained){
         super(name, "Debuff", weaknessesGained);
      }
      public RPG_Debuff(String name, int[] statModifiers, String[] weaknessesGained){
         super(name, "Debuff", statModifiers, weaknessesGained);
      }
      public int[] say(String user, String target){
         System.out.print(user + " used " + getName() + " on the " + target + "! ");
         if(super.getStatModifiers()[0] != 0){
            System.out.print(target + "\'s STR decreased by " + Math.abs(super.getStatModifiers()[0]) + "! ");
         } else if (super.getStatModifiers()[1] != 0){
            System.out.print(target + "\'s DEX decreased by " + Math.abs(super.getStatModifiers()[1]) + "! ");
         } else if(super.getStatModifiers()[2] != 0){
            System.out.print(target + "\'s CON decreased by " + Math.abs(super.getStatModifiers()[2]) + "! ");
         } else if(super.getStatModifiers()[3] != 0){
            System.out.print(target + "\'s INT decreased by " + Math.abs(super.getStatModifiers()[3]) + "! ");
         } else if(super.getStatModifiers()[4] != 0){
            System.out.print(target + "\'s WIS decreased by " + Math.abs(super.getStatModifiers()[4]) + "! ");
         } else if(super.getStatModifiers()[5] != 0){
            System.out.print(target + "\'s CHA decreased by " + Math.abs(super.getStatModifiers()[5]) + "! ");
         } else {
            System.out.print(target + "\'s maximum HP decreased by " + Math.abs(super.getStatModifiers()[6]) + "! ");
         }
         if(super.getTypes().length > 0){
            System.out.print(target + " became weak to ");
            String toPrint = "";
            for(String s : super.getTypes()){
               toPrint += s + ", ";
            }
            toPrint = toPrint.substring(0, toPrint.length()-2);
            System.out.println(toPrint + ".");
         }
         return super.getStatModifiers();
      }

   }
   
   private String name;
   int[] stats = RPG_Dice.randchar(); // {STR, DEX, CON, INT, WIS, CHA}
   private int hpMax = 1; // max hp
   private int hpCur = 1; // current hp
   private int AC = 10;
   private ArrayList<RPG_Item> inventory = new ArrayList<RPG_Item>();
   private ArrayList<RPG_Action> actions = new ArrayList<RPG_Action>();
   public RPG_Character(){}
   public RPG_Character(String name){
      this.name = name;
      actions.add(new RPG_Action());
   }
   public RPG_Character(String name, int hpMax){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      actions.add(new RPG_Action()); // Every character should get a default unarmed attack
   }
   public RPG_Character(String name, int hpCurrent, int hpMax){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpCurrent;
      actions.add(new RPG_Action());
   }
   public RPG_Character(String name, int hpMax, int[] stats, int AC){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      this.stats = stats;
      this.AC = AC;
      actions.add(new RPG_Action());
   }
   public RPG_Character(String name, int hpMax, int[] stats, int AC, RPG_Item[] inventory){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      this.stats = stats;
      this.AC = AC;
      for(RPG_Item i : inventory){
         addItem(i);
      }
      loadInventory();
   }
   public RPG_Character(String name, int hpMax, int[] stats, int AC,  RPG_Action[] actions){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      this.stats = stats;
      this.AC = AC;
      for(RPG_Action a : actions){
         this.actions.add(a);
      }
   }
   public RPG_Character(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, RPG_Item[] inventory){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      this.stats = stats;
      this.AC = AC;
      for(RPG_Action a : actions){
         this.actions.add(a);
      }
      for(RPG_Item i : inventory){
         addItem(i);
      }
      loadInventory();
   }
   // getters setters
   public String getName(){ return name; }
   public int getMaxHP(){ return hpMax; }
   public int getCurrentHP(){ return hpCur; }
   public void setCurrentHP(int newHP){
      if(newHP < hpMax){
         hpCur = newHP;
      } else if (newHP < 0) {
         hpCur = 0;
      } else {
         hpCur = hpMax;
      }
   }
   public void setMaxHP(int newHP){
      hpMax = newHP;
   }
   public int getAC(){ return AC; }
   public void setAC(int newAC){ this.AC = newAC; }
   public int[] getStats(){ return stats; }
   public ArrayList<RPG_Item> getInventory(){ return inventory; }
   public ArrayList<RPG_Action> getActions(){ return actions; }
   public boolean hasAction(String actionName){
      for(RPG_Action a : actions){
         if(a.getName().equals(actionName)){
            return true;
         }
      }
      return false;
   }
   public RPG_Action getAction(String actionName){
      for(RPG_Action a : actions){
         if(a.getName().equals(actionName)){
            return a;
         }
      }
      return null;
   }
 
   public void takeDamage(int damage){
      setCurrentHP(hpCur - damage);
   }
   public void heal(int healing){
      setCurrentHP(hpCur + healing);
   }
   public void fullHeal(){ hpCur = hpMax; }
   
   public void addItem(RPG_Item item){
      if(inventory.indexOf(item) != -1){
         inventory.get(inventory.indexOf(item)).incrementQuantity();
      } else {
         inventory.add(item);
      }
   }
   public boolean inventoryContains(String itemName){
      for(RPG_Item i : inventory){
         if(i.getName().equals(itemName)){ return true; }
      }
      return false;
   }
   public void loadInventory(){ // load all weapons, potions as actions
      for(RPG_Item i : inventory){
         if(i instanceof RPG_Weapon){
            RPG_Weapon weapon = (RPG_Weapon)i;
            int mod = 0;
            if(weapon.isRanged() || (weapon.isFinesse() && stats[1] > stats[0])){
               mod += RPG_Dice.getModifier(stats[1]); // use dex mod
            } else {
               mod += RPG_Dice.getModifier(stats[0]); // use str mod
            }
            RPG_Action weaponattack = new RPG_Action(weapon, mod, getProficiencyBonus());
            if(hasAction(weaponattack.getName())){
               replaceAction(weaponattack.getName(),weaponattack);
            } else if(actions.contains(UNARMED_STRIKE)){
               replaceAction("Attack", weaponattack);
            } else {
               addAction(weaponattack);
            }
         }
      }
   }
   public void printInventory(){ // prints out all items in inventory
      for(RPG_Item i : inventory){
         System.out.println(i.getName() + " x" + i.getQuantity());
      }
   }
   
   public void addAction(RPG_Action newAction){
      actions.add(newAction);
   }
   public RPG_Action removeAction(String toRemove){
      int foundIndex = -1;
      int i = 0;
      for(RPG_Action a : actions){
         if(a.getName().equals(toRemove)){
            foundIndex = i; // for duplicate cases, the last instance will be set to the removal instance
         }
         i++;
      }
      if(foundIndex == -1){ return null; } // if no action to remove is found, quit early
      return actions.remove(foundIndex);
   }
   public RPG_Action replaceAction(String oldActionName, RPG_Action newAction){ // returns the replaced action!
      RPG_Action temp = removeAction(oldActionName);
      addAction(newAction);
      return temp;
   }
   public void printActions(){
      for(RPG_Action a : actions){
         System.out.println("[" + a.getName().toUpperCase() + "]");
      }
   }
   
   public int getProficiencyBonus(){ // Default character prof bonus is 2; modified to be based on CR or level based on subclass
      return 2;
   }
   
   // rolls
   public int strSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[0]);
      return saveRoll + saveMod;
   }
   public int dexSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[1]);
      return saveRoll + saveMod;
   }
   public int conSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[2]);
      return saveRoll + saveMod;
   }
   public int intSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[3]);
      return saveRoll + saveMod;
   }
   public int wisSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[4]);
      return saveRoll + saveMod;
   }
   public int chaSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[5]);
      return saveRoll + saveMod;
   }

   public boolean strSave(int DC){
      int saveRoll = strSave();
      return (saveRoll >= DC);
   }
   public boolean dexSave(int DC){
      int saveRoll = dexSave();
      return (saveRoll >= DC);
   }
   public boolean conSave(int DC){
      int saveRoll = conSave();
      return (saveRoll >= DC);
   }
   public boolean intSave(int DC){
      int saveRoll = intSave();
      return (saveRoll >= DC);
   }
   public boolean wisSave(int DC){
      int saveRoll = wisSave();
      return (saveRoll >= DC);
   }
   public boolean chaSave(int DC){
      int saveRoll = chaSave();
      return (saveRoll >= DC);
   }
   
   public boolean save(int saveRoll, int DC){
      return (saveRoll >= DC);
   }
   //tostring
   public String toString(){
      String output = name + "\nHP: " + hpCur + "/"+ hpMax + "\n";
      for(int i = 0; i < 6; i++){
         if(i == 0){
            output += "STR: ";
         } else if(i == 1){
            output += "DEX: ";
         } else if(i == 2){
            output += "CON: ";
         } else if(i == 3){
            output += "INT: ";
         } else if(i == 4){
            output += "WIS: ";
         } else {
            output += "CHA: ";
         }
         output += stats[i] + "\n";
      }
      return output;
   }
}
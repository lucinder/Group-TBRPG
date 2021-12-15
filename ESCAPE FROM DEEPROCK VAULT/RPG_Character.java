/**
@author Lucinder
@file RPG_NPC
SUPERCLASS for Player Characters AND Enemies
Handles stat arrays, HP, and actions
**/
import java.util.ArrayList;
public class RPG_Character extends RPG_Interactable{
   public static final RPG_Action DONOTHING = new RPG_Action();
   public static final RPG_Attack UNARMED_STRIKE = new RPG_Attack();
   public static final int TEXTDELAY = 20;
   public static final int LINEDELAY = 0;
   
   public static void printStaggered(String input){
      try{
         printStaggered(input, TEXTDELAY);
      } catch(InterruptedException e){} // ignore interruption
   }
   public static void printStaggered(String input, int delayMS) throws InterruptedException{ // ONLY works for single-line commands
      String[] toPrint = input.split("");
      for(String s : toPrint){
         System.out.print(s);
         Thread.sleep(delayMS);
      }
      System.out.println();
      Thread.sleep(LINEDELAY);
   }
   
   private String name;
   int[] stats = RPG_Dice.randchar(); // {STR, DEX, CON, INT, WIS, CHA}
   private int hpMax = 1; // max hp
   private int hpCur = 1; // current hp
   private int mpMax = 0;
   private int mpCur = 0;
   private int AC = 10;
   private int GP = 0;
   private int proficiencyBonus = 2;
   private ArrayList<RPG_Item> inventory = new ArrayList<RPG_Item>();
   private ArrayList<RPG_Action> actions = new ArrayList<RPG_Action>();
   private ArrayList<RPG_Action> itemActions = new ArrayList<RPG_Action>(); // handles using inventory stuff
   private ArrayList<String> damageResistances = new ArrayList<String>(); // damage type resistances - default none
   private ArrayList<String> damageWeaknesses = new ArrayList<String>();
   private ArrayList<String> conditionImmunities = new ArrayList<String>(); // condition immunities
   private ArrayList<String> conditionAdvantages = new ArrayList<String>(); // condition save advantages
   public RPG_Character(){}
   public RPG_Character(String name){
      this.name = name;
   }
   public RPG_Character(String name, int hpMax){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
   }
   public RPG_Character(String name, int hpCurrent, int hpMax){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpCurrent;
   }
   public RPG_Character(String name, int hpMax, int[] stats, int AC){
      this.name = name;
      this.hpMax = hpMax;
      this.hpCur = hpMax;
      this.stats = stats;
      this.AC = AC;
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
   }
   
   public RPG_Character(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions){
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
   }
   // getters setters
   public String getName(){ return name; }
   public void setName(String newName){ name = newName; }
   public int getMaxHP(){ return hpMax; }
   public int getHP(){ return getCurrentHP(); } // pseudooverload for simplicity
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
   public void setHP(int newHP) { setCurrentHP(newHP); }
   public void setMaxHP(int newHP){
      hpMax = newHP;
   }
   public int getMaxMP(){ return mpMax; }
   public int getMP(){ return getCurrentMP(); } // pseudooverload for simplicity
   public int getCurrentMP(){ return mpCur; }
   public void setCurrentMP(int newMP){
      if(newMP < mpMax){
         mpCur = newMP;
      } else if (newMP < 0) {
         mpCur = 0;
      } else {
         mpCur = mpMax;
      }
   }
   public void setMP(int newMP) { setCurrentMP(newMP); }
   public void setMaxMP(int newMP){
      mpMax = newMP;
   }
   public void useMP(int toUse){ setMP(mpCur-toUse); }
   public void rechargeMP(){ setMP(mpMax); }
   
   public int getAC(){ return AC; }
   public void setAC(int newAC){ this.AC = newAC; }
   public int[] getStats(){ return stats; }
   public int getStat(int stat){ return stats[stat]; };
   public ArrayList<String> getResistances(){ return damageResistances; }
   public ArrayList<String> getWeaknesses(){ return damageWeaknesses; }
   public void setStats(int[] newStats){ stats = newStats; }
   public void setStr(int newStat){ stats[0] = newStat; }
   public void setDex(int newStat){ stats[1] = newStat; }
   public void setCon(int newStat){ stats[2] = newStat; }
   public void setInt(int newStat){ stats[3] = newStat; }
   public void setWis(int newStat){ stats[4] = newStat; }
   public void setCha(int newStat){ stats[5] = newStat; }
   public void addToStr(int toAdd){ stats[0] += toAdd; }
   public void addToDex(int toAdd){ stats[1] += toAdd; }
   public void addToCon(int toAdd){ stats[2] += toAdd; }
   public void addToInt(int toAdd){ stats[3] += toAdd; }
   public void addToWis(int toAdd){ stats[4] += toAdd; }
   public void addToCha(int toAdd){ stats[5] += toAdd; }
   public void addMaxHP(int toAdd){ setMaxHP(getMaxHP() + toAdd); }
   // add stuff to the resistance/weakness/immunity lists
   public void addResistance(String toAdd){
      if(!damageResistances.contains(toAdd)){
         damageResistances.add(toAdd);
      }
   }
   public void addWeakness(String toAdd){
      if(!damageWeaknesses.contains(toAdd)){
         damageWeaknesses.add(toAdd);
      }
   }
   public void addConditionAdvantage(String toAdd){
      if(!conditionAdvantages.contains(toAdd)){
         conditionAdvantages.add(toAdd);
      }
   }
   public void addConditionImmunity(String toAdd){
      if(!conditionImmunities.contains(toAdd)){
         conditionImmunities.add(toAdd);
      }
   }
   public ArrayList<RPG_Item> getInventory(){ return inventory; }
   public ArrayList<RPG_Action> getActions(){ return actions; }
   public ArrayList<RPG_Action> getItemActions(){ return itemActions; }
   public void setActions(ArrayList<RPG_Action> newSet){ this.actions = newSet; }
   public void setItemActions(ArrayList<RPG_Action> newSet){ this.itemActions = newSet; }
   public RPG_Item[] getInventoryArray(){ // converts inventory list to an array and returns array
      RPG_Item[] output = new RPG_Item[inventory.size()];
      int i = 0;
      for(RPG_Item a : inventory){
         output[i] = a;
         i++;
      }
      return output;
   }
   public RPG_Action[] getActionsArray(){ // converts action list to an array and returns array
      RPG_Action[] output = new RPG_Action[actions.size()];
      int i = 0;
      for(RPG_Action a : actions){
         output[i] = a;
         i++;
      }
      return output;
   }
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
   public void act(RPG_Action action, RPG_Character target){
      action.act(this, target);
   }
 
   public void takeDamage(int damage){
      setCurrentHP(hpCur - damage);
   }
   public void heal(int healing){
      setCurrentHP(hpCur + healing);
   }
   public void fullHeal(){ hpCur = hpMax; }
   
   // Inv management
   public int getGP(){ return GP; } // how much gold do we have?
   public void setGP(int newGP){ this.GP = newGP; }
   public void gainGP(int toAdd){ // gain gold
      printStaggered("Gained " + toAdd + " GP!");
      this.GP += toAdd;
   }
   public void removeGP(int toRemove){
      this.GP -= toRemove;
   }
   public void addItem(RPG_Item item){
      if(inventory.indexOf(item) != -1){
         inventory.get(inventory.indexOf(item)).incrementQuantity(); // if we have a duplicate, just increment its quantity
      } else {
         if(item.getName().equals("Gold")){ // cut method short for gold-pile gaining
            gainGP(item.getValue());
            return;
         }
         inventory.add(item);
         if(item instanceof RPG_Weapon || item instanceof RPG_Armor){ // reload inv whenever we gain a weapon, armor, or shield
            loadInventory();
         }
      }
   }
   
   public RPG_Item getItem(String itemName){ // get an item in the user's inventory by name
      for(RPG_Item i : inventory){
         if(i.getName().equals(itemName)){
            return i;
         }
      }
      return null;
   }
   
   public void useItem(String itemName){
      if(!inventoryContains(itemName)){ return; } // stop short if item does not exist
      for(RPG_Item i : inventory){
         if(i.getName().equals(itemName)){
            if(i.getQuantity() == 1){
               inventory.remove(i); // remove the first instance of the item
            } else {
               i.decrementQuantity();
            }
            loadInventory();
            return; // cut loop short
         }
      }
   }
   
   public void removeItem(RPG_Item item){
      for(RPG_Item i : inventory){
         if(i.getName().equals(item.getName())){
            if(i.getQuantity() == 1){
               inventory.remove(i); // remove the first instance of the item
            } else {
               i.decrementQuantity();
            }
            loadInventory();
            return; // cut loop short
         }
      }
   }
   public boolean hasItem(String itemName){ return inventoryContains(itemName); } // multiple names for methods, for my sanity
   public boolean inventoryContains(String itemName){
      for(RPG_Item i : inventory){
         if(i.getName().equals(itemName)){ return true; }
      }
      return false;
   }
   public void loadInventory(){ // load all weapons, potions as actions
      // CLEAR ACTIONS
      actions = new ArrayList<RPG_Action>();
      itemActions = new ArrayList<RPG_Action>();
      for(RPG_Item i : inventory){
         if(i instanceof RPG_Weapon){
            RPG_Attack weaponattack;
            RPG_Weapon weapon = (RPG_Weapon)i;
            if(!(i instanceof RPG_Potion || i instanceof RPG_SpellScroll)){
               int mod = 0;
               if(weapon.isRanged() || (weapon.isFinesse() && stats[1] > stats[0])){
                  mod += dexModifier(); // use dex mod
               } else {
                  mod += strModifier(); // use str mod
               }
               weaponattack = new RPG_Attack(weapon, mod, getProficiencyBonus());
               if(hasAction(weaponattack.getName())){
                  replaceAction(weaponattack.getName(),weaponattack);
               } else {
                  addAction(weaponattack);
               }
            } else {
               if(weapon instanceof RPG_Potion){ // detect potions
                  RPG_Potion pot = (RPG_Potion)weapon;
                  if(weapon.getDamageType().equals("Buff")){ // detect stat-modifying potions
                     weaponattack = new RPG_Buff(pot);
                  } else {
                     weaponattack = new RPG_Attack(pot, 0, 0);
                  }
               } else { // spell scroll
                  RPG_SpellScroll scroll = (RPG_SpellScroll)weapon;
                  weaponattack = new RPG_Attack(scroll.getSpell().getSpell()); // wack but works
               }
               if(hasAction(weaponattack.getName())){
                  replaceItemAction(weaponattack.getName(),weaponattack);
               } else {
                  addItemAction(weaponattack);
               }
            }
         }
      }
      loadArmorClass(); // always reload AC after loading inv
   }
   
   // getters for stat modifiers
   public int strModifier(){
      return RPG_Dice.getModifier(stats[0]);
   }
   public int dexModifier(){
      return RPG_Dice.getModifier(stats[1]);
   }
   public int conModifier(){
      return RPG_Dice.getModifier(stats[2]);
   }
   public int intModifier(){
      return RPG_Dice.getModifier(stats[3]);
   }
   public int wisModifier(){
      return RPG_Dice.getModifier(stats[4]);
   }
   public int chaModifier(){
      return RPG_Dice.getModifier(stats[5]);
   }
   
   public void printInventory() throws InterruptedException{ // prints out all items in inventory
      printStaggered("INVENTORY:");
      for(RPG_Item i : inventory){
         printStaggered(i.getName() + " x" + i.getQuantity());
      }
   }
   
   private void loadArmorClass(){  
      int unarmoredDefense = 10 + dexModifier();
      int shieldBonus = 0;
      int newAC = unarmoredDefense;
      for(RPG_Item i : this.getInventory()){ // load armor for items
         if(i instanceof RPG_Armor){
            int curAC = ((RPG_Armor)i).getAC(dexModifier());
            if(curAC > newAC){
               newAC = curAC;
            }
         }
         if(i instanceof RPG_Shield){
            int shielding = ((RPG_Shield)i).getACBonus();
            if(shielding > 5){
               System.out.println("DEBUG - something is wrong with this shield!");
            }
            if(shielding > shieldBonus){
               shieldBonus = shielding;
            }
         }
      }
      setAC(newAC + shieldBonus); // apply shield bonus
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
   public void addItemAction(RPG_Action newAction){
      itemActions.add(newAction);
   }
   public RPG_Action removeItemAction(String toRemove){
      int foundIndex = -1;
      int i = 0;
      for(RPG_Action a : itemActions){
         if(a.getName().equals(toRemove)){
            foundIndex = i; // for duplicate cases, the last instance will be set to the removal instance
         }
         i++;
      }
      if(foundIndex == -1){ return null; } // if no action to remove is found, quit early
      return itemActions.remove(foundIndex);
   }
   public RPG_Action replaceItemAction(String oldActionName, RPG_Action newAction){ // returns the replaced action!
      RPG_Action temp = removeItemAction(oldActionName);
      addItemAction(newAction);
      return temp;
   }
   public void printActions() throws InterruptedException{
      for(RPG_Action a : actions){
         printStaggered("[" + a.getName().toUpperCase() + "]");
      }
   }
   
   public int getProficiencyBonus(){ // Default character prof bonus is 2; modified to be based on CR or level based on subclass
      return proficiencyBonus;
   }
   public void setProficiencyBonus(int newBonus){
      this.proficiencyBonus = newBonus;
   }
   
   // rolls
   public int initiative(){ // identical to dexSave here, but distinguishing it is necessary because some subclasses override dexSave
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[1]);
      return saveRoll + saveMod;
   }
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
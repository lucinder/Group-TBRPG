/**
@author Lucinder
@file RPG_Player
PLAYER CHARACTER CLASS
Handles a lot of things, including the execution of racial features ingame and leveling
THIS CLASS IS UNFINISHED
**/


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.io.IOException;
import java.util.Scanner;

public class RPG_Player extends RPG_Character{
   public class RPG_Optional_Race_Features{
      private static boolean hasDarkvision = false;
      private static String breathWeaponType = "None"; // breath weapon type for dragonborn - default none (no breath weapon)
      private static int[] breathWeaponDice = {2,6}; // breath weapon damage dice - 2d6 default. should increase at certain levels
      private static boolean breathWeaponSaveDex = true; // is the breath weapon save dex (true) or con (false)? default true
      private static boolean breathWeaponUsed = false; // has the breath weapon been used this battle?
      private static boolean lucky = false; // character has the lucky feature?
      private static boolean luckyUsed = false; // has the lucky feature been used this rest - default false
      private static boolean cunning = false; // character has the gnome cunning feature?
      private static boolean nimble = false; // character has the nimble escape feature?
      private static boolean relentless = false; // character has the relentless endurance feature?
      private static boolean relentlessUsed = false; // has the relentless endurance feature been used this rest - default false
      private static boolean savage = false; // character has the savage attacks feature?
   }
   
   private boolean pacifist = true; // has the character spared all enemies so far?
   private boolean hidden = false; // for rogues- is the character currently hidden?
   private static int level = 1;
   private static int totalXP = 0;
   private RPG_Race pcRace = new RPG_Race();
   private RPG_Class pcClass = new RPG_Class();
   
   public RPG_Player(){ // no-arg constructor- DO NOT USE
      super();
   }
   public RPG_Player(String name, RPG_Race pcRace, RPG_Class pcClass) throws IOException{
   // 3-arg constructor- use this for randomly generating level 1 characters!
      super(name);
      this.pcRace = pcRace;
      this.pcClass = pcClass;
      
      int[] temp = RPG_Dice.randchar();
      //System.out.println("Unsorted Stat Roll: " + arrayToString(temp));
      List<Integer> statsTemp = Arrays.stream(temp).boxed().collect(Collectors.toList());
      int[] stats = new int[]{-1, -1, -1, -1, -1, -1};
      for(int i = 0; i < 3; i++){ // set 3 most important stats by class
         int largest = largestItemIndex(statsTemp);
         if(i == 0){
            stats[pcClass.getPrimaryStat()] = statsTemp.remove(largest);
         } else if (i == 1){
            stats[pcClass.getSecondaryStat()] = statsTemp.remove(largest);
         } else {
            stats[pcClass.getTertiaryStat()] = statsTemp.remove(largest);
         }
      }
      for(int i = stats.length - 1; i >= 0; i--){ // fill in remaining stats in reverse-descending order (str will generally be dumped)
         if(stats[i] == -1){
            int largest = largestItemIndex(statsTemp);
            stats[i] = statsTemp.remove(largest);
         }
      }
      System.out.println("Stats before racial asi:" + arrayToString(stats)); // testing purposes: print out stat array after modification
      stats = pcRace.applyASI(stats);
      System.out.println("Stats after racial asi: " + arrayToString(stats));
      super.stats = stats;
      // handle hp
      int hp = pcClass.getHitDie() + RPG_Dice.getModifier(stats[2]);
      super.setMaxHP(hp);
      super.fullHeal();
      // handle inventory
      for(RPG_Item i : pcClass.getStartingEquipment()){
         if(i.equals(RPG_Items_List.PLACEHOLDER_SIMPLE)){ // placeholder randomization
            super.addItem(RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE[(int)(Math.random()*RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE.length)]);
         } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL)) {
            super.addItem(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
         } else {
            super.addItem(i);
         }
      }
      super.loadInventory();
      registerOptionalRaceFeatures(); // handle optional race features
      loadArmorClass();
   }
   public int getTotalXP(){ return totalXP; }
   public void gainXP(int toAdd){
      this.totalXP += toAdd;
      // add leveling here
   }
   
   private void loadArmorClass(){  // load unarmored defense for monk/barbarian classes or armor for classes that start with it
      int unarmoredDefense = 10 + RPG_Dice.getModifier(super.getStats()[1]);
      if(pcClass.getName().equals("Monk")){
         unarmoredDefense = 10 + RPG_Dice.getModifier(super.getStats()[1]) + RPG_Dice.getModifier(super.getStats()[4]);
      } else if(pcClass.getName().equals("Barbarian")){
         unarmoredDefense = 10 + RPG_Dice.getModifier(super.getStats()[1]) + RPG_Dice.getModifier(super.getStats()[2]);
      }  
      if(unarmoredDefense > super.getAC()){
         super.setAC(unarmoredDefense);
      }
      for(RPG_Item i : getInventory()){ // load armor for items
         if(i instanceof RPG_Armor){
            int newAC = ((RPG_Armor)i).getAC(super.dexModifier());
            if(newAC > super.getAC()){
               super.setAC(newAC);
            }
         }
      }
   }
   
   private int largestItemIndex(List<Integer> set){
      if(set.size() < 1){ return -1; }
      int maxIndex = 0;
      for(int i = 1; i < set.size(); i++){
         if(set.get(i) > set.get(maxIndex)){
            maxIndex = i;
         }
      }
      return maxIndex;
   }
   
   // Registry for optional race features
   private void registerOptionalRaceFeatures(){
      RPG_Race.RaceFeature[] features = pcRace.getFeatures();
      for(RPG_Race.RaceFeature rf : features){
         if(rf.getName().equals("Darkvision")){
            RPG_Optional_Race_Features.hasDarkvision = true;
         }
         if(rf.getName().equals("Hellish Resistance")){
            addResistance("Fire");
         }
         if(rf.getName().equals("Lucky")){
            RPG_Optional_Race_Features.lucky = true; // lucky
         }
         if(rf.getName().equals("Draconic Ancestry")){
            chooseAncestry();
         }
         if(rf.getName().equals("Dwarven Resilience")){
            addResistance("Poison");
            addConditionAdvantage("Poison");
         }
         if(rf.getName().equals("Fey Ancestry")){
            addConditionAdvantage("Charmed");
            addConditionImmunity("Sleep");
         }
         if(rf.getName().equals("Brave")){
            addConditionAdvantage("Frightened");
         }
         if(rf.getName().equals("Relentless Endurance")){
            RPG_Optional_Race_Features.relentless = true; // relentless
         }
         if(rf.getName().equals("Savage Attacks")){
            RPG_Optional_Race_Features.savage = true;
         }
         // tba: infernal legacy
      }
   }
   
   private void chooseAncestry(){ // chooser function for draconic ancestry (dragonborn only)
      Scanner input = new Scanner(System.in);
      boolean metalChromChosen = false;
      boolean isMetal = false;
      boolean typeChosen = false;
      System.out.println("Are you a metallic or chromatic dragonborn?");
      String next = input.nextLine();
      while(!(next.equals("Q")) && !typeChosen){
         if(!metalChromChosen){
            if(next.toUpperCase().equals("METALLIC")){
               metalChromChosen = true;
               isMetal = true;
               System.out.println("Choose your scale metal!\n[1] Gold\n[2] Silver\n[3] Bronze\n[4] Brass\n[5] Copper");
            } else if (next.toUpperCase().equals("CHROMATIC")){
               metalChromChosen = true;
               System.out.println("Choose your scale color!\n[1] Red\n[2] Green\n[3] Blue\n[4] Black\n[5] White");
            } else {
               if(next.equals("Q")) {
                  System.exit(0);
               }
               System.out.println("Input not recognized!");
               System.out.println("Are you a metallic or chromatic dragonborn?");
            }
         } else {
            if(!isMetal){
               typeChosen = true;
               if(next.toUpperCase().equals("RED") || next.equals("1") || next.equals("[1]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Fire";
                  addResistance("Fire");
               }
               else if(next.toUpperCase().equals("GREEN") || next.equals("2") || next.equals("[2]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Poison";
                  addResistance("Poison");
                  RPG_Optional_Race_Features.breathWeaponSaveDex = false;
               }
               else if(next.toUpperCase().equals("BLUE") || next.equals("3") || next.equals("[3]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Lightning";
                  addResistance("Lightning");
               }
               else if(next.toUpperCase().equals("BLACK") || next.equals("4") || next.equals("[4]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Acid";
                  addResistance("Acid");
               }  
               else if(next.toUpperCase().equals("WHITE") || next.equals("5") || next.equals("[5]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Ice";
                  addResistance("Ice");
                  RPG_Optional_Race_Features.breathWeaponSaveDex = false;
               }
               else {
                  if(next.equals("Q")) {
                     System.exit(0);
                  }
                  typeChosen = false;
                  System.out.println("Color not recognized!");
                  System.out.println("Choose your scale color!\n[1] Red\n[2] Green\n[3] Blue\n[4] Black\n[5] White");
               }
            } else {
               typeChosen = true;
               if(next.toUpperCase().equals("GOLD") || next.equals("1") || next.equals("[1]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Fire";
                  addResistance("Fire");
               }
               else if(next.toUpperCase().equals("SILVER") || next.equals("2") || next.equals("[2]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Ice";
                  addResistance("Ice");
                  RPG_Optional_Race_Features.breathWeaponSaveDex = false;
               }
               else if(next.toUpperCase().equals("BRONZE") || next.equals("3") || next.equals("[3]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Lightning";
                  addResistance("Lightning");
               }
               else if(next.toUpperCase().equals("BRASS") || next.equals("4") || next.equals("[4]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Fire";
                  addResistance("Fire");
               }  
               else if(next.toUpperCase().equals("COPPER") || next.equals("5") || next.equals("[5]")){
                  RPG_Optional_Race_Features.breathWeaponType = "Acid";
                  addResistance("Acid");
               }
               else {
                  if(next.equals("Q")) {
                     System.exit(0);
                  }
                  typeChosen = false;
                  System.out.println("Metal not recognized!");
                  System.out.println("Choose your scale metal!\n[1] Gold\n[2] Silver\n[3] Bronze\n[4] Brass\n[5] Copper");
               }
            }
         }
         next = input.nextLine();
      }
      if(RPG_Optional_Race_Features.breathWeaponSaveDex){
         addAction(new RPG_SaveAttack("Dragon Breath",RPG_Optional_Race_Features.breathWeaponType,RPG_Optional_Race_Features.breathWeaponDice,(8+getProficiencyBonus()+conModifier()),1));
      } else {
         addAction(new RPG_SaveAttack("Dragon Breath",RPG_Optional_Race_Features.breathWeaponType,RPG_Optional_Race_Features.breathWeaponDice,(8+getProficiencyBonus()+conModifier()),2));
      }
      return;
   }
   public boolean isBreathUsed(){
      return RPG_Optional_Race_Features.breathWeaponUsed;
   }
   public void useBreath(){
      RPG_Optional_Race_Features.breathWeaponUsed = true;
   }
   
   // Hide action related things
   public boolean isHidden(){
      return hidden;
   }
   public void hide(){
      hidden = true;
   }
   public void unhide(){
      hidden = false;
   }
   
   public void takeDamage(int damage){ // damage override to account for half-orcs being epic
      if(getCurrentHP() - damage <= 0 && RPG_Optional_Race_Features.relentless && !RPG_Optional_Race_Features.relentlessUsed){
         System.out.println(getName() + " did not succumb!");
         RPG_Optional_Race_Features.relentlessUsed = true;
         setCurrentHP(1);
      } else {
         setCurrentHP(getCurrentHP() - damage);
      }
      if(getHP() <= 0){ // player loss quits game
         System.out.println("You died!");
         System.out.println("GAME OVER");
         System.exit(0);
      }
   }
   
   public boolean isPacifist(){ // has the player killed NO enemies?
      return this.pacifist;
   }
   
   public void sin(){ // called if an enemy is killed. sets the pacifist field to false
      this.pacifist = false;
   }
   
   public void rechargeRacialAbilities(){ // called after battle. recharges the lucky, relentless, and breath weapon features.
      // recharge relentless
      RPG_Optional_Race_Features.relentlessUsed = false;
      // recharge lucky
      RPG_Optional_Race_Features.luckyUsed = false;
      // recharge breath weapon
      RPG_Optional_Race_Features.breathWeaponUsed = false;
   }
   
   // prof bonus
   public int getProficiencyBonus(){
      int profBonus = 2;
      if(level > 4){
         if(level < 9){
            profBonus = 3;
         } else if(level < 13){
            profBonus = 4;
         } else if(level < 17){
            profBonus = 5;
         } else {
            profBonus = 6;
         }
      }
      return profBonus;
   }
   
   // saving throw overrides
    public int strSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[0]);
      if(pcClass.getPrimaryStat() == 0 || pcClass.getSecondaryStat() == 0){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   public int dexSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[1]);
      if(pcClass.getPrimaryStat() == 1 || pcClass.getSecondaryStat() == 1){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   public int conSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[2]);
      if(pcClass.getPrimaryStat() == 2 || pcClass.getSecondaryStat() == 2){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   public int intSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[3]);
      if(pcClass.getPrimaryStat() == 3 || pcClass.getSecondaryStat() == 3){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   public int wisSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[4]);
      if(pcClass.getPrimaryStat() == 4 || pcClass.getSecondaryStat() == 4){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   public int chaSave(){
      int saveRoll = RPG_Dice.XdY(1,20);
      int saveMod = RPG_Dice.getModifier(stats[5]);
      if(pcClass.getPrimaryStat() == 5 || pcClass.getSecondaryStat() == 5){
         saveMod += getProficiencyBonus();
      }
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave(true) + saveMod;
      }
      return saveRoll + saveMod;
   }
   
   // save success qualifier overrides
   private int luckySave(boolean distinguisher){ // distinguisher is only there to ensure overloading works
      System.out.println("Your inexplicable luck kicked in!");
      RPG_Optional_Race_Features.luckyUsed = true;
      return 20;
   }
   private boolean luckySave(){
      System.out.println("Your inexplicable luck kicked in!");
      RPG_Optional_Race_Features.luckyUsed = true;
      return true;
   }
   public boolean strSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = strSave();
      return (saveRoll >= DC);
   }
   public boolean dexSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = dexSave();
      return (saveRoll >= DC);
   }
   public boolean conSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = conSave();
      return (saveRoll >= DC);
   }
   public boolean intSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = intSave();
      return (saveRoll >= DC);
   }
   public boolean wisSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = wisSave();
      return (saveRoll >= DC);
   }
   public boolean chaSave(int DC){
      if(RPG_Optional_Race_Features.lucky && !RPG_Optional_Race_Features.luckyUsed){ // lucky override
         return luckySave();
      }
      int saveRoll = chaSave();
      return (saveRoll >= DC);
   }

         
   // tostring
   private String arrayToString(String[] arr){
      String output = "{";
      for(String s : arr){
         output += s + ", ";
      }
      if(output.length() > 2){
         output = output.substring(0,output.length()-2);
      }
      return output + "}"; // cutoff last comma 
   }
   private String arrayToString(int[] arr){
      String output = "{";
      for(int s : arr){
         output += s + ", ";
      }
      if(output.length() > 2){
         output = output.substring(0,output.length()-2);
      }
      return output+"}"; // cutoff last comma 
   }
   public String toString(){
      String output = super.getName() + "\nHP: " + super.getCurrentHP() + "/"+ super.getMaxHP() + "\nLevel " + level + " " + pcRace.getName() + " " + pcClass.getName() + "\n";
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
/**
@author Lucinder
@file RPG_Class.java
A class to store D&D-style RPG Classes
Includes the following fields: name, hit die size, feature list, proficiencies (separate arrays for weapon, armor, tool, skill, and save profs.), and starting equipment
**/
public class RPG_Class{
   public static class ClassFeature extends RPG_Race.RaceFeature{ // nested class for class features using racefeature superclass format
      private int featureLevel = 1; // unlock level for feature - default 1
      public ClassFeature(){
         super();
      }
      public ClassFeature(String name, String description){
         super(name, description);
      }
      public ClassFeature(String name, String description, int level){
         super(name, description);
         featureLevel = level;
      }
      // getters and setters for classfeature class
      public int getUnlockLevel(){ return featureLevel; }
      public void setUnlockLevel(int newLevel){ featureLevel = newLevel; }
   }
   
   // ASI Container
   public static final ClassFeature ABILITY_SCORE_IMPROVEMENT = new ClassFeature(
      "Ability Score Improvement",
      "You can increase one ability score of your choice by 2, or you can increase two ability scores of your choice by 1. You can\'t increase an ability score above 20 using this feature."
   );
   
   private String name = "Placeholder Class"; // class name
   private int hitDie = 8; // hit die size (i.e. d6, d8, d10, d12)
   private int[] asiLevels = new int[]{4,8,12,16,19}; // default ASI unlock levels
   private int primaryStat = 0; // most important stat. default str
   private int secondaryStat = 2; // second most important stat. default con
   private int tertiaryStat = 4; // third most important stat. default wis 
   private ClassFeature[] features = new ClassFeature[0]; // all class features
   private RPG_Item[] equipment = new RPG_Item[0]; // starting inventory for class
   
   // constructors
   public RPG_Class(){}
   public RPG_Class(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, ClassFeature[] features, RPG_Item[] startingEquipment){
      this.name = name;
      this.hitDie = hitDie;
      if(priorityStats.length > 2){ // keep defaults if priority array is unfinished or empty
         this.primaryStat = priorityStats[0];
         this.secondaryStat = priorityStats[1];
         this.tertiaryStat = priorityStats[2];
      };
      this.asiLevels = abilityScoreImprovementLevels;
      this.features = features;
      this.equipment = startingEquipment;
   }
   
   // getters and setters
   public String getName(){ return name; }
   public int getHitDie(){ return hitDie; }
   public int[] getASILevels(){ return asiLevels; }
   public int[] getPriorityStats(){ return new int[]{primaryStat, secondaryStat, tertiaryStat}; }
   public int getPrimaryStat(){ return primaryStat; }
   public int getSecondaryStat(){ return secondaryStat; }
   public int getTertiaryStat(){ return tertiaryStat; }
   public boolean isASILevel(int level){ // does this class gain an ASI at a given level? (used for levelups)
      for(int i: asiLevels){
         if(i == level){
            return true;
         }
      }
      return false;
   }
   public ClassFeature[] getFeatures(){ return features; }
   public ClassFeature getFeature(String name){ // find a feature by name
      for(ClassFeature f: features){
         if(f.getName().equals(name)){
            return f;
         }
      }
      return null;
   }
   public boolean hasFeature(String name){ // check if class has a certain feature
      for(ClassFeature f: features){
         if(f.getName().equals(name)){
            return true;
         }
      }
      return false;
   }
   public RPG_Item[] getStartingEquipment(){ return equipment; }
   public RPG_Item getItem(String name){ // find an item by name
      for(RPG_Item i: equipment){
         if(i.getName().equals(name)){
            return i;
         }
      }
      return null;
   }
   public boolean hasItem(String name){ // check if class starts with a certain item
      for(RPG_Item i: equipment){
         if(i.getName().equals(name)){
            return true;
         }
      }
      return false;
   }
   // tostring
   private String arrayToString(String[] arr){
      String output = "";
      for(String s : arr){
         output += s + ", ";
      }
      if(output.length() > 2){
         output.substring(0,output.length()-2);
      }
      return output; // cutoff last comma 
   }
   public String toString(){
      String output = name;
      output += "\n\nHit Dice: 1d" + hitDie;
      output += "\nHit Points at 1st Level: " + hitDie + " + your Constitution modifier";
      output += "\nHit Points at Higher Levels: " + "1d" + hitDie + " + your Constitution modifier per " + name + " level after 1st";
      output += "\n\nFEATURES";
      for(ClassFeature f : features){
         output += "\n"+f.toString();
      }
      output += "\n\nSTARTING EQUIPMENT";
      for(RPG_Item i : equipment){
         output += "\n"+i.getName();
      }
      /** COLORIZED VERSION FOR CONSOLES THAT SUPPORT ANSI COLOR CODES
      COLORING FORMAT:
      NAME = RED
      SECTION HEADINGS = GREEN
      FIELD DESCRIPTORS = WHITE
      FIELD VALUES = YELLOW
      
      String output = ConsoleColors.RED + name;
      output += ConsoleColors.WHITE + "\n\nHit Dice:" + ConsoleColors.YELLOW +" 1d" + hitDie;
      output += ConsoleColors.WHITE + "\nHit Points at 1st Level: " + ConsoleColors.YELLOW + hitDie + " + your Constitution modifier";
      output += ConsoleColors.WHITE + "\nHit Points at Higher Levels: " + ConsoleColors.YELLOW + "1d" + hitDie + " + your Constitution modifier per " + name + " level after 1st";
      output += ConsoleColors.GREEN + "\n\nPROFICIENCIES";
      output += ConsoleColors.WHITE + "\nArmor: " + ConsoleColors.YELLOW;
      if(profArmors.length > 0){
         output += arrayToString(profArmors);
      } else {
         output += "None";
      }
      output += ConsoleColors.WHITE + "\nWeapons: " + ConsoleColors.YELLOW;
      if(profWeapons.length > 0){
         output += arrayToString(profWeapons);
      } else {
         output += "None";
      }
      output += ConsoleColors.WHITE + "\nTools: " + ConsoleColors.YELLOW;
      if(profTools.length > 0){
         output += arrayToString(profTools);
      } else {
         output += "None";
      }
      output += ConsoleColors.WHITE + "\nSkills: " + ConsoleColors.YELLOW + "Choose " + profSkillCount + " from " + arrayToString(profSkills);
      output += ConsoleColors.GREEN + "\n\nFEATURES";
      for(ClassFeature f : features){
         output += "\n"+f.toString();
      }
      output += ConsoleColors.GREEN + "\n\nSTARTING EQUIPMENT" + ConsoleColors.YELLOW;
      for(DD_Item i : equipment){
         output += "\n"+i.getName();
      }
      output += ConsoleColors.RESET; // reset active color
      **/
      return output;
   }
}
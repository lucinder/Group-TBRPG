/**
@author Lucinder
@file RPG_Class.java
A class to store D&D-style RPG Classes
Includes the following fields: name, hit die size, feature list, proficiencies (separate arrays for weapon, armor, tool, skill, and save profs.), and starting equipment
**/
public class RPG_Class{
   private String name = "Placeholder Class"; // class name
   private int hitDie = 8; // hit die size (i.e. d6, d8, d10, d12)
   private int[] asiLevels = new int[]{4,8,12,16,19}; // default ASI unlock levels
   private int primaryStat = 0; // most important stat. default str
   private int secondaryStat = 2; // second most important stat. default con
   private int tertiaryStat = 4; // third most important stat. default wis
   private RPG_Item[] equipment = new RPG_Item[0]; // starting inventory for class
   
   // constructors
   public RPG_Class(){}
   public RPG_Class(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment){
      this.name = name;
      this.hitDie = hitDie;
      if(priorityStats.length > 2){ // keep defaults if priority array is unfinished or empty
         this.primaryStat = priorityStats[0];
         this.secondaryStat = priorityStats[1];
         this.tertiaryStat = priorityStats[2];
      };
      this.asiLevels = abilityScoreImprovementLevels;
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
      output += "\n\nSTARTING EQUIPMENT";
      for(RPG_Item i : equipment){
         output += "\n"+i.getName();
      }
      return output;
   }
}
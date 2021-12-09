/**
@author Lucinder
@file RPG_Class.java
A class to store D&D-style Races
Includes the following fields: name, racial ability score improvements, and feature list
**/
import java.io.IOException;
import java.util.Scanner;
public class RPG_Race{
   public static class RaceFeature{
      private String featureName; // feature name
      private String featureDesc; // feature description
      public RaceFeature(){
         featureName = "Placeholder Feature";
         featureDesc = "Placeholder Description";
      }
      public RaceFeature(String name, String description){
         featureName = name;
         featureDesc = description;
      }
      // getters and setters for feature class
      public String getName(){ return featureName; }
      public String getDescription(){ return featureDesc; }
      public void setName(String newName){ featureName = newName; }
      public void setDescription(String newDescription){ featureDesc = newDescription; }
      public String toString(){
         return featureName + "\n" + featureDesc;
      }
   }
   
   private String name;
   private int[] asi; // ability score improvement distribution
   // ASI FORMAT: STR, DEX, CON, INT, WIS, CHA
   private RaceFeature[] features;
   
   public RPG_Race(){
      this.name = "Placeholder";
      asi = new int[]{0,0,0,0,0,0}; // no stat bonuses by default
      features = new RaceFeature[]{};
   }
   public RPG_Race(String name, int[] abilityScoreImprovements, RaceFeature[] racialFeatures){
      this.name = name;
      asi = abilityScoreImprovements;
      features = racialFeatures;
         
   }
   // getters and setters
   public String getName(){ return name; }
   public int[] getASI(){ return asi; }
   public RaceFeature[] getFeatures(){ return features; }
   public RaceFeature getFeature(String name){ // find a feature by name
      for(RaceFeature f: features){
         if(f.getName().equals(name)){
            return f;
         }
      }
      return null;
   }
   public boolean hasFeature(String name){ // check if race has a certain feature
      for(RaceFeature f: features){
         if(f.getName().equals(name)){
            return true;
         }
      }
      return false;
   }
   private String arrayToString(String[] arr){
      String output = "";
      for(String s : arr){
         output += s + ", ";
      }
      return output.substring(0,output.length()-2); // cutoff last comma 
   }
   private String arrayToString(int[] arr){
      String output = "";
      for(int i : arr){
         output += i + ", ";
      }
      return output.substring(0,output.length()-2); // cutoff last comma 
   }
   // toString
   public String toString(){
      String output = name += "\n";
      output += "Ability Scores: ";
      for(int i = 0; i < asi.length; i++){
         if(asi[i] > 0){
            if(i == 0){
               output += "Str +";
            } else if(i == 1){
               output += "Dex +";
            } else if(i == 2){
               output += "Con +";
            } else if(i == 3){
               output += "Int +";
            } else if(i == 4){
               output += "Wis +";
            } else if(i == 5){
               output += "Cha +";
            } else {
               output += "Any +";
            }
            output += asi[i] + " ";
         } else if(asi[i] < 0){
            if(i == 0){
               output += "Str ";
            } else if(i == 1){
               output += "Dex ";
            } else if(i == 2){
               output += "Con ";
            } else if(i == 3){
               output += "Int ";
            } else if(i == 4){
               output += "Wis ";
            } else if(i == 5){
               output += "Cha ";
            } else {
               output += "Any ";
            }
            output += asi[i] + " ";
         }
      }
      output += "\n\nFEATURES:";
      for(RaceFeature r : features){
         output += "\n" + r;
      }
      return output;
   }
   public int[] applyASI(int[] baseStats) throws IOException{
      int[] newStats = baseStats;
      if(baseStats.length < asi.length){ // races with customizable asi allocations
         for(int i = 0; i < baseStats.length; i++){
            newStats[i] += asi[i];
         }
         Scanner input = new Scanner(System.in);
         for(int i = baseStats.length; i < asi.length; i++){
            // prompt user to choose which stat to allocate
            System.out.println("Your race lets you allocate a " + asi[i] + " to any stat.");
            System.out.println("Allocate " + asi[i] + " to which stat?");
            System.out.println("[0] Strength\n[1] Dexterity\n[2] Contitution\n[3] Intelligence\n[4] Wisdom\n[5] Charisma");
            char choice = input.nextLine().charAt(0);
            int stat = Character.getNumericValue(choice);
            if(stat >= baseStats.length || (newStats[stat]+asi[i]) > 20){
               System.out.println("Can't allocate!");
               i--; // keep for loop going until an allocation is made
            } else {
               newStats[stat] += asi[i];
            }
         }
      } else {
         for(int i = 0; i < asi.length; i++){
            newStats[i] += asi[i];
         }
      }
      for(int i = 0; i < newStats.length; i++){
         if(newStats[i] > 20){
            newStats[i] = 20; // cap stats at standard 20 limit
         }
      }
      return newStats;
   }
}
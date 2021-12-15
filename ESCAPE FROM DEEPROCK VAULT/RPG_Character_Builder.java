/**
@author Lucinder
@file RPG_Character_Builder.java
Tester file for various aspects of the character builder incl. classbuilding, racebuilding, stat array generation, attacks, and saving throws
**/
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.*;

public class RPG_Character_Builder{
   public static RPG_Classes_List CLASS_LIST = new RPG_Classes_List();
   public static RPG_Races_List RACE_LIST = new RPG_Races_List();
   static Scanner input = new Scanner(System.in);
   private static RPG_Player player;
   public static RPG_Player buildCharacter() {
      try{
         String raceOptions = "\nAvailable races:\n Human (all +1)\n Elf (dex +2, any +1)\n Dwarf (con +2, any +1)\n Half-Orc (str +2, con +1)\n Goblin (dex +2, con +1)\n Gnome (int +2, any +1)\n Tiefling (cha +2, int +1)\n Dragonborn (str +2, cha +1)\n Halfling (dex +2, any +1)";
         String classOptions = "\nAvailable classes:\n Artificer\n Barbarian\n Bard\n Cleric\n Druid\n Fighter\n Monk\n Paladin\n Ranger\n Rogue\n Sorcerer\n Warlock\n Wizard";
         System.out.println("Enter \'Q\' to quit at any time!");
         boolean namePicked = false;
         boolean classPicked = false;
         boolean racePicked = false;
         RPG_Race race = new RPG_Race();
         RPG_Class clss = new RPG_Class();
         String name = "";
         System.out.print("Enter your character's name: ");
         String next = input.nextLine();
         while(!next.toUpperCase().equals("Q")){
            if(!namePicked){
               name = next;
               namePicked = true;
               System.out.println("\nChoose a race!");
               System.out.println(raceOptions);
            }
            else if(!racePicked){
               race = RACE_LIST.BASIC_RACES.get(next.toUpperCase()); // get race
               if(race != null){
                  racePicked = true;
                  System.out.println("\nChoose a class!");
                  System.out.println(classOptions);
               } else {
                  System.out.println("\nNo valid race match found!");
                  System.out.println("\nChoose a race!");
                  System.out.println(raceOptions);
               }
            } else if(!classPicked){
               clss = CLASS_LIST.CLASSES.get(next.toUpperCase());
               if(clss != null){
                  classPicked = true;
                  System.out.println("\nGenerating character!");
                  break;
               } else {
                  System.out.println("\nNo valid class match found!");
                  System.out.println("\nChoose a class!");
                  System.out.println(classOptions);
               }
            }
            next = input.nextLine();
            if(next.toUpperCase().equals("Q")){ System.exit(0); }
         }
         player = new RPG_Player(name, race, clss); // initialize new player
         System.out.println(player);
         return player;
      } catch (IOException e){
         e.printStackTrace();
         return new RPG_Player();
      }
   }
}
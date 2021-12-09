/**
@author Lucinder
@file RPG_Character_Tester.java
Tester file for various aspects of the character builder incl. classbuilding, racebuilding, stat array generation, attacks, and saving throws
**/
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.*;

public class RPG_Character_Tester{
   public static RPG_Classes_List CLASS_LIST = new RPG_Classes_List();
   public static RPG_Races_List RACE_LIST = new RPG_Races_List();
   static Scanner input = new Scanner(System.in);
   private static RPG_Player player;
   public static void main(String[] args) throws IOException, InterruptedException, Exception{
      System.out.println("Enter \'Q\' to quit at any time!");
      boolean namePicked = false;
      boolean classPicked = false;
      boolean racePicked = false;
      RPG_Race race = new RPG_Race();
      RPG_Class clss = new RPG_Class();
      String name = "";
      System.out.print("Enter your character's name: ");
      String next = input.nextLine();
      while(!next.equals("Q")){
         if(!namePicked){
            name = next;
            namePicked = true;
            System.out.println("\nChoose a race!");
         }
         else if(!racePicked){
            race = RACE_LIST.BASIC_RACES.get(next.toUpperCase()); // get race
            if(race != null){
               racePicked = true;
               System.out.println("\nChoose a class!");
            } else {
               System.out.println("\nNo valid race match found!");
               System.out.println("\nChoose a race!");
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
            }
         }
         next = input.nextLine();
         if(next.equals("Q")){ System.exit(0); }
      }
      player = new RPG_Player(name, race, clss); // initialize new player
      System.out.println(player);
      
      testAPI();
   }
   public static void testAPI() throws InterruptedException, Exception {
      String cmdPrompt = "What will you do?\n[1] Test saving throws\n[2] Test weapon attacks\n[3] Print inventory\n[4] Show action list";
      String next = input.nextLine();
      while(!next.equals("Q")){
         if(next.equals("1")){
            rollSaves();
         } else if(next.equals("2")){
            rollAttacks();
         } else if(next.equals("3")){
            player.printInventory();
         } else if(next.equals("4")){
            player.printActions();
         }
         System.out.println(cmdPrompt);
         next = input.nextLine();
         if(next.equals("Q")){ System.exit(0); }
      }
   }
   public static void rollSaves(){
      System.out.println("Enter the DC for the saving throws: ");
      int DC = input.nextInt();
      System.out.println("The DC for the following rolls is " + DC);
      int[] saves = {player.strSave(), player.dexSave(), player.conSave(), player.intSave(), player.wisSave(), player.chaSave()};
      System.out.println(player.getName() + " makes a Strength saving throw! Result: " +  saves[0] + ". Success? " + player.save(saves[0], DC));
      System.out.println(player.getName() + " makes a Dexterity saving throw! Result: " +  saves[1] + ". Success? " + player.save(saves[1], DC));
      System.out.println(player.getName() + " makes a Constitution saving throw! Result: " +  saves[2] + ". Success? " + player.save(saves[2], DC));
      System.out.println(player.getName() + " makes an Intelligence saving throw! Result: " +  saves[3] + ". Success? " + player.save(saves[3], DC));
      System.out.println(player.getName() + " makes a Wisdom saving throw! Result: " +  saves[4] + ". Success? " + player.save(saves[4], DC));
      System.out.println(player.getName() + " makes a Charisma saving throw! Result: " +  saves[5] + ". Success? " + player.save(saves[5], DC));
   }
   
   public static void rollAttacks() throws Exception{
      System.out.println("Enter the AC for the attack rolls: ");
      int AC = input.nextInt();
      System.out.println("The AC for the following rolls is " + AC);
      for(RPG_Action a : player.getActions()){
         a.act(player, new RPG_Enemy());
      }
   }
}
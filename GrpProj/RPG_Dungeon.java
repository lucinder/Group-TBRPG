/**
@author Lucinder
@file RPG_Dungeon.java
Container file for dungeons (room sets).
**/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class RPG_Dungeon{
   // private static RPG_Room[][] DUNGEON_ARRAY;
   private static RPG_Room DUNGEON_ROOM_HEAD;
   private static RPG_Room DUNGEON_ROOM_CURRENT;
   public static final int CR_DIVISION = 5; // how many rooms should an interval of 1 CR be (for enemy generation)?
   private RPG_Player player;
   
   public RPG_Dungeon(RPG_Room head, RPG_Player p) throws IOException{
      player = p;
      DUNGEON_ROOM_HEAD = head;
      DUNGEON_ROOM_CURRENT = head;
      mainLoop();
   }
   
   public void mainLoop() throws IOException{
      Scanner input = new Scanner(System.in);
      System.out.println("What will you do?");
      String[] action = input.nextLine().split(" "); // split the input into a series of words
      if(contains(action, "move") || contains(action,"go") || contains(action,"proceed")){ // CONTAINS METHOD IS NOT CASE SENSITIVE
         if(contains(action, "n") || contains(action,"north") || contains(action,"up")){
            move("N");
         } else if (contains(action, "e") || contains(action,"east") || contains(action,"right")){
            move("E");
         } else if (contains(action, "s") || contains(action,"south") || contains(action,"down")){
            move("S");
         } else if (contains(action, "w") || contains(action,"west") || contains(action,"left")){
            move("W");
         } else {
            System.out.println("No valid direction detected!");
         }
      } else {
         System.out.println("No valid action detected!");
      }
      mainLoop(); // loop infinitely unless something in the process of loading rooms/fighting/etc. breaks this loop
   }
   private boolean contains(String[] array, String key){ // helper to check if a string array contains a given string- not case sensitive
      for(String s : array){
         if(s.toLowerCase().equals(key.toLowerCase())){ return true; }
      }
      return false;
   }
   
   public void loadRoomContents() throws IOException{
      for(RPG_Interactable i : DUNGEON_ROOM_CURRENT.getObjects()){
         i.interactionEvent();
         if(i instanceof RPG_Enemy){
            RPG_Enemy en = (RPG_Enemy)i;
            if(en.getHP() > 0){
               int[] preCombatStats = player.getStats();
               combat(player, en);
               player.setStats(preCombatStats); // revert any temporary stat changes
            }
         }
      }
   }
   
   // move to an adjacent, open square
   public void move(String dir) throws IOException{
      if(dir.equals("N")){
         System.out.println(player.getName() + " proceeded to the north.");
         if(DUNGEON_ROOM_CURRENT.hasUp()){
            if(DUNGEON_ROOM_CURRENT.isExit("N")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getUp();
               System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               System.out.println("But the door was locked!");
            }
         } else {
            System.out.println("But there was no door leading north!");
         }
      }
      else if(dir.equals("E")){
         System.out.println(player.getName() + " proceeded to the east.");
         if(DUNGEON_ROOM_CURRENT.hasRight()){
            if(DUNGEON_ROOM_CURRENT.isExit("E")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getRight();
               System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               System.out.println("But the door was locked!");
            }
         } else {
            System.out.println("But there was no door leading east!");
         }
      }
      else if(dir.equals("S")){
         System.out.println(player.getName() + " proceeded to the south.");
         if(DUNGEON_ROOM_CURRENT.hasDown()){
            if(DUNGEON_ROOM_CURRENT.isExit("S")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getDown();
               System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               System.out.println("But the door was locked!");
            }
         } else {
            System.out.println("But there was no door leading south!");
         }
      } else if(dir.equals("W")){
         System.out.println(player.getName() + " proceeded to the west.");
         if(DUNGEON_ROOM_CURRENT.hasLeft()){
            if(DUNGEON_ROOM_CURRENT.isExit("W")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getLeft();
               System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               System.out.println("But the door was locked!");
            }
         } else {
            System.out.println("But there was no door leading west!");
         }
      }
   }
  public static void combat(RPG_Player player, RPG_Enemy enemy) throws IOException{
      int playerInit = player.dexSave();
      int enInit = enemy.dexSave();
      boolean playerFirst = playerInit >= enInit;
      int turnNo = 0;
      while(player.getHP() > 0 && enemy.getHP() > 0 && !enemy.isPacified()){
         if((turnNo%2 == 0 && playerFirst) || (turnNo%2 != 0 && !playerFirst)){ // player's turn
            ArrayList<RPG_Character.RPG_Action> actions = player.getActions();
            Scanner input = new Scanner(System.in);
            int selection = -1;
            boolean actionSelected = false;
            while(!actionSelected){
               System.out.println("["+enemy.getName()+": HP " + enemy.getHP() + "/" + enemy.getMaxHP() + "] vs [" + player.getName()+ ": HP " + player.getHP() + "/" + player.getMaxHP() + "]");
               System.out.println("What will " + player.getName() + " do?"); // prompt user for action
               int i = 0;
               for(RPG_Character.RPG_Action a : actions){
                  System.out.println(" [" + i+"] " + a.getName());
                  i++;
               }
               try{
                  actionSelected = true;
                  selection = Integer.parseInt(input.nextLine());
                  if(selection >= actions.size()){
                     throw new Exception();
                  }
               } catch(Exception e){ // should catch all non-int input
                  System.out.println("Invalid selection!");
                  actionSelected = false; // deselect
                  continue; // keep looping until a valid selection is made 
               }
            }
            RPG_Character.RPG_Action a = actions.get(selection);
            a.act(player, enemy);
         } else { // enemy's turn
            enemy.doAction(player);
         }
         turnNo++;
      }
      if(player.getHP() <= 0){ // player loss quits game
         System.out.println("You died!");
         System.out.println("GAME OVER");
         System.exit(0);
      } else {
         System.out.println("YOU WON!");
         enemy.die(); // sets the enemy interaction to a corpse
      }
   }
}
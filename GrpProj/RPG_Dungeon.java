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
      // System.out.println("TEST- xp for CR 4 creature = " + XPperCR.get(4));
      // System.out.println("Test- xp for a CR 1/8 creature = " + XPperCR.get(0.125));
      mainLoop();
   }
   
   public void mainLoop() throws IOException{
      if(DUNGEON_ROOM_CURRENT.getIfFinalRoom()){ // have we finished the dungeon?
         ending();
         System.exit(0);
      }
      Scanner input = new Scanner(System.in);
      printRoomDesc();
      System.out.println("What will you do?");
      String[] action = input.nextLine().split(" "); // split the input into a series of words
      if(action.equals("Q")){ System.exit(0); } // QUIT option
      if(contains(action,"inventory")){ // check inventory
         player.printInventory();
      } else if(contains(action,"treasure") || contains(action,"open") || contains(action,"chest")){
         boolean openSuccessful = false;
         for(RPG_Interactable i : DUNGEON_ROOM_CURRENT.getObjects()){
            if(i instanceof RPG_Treasure){
               RPG_Treasure tres = (RPG_Treasure)i;
               tres.interactionEvent(player);
               openSuccessful = true;
            }
         }
         if(!openSuccessful){
            System.out.println("You tried to open a treasure chest... but there were no chests to open!");
         }
      } else if(contains(action, "hp") || contains(action, "health")){ // check xp
         System.out.println("Your current HP is [" + player.getHP() + "/" + player.getMaxHP() + "].");
      } else if(contains(action, "gp") || contains(action, "gold") || contains(action, "money")){ // check xp
         System.out.println("You currently have " + player.getGP() + " GP.");
      } else if(contains(action, "xp")){ // check xp
         System.out.println("You currently have a total of " + player.getTotalXP() + " XP!");
      } else if(contains(action, "ac")) { // check armor class
         System.out.println("You currently have an Armor Class of " + player.getAC() + ".");
      } else if(contains(action, "disarm") || contains(action, "disable")){ // disarm a trap
         boolean disarmSuccessful = false;
         for(RPG_Interactable i : DUNGEON_ROOM_CURRENT.getObjects()){
            if(i instanceof RPG_Trap){ // trap found
               RPG_Trap trap = (RPG_Trap)i;
               trap.disable();
               disarmSuccessful = true;
            }
         }
         if(!disarmSuccessful) { // no traps found
            System.out.println("You tried to disarm a trap... but there were no traps to disarm!");
         }
      } else if(contains(action, "move") || contains(action,"go") || contains(action,"proceed")){ // CONTAINS METHOD IS NOT CASE SENSITIVE
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
      } else if(contains(action,"die")){
         player.die(); // funney :)
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
   private boolean contains(RPG_Interactable[] array, String key){
      for(RPG_Interactable s : array){
         if(s.getName().toLowerCase().equals(key.toLowerCase())){ return true; }
      }
      return false;
   }
   
   
   public void loadRoomContents() throws IOException{
      for(RPG_Interactable i : DUNGEON_ROOM_CURRENT.getObjects()){
         if(!(i instanceof RPG_Treasure)){
            i.interactionEvent();
         }
         if(i instanceof RPG_Trap){
            RPG_Trap trap = (RPG_Trap)i;
            trap.trigger(player); // triggers a trap attack. the trigger method itself handles disabled traps.
         }
         if(i instanceof RPG_Enemy){ // enemy detected?
            RPG_Enemy en = (RPG_Enemy)i;
            if(!en.isPacified() && en.getHP() > 0){ // engage battle if enemy is alive and angry
               int[] preCombatStats = player.getStats();
               combat(player, en);
               player.setStats(preCombatStats); // revert any temporary stat changes
            }
         }
      }
   }
   
   
   public void printRoomDesc(){
      boolean doorN = DUNGEON_ROOM_CURRENT.hasUp() && DUNGEON_ROOM_CURRENT.isExit("N");
      boolean doorE = DUNGEON_ROOM_CURRENT.hasRight() && DUNGEON_ROOM_CURRENT.isExit("E");
      boolean doorS = DUNGEON_ROOM_CURRENT.hasDown() && DUNGEON_ROOM_CURRENT.isExit("S");
      boolean doorW = DUNGEON_ROOM_CURRENT.hasLeft() && DUNGEON_ROOM_CURRENT.isExit("W");
      if(doorN){
         if(doorE){
            if(doorS){
               if(doorW){ // NESW
                  System.out.println("There is a door on each wall.");
               } else { // NES
                  System.out.println("There are doors to the north, east, and south.");
               }
            } else if(doorW){ // NEW
               System.out.println("There are doors to the north, east, and west.");
            } else { // NE
               System.out.println("There are doors to the north and east.");
            }
         } else if(doorS){ 
            if(doorW){ //NSW
               System.out.println("There are doors to the north, south, and east.");
            } else { // NS
               System.out.println("There are doors to the north and south.");
            }
         } else if(doorW){ // NW
            System.out.println("There are doors to the north and west.");
         } else { // N
            System.out.println("There is a door to the north.");
         }
      } else if (doorE){
         if(doorS){
            if(doorW){ // ESW
               System.out.println("There are doors to the east, south, and west.");
            } else{ // ES
               System.out.println("There are doors to the east and south.");
            }
         } else if (doorW){ // EW
            System.out.println("There are doors to the east and west.");
         } else { // E
            System.out.println("There is a door to the east.");
         }
      } else if (doorS){
         if(doorW){ // SW
            System.out.println("There are doors to the south and west.");
         } else { // S
            System.out.println("There is a door to the south.");
         }
      } else if (doorW){ // W
         System.out.println("There is a door to the west.");
      }
      if(DUNGEON_ROOM_CURRENT.getType().equals("Tres") || DUNGEON_ROOM_CURRENT.getType().equals("EnTres") || DUNGEON_ROOM_CURRENT.getType().equals("TrapTres") || DUNGEON_ROOM_CURRENT.getType().equals("EnTrapTres")){
         System.out.println("A treasure chest lies on the ground.");
      }
      System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
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
   
   
  public void combat(RPG_Player player, RPG_Enemy enemy) throws IOException{
      int playerInit = player.initiative(); // roll initiative for the player
      int enInit = enemy.initiative(); // roll initiative for the enemy
      boolean playerFirst = playerInit >= enInit; // compare initiative scores
      int turnNo = 0;
      boolean spareRange = false; // qualifier for the "spare range" of an enemy
      while(player.getHP() > 0 && enemy.getHP() > 0 && !enemy.isPacified()){
         if((turnNo%2 == 0 && playerFirst) || (turnNo%2 != 0 && !playerFirst)){ // player's turn
            ArrayList<RPG_Action> actions = player.getActions();
            Scanner input = new Scanner(System.in);
            int selection = -1;
            boolean actionSelected = false;
            ArrayList<RPG_Action> actable = new ArrayList<RPG_Action>(); // handles all the actions we CAN execute right now
            while(!actionSelected){
               System.out.println("["+enemy.getName()+": HP " + enemy.getHP() + "/" + enemy.getMaxHP() + "] vs [" + player.getName()+ ": HP " + player.getHP() + "/" + player.getMaxHP() + "]");
               System.out.println("What will " + player.getName() + " do?"); // prompt user for action
               actable = new ArrayList<RPG_Action>(); // clear actable list
               int i = 0;
               for(RPG_Action a : actions){
                  if(!(a.getName().equals("Dragon Breath") && player.isBreathUsed())){ // skip expended breath attacks
                     System.out.println(" [" + i+"] " + a.getName());
                     actable.add(a);
                     i++;
                  }
               }
               if(spareRange){
                  System.out.println(" [" + i + "] Spare the Enemy");
               }
               try{
                  actionSelected = true;
                  String inp = input.nextLine();
                  if(inp.equals("Q")){ System.exit(0); } // QUIT option
                  selection = Integer.parseInt(inp);
                  if((spareRange && selection > actions.size()) || (!spareRange && selection >= actions.size())){ // overflow handler
                     throw new Exception();
                  }
               } catch(Exception e){ // should catch all non-int input
                  System.out.println("Invalid selection!");
                  actionSelected = false; // deselect
                  continue; // keep looping until a valid selection is made 
               }
            }
            if(selection != actable.size()){ // any fight option selected
               RPG_Action a = actable.get(selection);
               if(contains(a.getName().split(" "), "Potion")){
                  a.act(player,player); // heal SELF
                  player.useItem(a.getName()); // remove expendable items after use
               } else {
                  a.act(player, enemy);
                  if(a.getName().equals("Dragon Breath")){
                     player.useBreath(); // expend per-fight breath weapon use
                  }
                  if(!a.getName().equals("Hide")){ // all attacks except for hiding reveal the player's location to the enemy
                     player.unhide();
                  }
               }
            } else { // spare selected
               enemy.pacify();
            }
            if(enemy.getHPPercent() <= 30.0){ // is the enemy at 30% or less HP?
               spareRange = true; // enemies at low HP can be spared
            }
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
         if(!enemy.isPacified()){
            enemy.die(); // sets the enemy interaction to a corpse
            player.sin(); // player is no longer a full pacifist
         }
         player.rechargeRacialAbilities(); // recharge any expended racial abilities after battle
         int toGain = enemy.getXP();
         System.out.println(player.getName() + " gained " + toGain + " XP.");
         player.gainXP(toGain);
      }
   }
   
   public void ending(){ // GAME ENDING
      System.out.println("As your final foe yields before you, you feel a cool breeze wash over your body.");
      System.out.println("The final door, leading to the surface, is ajar. Rays of sunlight flickers in from the other side.");
      System.out.println("Proceed?");
      // FINAL CHOICE
      Scanner input = new Scanner(System.in);
      String[] action = input.nextLine().split(" ");
      if(contains(action,"n") || contains(action, "no") || contains(action, "not")){
       // Stay in the dungeon
         System.out.println("Despite everything, you decide to stay behind.");
         if(player.isPacifist()){
            System.out.println("With the many friends you\'ve made down here, you live a peaceful life in the dungeon as its new champion.");
         } else {
            System.out.println("It\'s a lonely life down here, but you stake your claim as the new champion of the dungeon.");
         }
      }
      else if(contains(action, "y") || contains(action, "yes") || contains(action, "proceed")){
         // Leave the dungeon
         System.out.println("Pushing open the final door all the way, you step into the sunlight, ready to begin your life anew on the surface.");
         if(player.isPacifist()){
            System.out.println("Your friends from the dungeon call out to you, saying goodbye and cheering you on as you take on the world above!");
         }
      }
   }
}
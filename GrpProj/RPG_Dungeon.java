/**
@author Lucinder
@file RPG_Dungeon.java
Container file for dungeons (room sets).
**/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class RPG_Dungeon{
   private static final String TITLE = "ESCAPE FROM DEEPROCK VAULT";
   private static final String CREDITS = "Backend Programming - - - Savanna Wheeler\nFrontend Programming - - - Justin Seo\nLead Scriptwriter - - - Pilsung Kwok\nGame Testing - - - Abdalrahman Shaath";
   public static final int TEXTDELAY = 20;
   public static final int LINEDELAY = 100;
   // private static RPG_Room[][] DUNGEON_ARRAY;
   int[] preCombatStats;
   private static RPG_Room DUNGEON_ROOM_HEAD;
   private static RPG_Room DUNGEON_ROOM_CURRENT;
   public static final int CR_DIVISION = 5; // how many rooms should an interval of 1 CR be (for enemy generation)?
   private RPG_Player player;
   
   public RPG_Dungeon(RPG_Room head, RPG_Player p) throws IOException,InterruptedException,Exception{
      player = p;
      DUNGEON_ROOM_HEAD = head;
      DUNGEON_ROOM_CURRENT = head;
      // printStaggered("TEST- xp for CR 4 creature = " + XPperCR.get(4));
      // printStaggered("Test- xp for a CR 1/8 creature = " + XPperCR.get(0.125));
      mainLoop();
   }
   
   // INTRODUCTION SECTION
   public static void intro(){
      try{
         printStaggered("--- " + TITLE + " ---");
         printStaggered("The Kingdom of Veritas has been waging war against monsters for the past 500 years.");
         printStaggered("The war remained even, with heavy casualties on both sides, until Veritas\'s four sages summoned forth a hero.");
         printStaggered("The hero fought valiantly, reclaiming lost territories, and eventually sealed the Monster King along with its generals into an underground dungeon.");
         printStaggered("Just before the Monster King was sealed, it let out an almighty roar, declaring that it would return to take its revenge on Veritas.");
         printStaggered("Due to the threat within the now-created dungeon, an Adventurer\'s Guild was created, gathering all sorts of races and professions across the kingdom to attempt to clear the dungeon.");
         printStaggered(". . .");
         printStaggered("100 years later, signs of the Monster King\'s reemergence appeared.");
         printStaggered("Monsters began swarming and attacking outside of the dungeon!");
         printStaggered("Veritas once again fell into war, and looked for a new hero who could save them from their peril...");
         return;
      } catch (InterruptedException e){
         e.printStackTrace();
      }
   }
   public static void intro2(int levels, int levelLength){
      try{
         printStaggered("You wake up in what seems to be a large stone chamber of some kind.");
         printStaggered("The walls are rock-gray, candles dimly light the room, and the smells of sweat and grime permeate your nose.");
         printStaggered("As you gain your senses you notice a voice exclaiming,");
         printStaggered("\"The summoning worked! Welcome hero, I am the King of Veritas.\"");
         printStaggered("\"I am sorry to have called you here abruptly. However, my kingdom is in deep trouble.\"");
         printStaggered("\"The Monster King has recently awakened and is threatening to destroy our kingdom.\"");
         printStaggered("\"We need your help to defeat him.\"");
         printStaggered("\"Will you please accept our request?\"");
         printStaggered("Accept?\n [0] Yes\n [1] No");
         Scanner input = new Scanner(System.in);
         String selection = input.nextLine();
         while(!(selection.equals("0") || selection.equals("1"))){
            printStaggered("Invalid selection!");
            printStaggered("Accept?\n [0] Yes\n [1] No");
            selection = input.nextLine();
         }
         int select = Integer.parseInt(selection);
         if(select == 0){
            printStaggered("\"Thank you for accepting my request. We will give you supplies and lead you to the dungeon tomorrow.\"");
         } else {
            printStaggered("\"I am sorry hero, but we cannot allow you to decline.\"");
            printStaggered("The King motions to his servants and they start chanting in unison.");
            printStaggered("A magic circle appears around your arms, legs, and neck, binding you to the chamber floor.");
            printStaggered("The chanting finishes and a golden circle is tattooed onto your neck.");
            printStaggered("\"This tattoo is a magical slave contract. You will be forced to defeat the Monster King in the dungeon.\"");
         }
         printStaggered("The next day, the same four servants from before appear before you in your home.");
         printStaggered("\"Good morning hero. We are the sages which serve the Veritas King.\"");
         printStaggered("\"We will teleport you into the dungeon after providing you with supplies.\"");
         printStaggered("One sage gives you a bag of supplies.");
         printStaggered("\"Once in the dungeon, you will have to traverse " + (1+levelLength)*levels + " rooms leading eastward.\"");
         printStaggered("\"Within each room there may be enemies, traps, or even treasure.\"");
         printStaggered("\"In order to face the Monster King, you must navigate through " + levels + " levels.\"");
         printStaggered("\"Beware, for at the end of each level is a general that serves the Monster King.\"");
         printStaggered("\"You must defeat each general to reach the next level.\"");
         printStaggered("\"There is a kind shopkeep in the dungeon who lingers near the generals\' rooms- from his shop, you can continue obtaining supplies.\"");
         printStaggered("\"Good luck!\"");
         printStaggered("The room begins to tremble as a beam of white light hits your body.");
         printStaggered("Your surroundings begin to twist and warp until your vision turns black...");
         return;
      } catch (Exception e){
         e.printStackTrace();
      }
   }
   
   
   public static void printStaggered(String input) throws InterruptedException{
      printStaggered(input, TEXTDELAY);
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
   
   
   
   public void mainLoop() throws IOException,InterruptedException,Exception{
      if(DUNGEON_ROOM_CURRENT.getIfFinalRoom()){ // have we finished the dungeon?
         ending();
         System.exit(0);
      }
      Scanner input = new Scanner(System.in);
      printRoomDesc();
      printStaggered("What will you do?");
      String[] action = input.nextLine().split(" "); // split the input into a series of words
      if(action[0].toUpperCase().equals("Q")){ System.exit(0); } // QUIT option
      if(contains(action,"inventory")){ // check inventory
         player.printInventory();
      } else if (contains(action,"stats")) {
         System.out.println(player);
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
            printStaggered("You tried to open a treasure chest... but there were no chests to open!");
         }
      } else if(contains(action, "hp") || contains(action, "health")){ // check xp
         printStaggered("Your current HP is [" + player.getHP() + "/" + player.getMaxHP() + "].");
      } else if(contains(action, "gp") || contains(action, "gold") || contains(action, "money")){ // check xp
         printStaggered("You currently have " + player.getGP() + " GP.");
      } else if(contains(action, "xp")){ // check xp
         printStaggered("You currently have a total of " + player.getTotalXP() + " XP!");
      } else if(contains(action, "ac")) { // check armor class
         printStaggered("You currently have an Armor Class of " + player.getAC() + ".");
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
            printStaggered("You tried to disarm a trap... but there were no traps to disarm!");
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
            printStaggered("No valid direction detected!");
         }
      } else if(contains(action,"die")){
         player.die(); // funney :)
      } else {
         printStaggered("No valid action detected!");
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
   
   public void loadRoomContents() throws IOException,InterruptedException,Exception{
      for(RPG_Interactable i : DUNGEON_ROOM_CURRENT.getObjects()){
         i.interactionEvent();
         if(i instanceof RPG_Trap){
            RPG_Trap trap = (RPG_Trap)i;
            trap.trigger(player); // triggers a trap attack. the trigger method itself handles disabled traps.
         }
         if(i instanceof RPG_Enemy){ // enemy detected?
            // testing out the new combat system
            RPG_Enemy en = (RPG_Enemy)i;
            if(!(en.isPacified()) && en.getHP() > 0){
               RPG_Combat battle = new RPG_Combat(player);
               battle.add(en);
               battle.start();
            }
            /**
            if(!en.isPacified() && en.getHP() > 0){ // engage battle if enemy is alive and angry
               combat(player, en);
            }
            **/
         }
      }
   }
   
   
   public void printRoomDesc() throws InterruptedException{
      boolean doorN = DUNGEON_ROOM_CURRENT.hasUp() && DUNGEON_ROOM_CURRENT.isExit("N");
      boolean doorE = DUNGEON_ROOM_CURRENT.hasRight() && DUNGEON_ROOM_CURRENT.isExit("E");
      boolean doorS = DUNGEON_ROOM_CURRENT.hasDown() && DUNGEON_ROOM_CURRENT.isExit("S");
      boolean doorW = DUNGEON_ROOM_CURRENT.hasLeft() && DUNGEON_ROOM_CURRENT.isExit("W");
      if(doorN){
         if(doorE){
            if(doorS){
               if(doorW){ // NESW
                  printStaggered("There is a door on each wall.");
               } else { // NES
                  printStaggered("There are doors to the north, east, and south.");
               }
            } else if(doorW){ // NEW
               printStaggered("There are doors to the north, east, and west.");
            } else { // NE
               printStaggered("There are doors to the north and east.");
            }
         } else if(doorS){ 
            if(doorW){ //NSW
               printStaggered("There are doors to the north, south, and east.");
            } else { // NS
               printStaggered("There are doors to the north and south.");
            }
         } else if(doorW){ // NW
            printStaggered("There are doors to the north and west.");
         } else { // N
            printStaggered("There is a door to the north.");
         }
      } else if (doorE){
         if(doorS){
            if(doorW){ // ESW
               printStaggered("There are doors to the east, south, and west.");
            } else{ // ES
               printStaggered("There are doors to the east and south.");
            }
         } else if (doorW){ // EW
            printStaggered("There are doors to the east and west.");
         } else { // E
            printStaggered("There is a door to the east.");
         }
      } else if (doorS){
         if(doorW){ // SW
            printStaggered("There are doors to the south and west.");
         } else { // S
            printStaggered("There is a door to the south.");
         }
      } else if (doorW){ // W
         printStaggered("There is a door to the west.");
      }
      printStaggered(DUNGEON_ROOM_CURRENT.getDialogue());
   }
   
   
   // move to an adjacent, open square
   public void move(String dir) throws IOException,InterruptedException,Exception{
      if(dir.equals("N")){
         printStaggered(player.getName() + " proceeded to the north.");
         if(DUNGEON_ROOM_CURRENT.hasUp()){
            if(DUNGEON_ROOM_CURRENT.isExit("N")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getUp();
               printStaggered(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               printStaggered("But the door was locked!");
            }
         } else {
            printStaggered("But there was no door leading north!");
         }
      }
      else if(dir.equals("E")){
         printStaggered(player.getName() + " proceeded to the east.");
         if(DUNGEON_ROOM_CURRENT.hasRight()){
            if(DUNGEON_ROOM_CURRENT.isExit("E")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getRight();
               printStaggered(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               printStaggered("But the door was locked!");
            }
         } else {
            printStaggered("But there was no door leading east!");
         }
      }
      else if(dir.equals("S")){
         printStaggered(player.getName() + " proceeded to the south.");
         if(DUNGEON_ROOM_CURRENT.hasDown()){
            if(DUNGEON_ROOM_CURRENT.isExit("S")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getDown();
               printStaggered(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               printStaggered("But the door was locked!");
            }
         } else {
            printStaggered("But there was no door leading south!");
         }
      } else if(dir.equals("W")){
         printStaggered(player.getName() + " proceeded to the west.");
         if(DUNGEON_ROOM_CURRENT.hasLeft()){
            if(DUNGEON_ROOM_CURRENT.isExit("W")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getLeft();
               printStaggered(DUNGEON_ROOM_CURRENT.getDialogue());
               loadRoomContents();
            } else {
               printStaggered("But the door was locked!");
            }
         } else {
            printStaggered("But there was no door leading west!");
         }
      }
   }
   
   /** OLD COMBAT SYSTEM
  public void combat(RPG_Player player, RPG_Enemy enemy) throws IOException,InterruptedException{
      preCombatStats = player.getStats().clone(); // pass by value
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
               printStaggered("["+enemy.getName()+": HP " + enemy.getHP() + "/" + enemy.getMaxHP() + "] vs [" + player.getName()+ ": HP " + player.getHP() + "/" + player.getMaxHP() + "]");
               printStaggered("What will " + player.getName() + " do?"); // prompt user for action
               actable = new ArrayList<RPG_Action>(); // clear actable list
               int i = 0;
               for(RPG_Action a : actions){
                  if(!(a.getName().equals("Dragon Breath") && player.isBreathUsed())){ // skip expended breath attacks
                     printStaggered(" [" + i+"] " + a.getName());
                     actable.add(a);
                     i++;
                  }
               }
               if(spareRange){
                  printStaggered(" [" + i + "] Spare the Enemy");
               }
               try{
                  actionSelected = true;
                  String inp = input.nextLine();
                  if(inp.equals("Q")){ System.exit(0); } // QUIT option
                  selection = Integer.parseInt(inp);
                  if((spareRange && selection > actions.size()) || (!spareRange && selection >= actions.size())){ // overflow handler
                     throw new Exception();
                  }
               } catch(Exception e){ // should catch all non-int input except quit
                  printStaggered("Invalid selection!");
                  actionSelected = false; // deselect
                  continue; // keep looping until a valid selection is made 
               }
            }
            if(selection != actable.size()){ // any fight option selected
               RPG_Action a = actable.get(selection);
               if(contains(a.getName().split(" "), "Potion")){
                  a.act(player,player); // heal or buff SELF
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
            if(!enemy.isPacified() && enemy.getHPPercent() <= 30.0){ // is the enemy at 30% or less HP?
               printStaggered("The " + enemy.getName() + " is trembling with fear.");
               spareRange = true; // enemies at low HP can be spared
            }
         } else { // enemy's turn   
            enemy.doAction(player);
         }
         turnNo++;
      }
      if(player.getHP() <= 0){ // player loss quits game
         printStaggered("You died!");
         printStaggered("GAME OVER");
         System.exit(0);
      } else {
         printStaggered("YOU WON!");
         player.setStats(preCombatStats); // revert any temporary stat changes
         if(!enemy.isPacified()){
            enemy.die(); // sets the enemy interaction to a corpse
            player.sin(); // player is no longer a full pacifist
         }
         player.rechargeRacialAbilities(); // recharge any expended racial abilities after battle
         int toGain = enemy.getXP();
         printStaggered(player.getName() + " gained " + toGain + " XP.");
         player.gainXP(toGain);
      }
   }
   **/
   
   public void ending() throws InterruptedException{ // GAME ENDING
      printStaggered("As your final foe yields before you, you feel a cool breeze wash over your body.");
      printStaggered("The final door, leading to the surface, is ajar. Rays of sunlight flickers in from the other side.");
      printStaggered("Proceed?");
      // FINAL CHOICE
      Scanner input = new Scanner(System.in);
      String[] action = input.nextLine().split(" ");
      if(contains(action,"n") || contains(action, "no") || contains(action, "not")){
       // Stay in the dungeon
         printStaggered("Despite everything, you decide to stay behind.");
         if(player.isPacifist()){
            printStaggered("With the many friends you\'ve made down here, you live a peaceful life in the dungeon as its new champion.");
         } else {
            printStaggered("It\'s a lonely life down here, but you stake your claim as the new champion of the dungeon.");
         }
      }
      else if(contains(action, "y") || contains(action, "yes") || contains(action, "proceed")){
         // Leave the dungeon
         printStaggered("Pushing open the final door all the way, you step into the sunlight, ready to begin your life anew on the surface.");
         if(player.isPacifist()){
            printStaggered("Your friends from the dungeon call out to you, saying goodbye and cheering you on as you take on the world above!");
         }
      }
      printStaggered(TITLE); 
      printStaggered(CREDITS);
      printStaggered("Thanks for playing!");
   }
}
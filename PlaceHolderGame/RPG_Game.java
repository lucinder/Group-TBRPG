import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class RPG_Game{
   private static final String TITLE = "DEEPLOCK";
   private static final String CREDITS = "Programmers - - - Savanna Wheeler, Abdalrahman Shaath\nLead Scriptwriter - - - Pilsung Kwok";
   public static void start() throws IOException{

      int levels = 9;
      int levelLength = 4;
      // System.out.println("Dungeon of " + levels + " levels with " + levelLength + " main rooms per level generated:");
=======
      int levels = 5;
      int levelLength = 2;
      System.out.println("Dungeon of " + levels + " levels with " + levelLength + " main rooms per level generated:");

      RPG_DungeonBuilder newDungeon = new RPG_DungeonBuilder(levels,levelLength);
      RPG_Player player = RPG_Character_Builder.buildCharacter();
      RPG_Dungeon mainDungeon = new RPG_Dungeon(newDungeon.getHead(), player);
      System.out.println(TITLE); 
      System.out.println(CREDITS);
      System.out.println("Thanks for playing!");
   }
   public static void start(boolean debugMode) throws IOException{
      if(!debugMode){
         start(); // play game as normal
         return;
      } // otherwise, use debug mode selection
      Scanner input = new Scanner(System.in);
      System.out.println("How many levels?");
      int levels = Integer.parseInt(input.nextLine());
      System.out.println("How many rooms per level?");
      int levelLength = Integer.parseInt(input.nextLine());
      System.out.println("Dungeon difficulty?\n[0] Easy\n[1] Normal\n[2] Hard");
      int selection = Integer.parseInt(input.nextLine());
      RPG_DungeonBuilder newDungeon;
      if(selection == 0){ // build dungeon of specific difficulty
         newDungeon = new RPG_DungeonBuilder(levels,levelLength,"Easy");
      } else if (selection == 2){
         newDungeon = new RPG_DungeonBuilder(levels,levelLength,"Hard");
      } else {
         newDungeon = new RPG_DungeonBuilder(levels,levelLength);
      }
      RPG_Player player = RPG_Character_Builder.buildCharacter();
      RPG_Dungeon mainDungeon = new RPG_Dungeon(newDungeon.getHead(), player);
      System.out.println(TITLE); 
      System.out.println(CREDITS);
      System.out.println("Thanks for playing!");
   }
}
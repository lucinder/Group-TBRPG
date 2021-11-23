import java.io.*;
public class RPG_Game{
   public static void start() throws IOException{
      int levels = 9;
      int levelLength = 4;
      System.out.println("Dungeon of " + levels + " levels with " + levelLength + " main rooms per level generated:");
      RPG_DungeonBuilder newDungeon = new RPG_DungeonBuilder(levels,levelLength);
      RPG_Player player = RPG_Character_Builder.buildCharacter();
      RPG_Dungeon mainDungeon = new RPG_Dungeon(newDungeon.getHead(), player); 
   }
}
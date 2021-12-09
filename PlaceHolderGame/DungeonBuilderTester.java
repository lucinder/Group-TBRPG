public class DungeonBuilderTester{
   public static void main(String[] args){
      int levels = 3;
      int levelLength = 3;
      System.out.println("Dungeon of " + levels + " levels with " + levelLength + " main rooms per level:");
      RPG_DungeonBuilder newDungeon = new RPG_DungeonBuilder(levels,levelLength);
   }
}
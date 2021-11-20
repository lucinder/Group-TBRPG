/**
@author Lucinder
@file RPG_Dungeon.java
Container file for dungeons (room sets).
**/
public class RPG_Dungeon{
   // private static RPG_Room[][] DUNGEON_ARRAY;
   private static RPG_Room DUNGEON_ROOM_HEAD;
   private static RPG_Room DUNGEON_ROOM_CURRENT;
   private RPG_Player player;
   
   // move to an adjacent, open square
   public void move(String dir){
      if(dir.equals("N")){
         System.out.println(player.getName() + " proceeded to the north.");
         if(DUNGEON_ROOM_CURRENT.hasUp()){
            if(DUNGEON_ROOM_CURRENT.isExit("N")){
               DUNGEON_ROOM_CURRENT = DUNGEON_ROOM_CURRENT.getUp();
               System.out.println(DUNGEON_ROOM_CURRENT.getDialogue());
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
            } else {
               System.out.println("But the door was locked!");
            }
         } else {
            System.out.println("But there was no door leading west!");
         }
      }
   }
   
}
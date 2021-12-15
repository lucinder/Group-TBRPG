/**
@author Lucinder
extremely simplified dungeon build for ease of testing items!
**/
import java.io.*;
public class BunchOTests{
   public static void main(String[] args){
      try{
         RPG_Classes_List CLASS_LIST = new RPG_Classes_List();
         RPG_Races_List RACE_LIST = new RPG_Races_List();
         RPG_Player c = new RPG_Player("gogy", RACE_LIST.BASIC_RACES.get("HALF-ORC"), CLASS_LIST.CLASSES.get("CLERIC"));
         RPG_Dungeon d = new RPG_Dungeon(buildDungeon(1,3), c);
         d.mainLoop();
      } catch (Exception e){
         e.printStackTrace();
      }
   }
   // custom dungeon builder
   public static RPG_Room buildDungeon(int levels, int levelLength){
      RPG_Room head = new RPG_Room("0_Empty");
      RPG_Room cur = head;
      for(int i = 1; i <= levels; i++){ // populate all dungeon levels
         for(int j = 1; j <= levelLength; j++){ // populate all dungeon rooms
            int idNo = (i-1)*levelLength + j;
            if(j == levelLength){
               cur.setUp(new RPG_Room(idNo+"n_Shop")); // add shop room to the north
               cur.unlockExit("N");
               cur.getUp().unlockExit("S");
               System.out.println("DEBUG - Room ID = " + cur.getUp().getID());
               cur.setRight(new RPG_Room(idNo+"_Boss")); // add boss room
               cur.unlockExit("E");
               cur = cur.getRight();
               if(i == 1){ // load first boss dialogue
                  cur.setSpecialDialogue(new String[]{
                     "BOSS ROOM"
                  });
               }
               if(i == levels){ // are we in the VERY last room?
                  cur.setFinalRoom();
               }
               cur.unlockExit("W");
               System.out.println("DEBUG - Room ID = " + cur.getID());
            } else { 
               cur.unlockExit("E");
               if(j%2 == 1){
                  cur.setRight(new RPG_Room(idNo+"_Tres"));
               } else {
                  cur.setRight(new RPG_Room(idNo+"_En"));
               }
               cur = cur.getRight();
               cur.unlockExit("W");
               System.out.println("DEBUG - Room ID = " + cur.getID());
            }
         }
      }
      return head;
   }
}
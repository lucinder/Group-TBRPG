public class RPG_DungeonBuilder{
   private RPG_Room head;
   public RPG_DungeonBuilder(int levels, int levelLength){
      /**
      if levelLength = 1, each level is an empty room
      if levelLength = 2, each level is a starting (empty) room followed by a boss room
      if levelLength = 3, each level is a starting (empty) room followed by a combat room, and then a boss room
      if levelLength >= 4, each level is a starting(empty) room followed by any combination of combat or treasure rooms, and then a boss room.
      **/
      head = new RPG_Room("0_Empty");
      RPG_Room cur = head;
      for(int i = 1; i <= levels; i++){ // populate all dungeon levels
         for(int j = 1; j <= levelLength; j++){ // populate all dungeon rooms
            int idNo = (i-1)*levelLength + j;
            if(j == levelLength){
               cur.unlockExit("E");
               cur.setRight(new RPG_Room(idNo+"_Boss")); // add boss room
               cur = cur.getRight();
               cur.unlockExit("W"); // unlock boss room
               System.out.println(cur.getID());
            } else { 
               cur.unlockExit("E");
               // randomize next room's contents
               cur.setRight(new RPG_Room(idNo+"_"+getRandomRoomType()));
               cur = cur.getRight();
               cur.unlockExit("W");
               System.out.println(cur.getID());
               // randomly add north and south branches
               boolean north = branch();
               boolean south = branch();
               if(north){
                  cur.unlockExit("N");
                  RPG_Room northbranch = new RPG_Room(idNo+"n_"+getRandomRoomType());
                  northbranch.unlockExit("S");
                  if(cur.getLeft() != null && cur.getLeft().getUp() != null){ // backtrack linking for north branch
                     cur.getLeft().getUp().unlockExit("E");
                     cur.getLeft().getUp().setRight(cur.getUp());
                     cur.getUp().unlockExit("W");
                     cur.getUp().setLeft(cur.getLeft().getUp());
                  }
                  System.out.println(northbranch.getID());
               }
               if(south){
                  cur.unlockExit("S");
                  RPG_Room southbranch = new RPG_Room(idNo+"s_"+getRandomRoomType());
                  southbranch.unlockExit("N");
                  if(cur.getLeft() != null && cur.getLeft().getDown() != null){ // backtrack linking for south branch
                     cur.getLeft().getDown().unlockExit("E");
                     cur.getLeft().getDown().setRight(cur.getDown());
                     cur.getDown().unlockExit("W");
                     cur.getDown().setLeft(cur.getLeft().getDown());
                  }
                  System.out.println(southbranch.getID());
               }
            }
         }
      }
   }
   public RPG_Room getHead(){ return head; }
   
   private boolean branch(){ return (int)(Math.random()*2) == 0; }
   private String getRandomRoomType(){
      String[] roomTypes = {"En","Trap","Tres","EnTrap","EnTres","TrapTres","EnTrapTres"};
      int index = (int)(Math.random()*roomTypes.length);
      return roomTypes[index];
   }
}
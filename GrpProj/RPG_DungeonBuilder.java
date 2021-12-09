public class RPG_DungeonBuilder{
   private RPG_Room head;
   public RPG_DungeonBuilder(int levels, int levelLength) throws InterruptedException{
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
               cur.setUp(new RPG_Room(idNo+"n_Shop")); // add shop room to the north
               cur.unlockExit("N");
               cur.getUp().unlockExit("S");
               // System.out.println("DEBUG - Room ID = " + cur.getUp().getID());
               cur.setRight(new RPG_Room(idNo+"_Boss")); // add boss room
               cur.unlockExit("E");
               cur = cur.getRight();
               if(i == levels){ // are we in the VERY last room?
                  cur.setFinalRoom();
               }
               cur.unlockExit("W"); // unlock boss room
               // System.out.println(cur.getID());
            } else { 
               cur.unlockExit("E");
               // randomize next room's contents
               cur.setRight(new RPG_Room(idNo+"_"+getRandomRoomType_NormalPath()));
               cur = cur.getRight();
               cur.unlockExit("W");
               // System.out.println(cur.getID());
               // randomly add north and south branches
               boolean north = branch();
               boolean south = branch();
               if(north){
                  RPG_Room northbranch = new RPG_Room(idNo+"n_"+getRandomRoomType_BranchPath());
                  cur.setUp(northbranch);
                  cur.unlockExit("N");
                  northbranch.setDown(cur);
                  northbranch.unlockExit("S");
                  if(cur.getLeft() != null && cur.getLeft().getUp() != null){ // backtrack linking for north branch
                     cur.getLeft().getUp().unlockExit("E");
                     cur.getLeft().getUp().setRight(cur.getUp());
                     cur.getUp().unlockExit("W");
                     cur.getUp().setLeft(cur.getLeft().getUp());
                  }
                  // System.out.println(northbranch.getID());
               }
               if(south){
                  RPG_Room southbranch = new RPG_Room(idNo+"s_"+getRandomRoomType_BranchPath());
                  cur.setDown(southbranch);
                  cur.unlockExit("S");
                  southbranch.setUp(cur);
                  southbranch.unlockExit("N");
                  if(cur.getLeft() != null && cur.getLeft().getDown() != null){ // backtrack linking for south branch
                     cur.getLeft().getDown().unlockExit("E");
                     cur.getLeft().getDown().setRight(cur.getDown());
                     cur.getDown().unlockExit("W");
                     cur.getDown().setLeft(cur.getLeft().getDown());
                  }
                  // System.out.println(southbranch.getID());
               }
            }
         }
      }
   }
   
   // DEBUG MODE CONSTRUCTOR
   public RPG_DungeonBuilder(int levels, int levelLength, String difficultyOverride) throws InterruptedException{
      head = new RPG_Room("0_Empty");
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
               if(i == levels){ // are we in the VERY last room?
                  cur.setFinalRoom();
               }
               cur.unlockExit("W");
               System.out.println("DEBUG - Room ID = " + cur.getID());
            } else { 
               cur.unlockExit("E");
               cur.setRight(new RPG_Room(idNo+"_"+getRandomRoomType_NormalPath(),difficultyOverride));
               cur = cur.getRight();
               cur.unlockExit("W");
               System.out.println("DEBUG - Room ID = " + cur.getID());
               boolean north = branch();
               boolean south = branch();
               if(north){
                  RPG_Room northbranch = new RPG_Room(idNo+"n_"+getRandomRoomType_BranchPath(),difficultyOverride);
                  cur.setUp(northbranch);
                  cur.unlockExit("N");
                  northbranch.setDown(cur);
                  northbranch.unlockExit("S");
                  if(cur.getLeft() != null && cur.getLeft().getUp() != null){
                     cur.getLeft().getUp().unlockExit("E");
                     cur.getLeft().getUp().setRight(cur.getUp());
                     cur.getUp().unlockExit("W");
                     cur.getUp().setLeft(cur.getLeft().getUp());
                  }
                 System.out.println("DEBUG - Room ID = " + northbranch.getID());
               }
               if(south){
                  RPG_Room southbranch = new RPG_Room(idNo+"s_"+getRandomRoomType_BranchPath(),difficultyOverride);
                  cur.setDown(southbranch);
                  cur.unlockExit("S");
                  southbranch.setUp(cur);
                  southbranch.unlockExit("N");
                  if(cur.getLeft() != null && cur.getLeft().getDown() != null){ 
                     cur.getLeft().getDown().unlockExit("E");
                     cur.getLeft().getDown().setRight(cur.getDown());
                     cur.getDown().unlockExit("W");
                     cur.getDown().setLeft(cur.getLeft().getDown());
                  }
                  System.out.println("DEBUG - Room ID = " + southbranch.getID());
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
   private String getRandomRoomType_NormalPath(){
      String[] roomTypes = {"Empty","En"}; // only empty and enemy rooms on the normal path. ratio is 5:1 for enemy-normal
      int index = (int)(Math.random()*6);
      if(index == 0){ // empty
         return roomTypes[index];
      }
      return roomTypes[1]; // enemy room
   }
   private String getRandomRoomType_BranchPath(){
      String[] roomTypes = {"Tres","EnTres","TrapTres","EnTrapTres"}; // all branching paths have treasure, but may have additional threats
      int index = (int)(Math.random()*roomTypes.length);
      return roomTypes[index];
   }
}
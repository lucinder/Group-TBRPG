/**
@author AboudS
Revised for integration by Lucinder
@file RPG_Room.java
A Room class, containing a list of interactables and defined set of exits.
**/
import java.util.ArrayList;
public class RPG_Room {
   private int roomNumber = 0;
   // Room #0 is the generic ID for a "wall" or placeholder room.
   // Room #1, the starting room, must have a path to every other room in the dungeon with at least 1 door.
   private String roomID = "DefaultRoomID"; 
   /** Lucinder's note: my aim is to eventually be able to implement a "seed" feature using this class
    ID nomenclature: X_T_O
    Where X = absolute room number in dungeon,
    T = the room type,
    O = the id strings of each interactable in the room (to be worked on)
    The nomenclature system will be important if we choose to implement saving and loading as a feature in the future!
   **/
   private String roomType = "Empty"; // Room type: must be one of the following: Empty, Enemy, Trap, Treasure, EnTrap (Enemy/Trap), EnTres (Enemy/Treasure), EnTrapTres (Enemy/Trap/Treasure), TrapTres (Trap/Treasure), Shop, Boss
   private String dialogue = "";
   private boolean[] exits = {false, false, false, false};
   /** Added 11/18- which sides of the room are functional exits (N, E, S, W)? default none.
    The empty no-exit default room provides a room that serves as a "wall"- that is, it can be placed between rooms or dungeon layers to prevent direct passage.
    Using the exit system, we can build our dungeon as a 2d array rather than a LinkedList, but also place certain limits on access even within the linked list structure
    The reason in general I've made this change is for modularity; randomization of dungeons can be done more easily using conditionals.
    - All rooms on the main path must have at least 1 exit.
    - Dead ends- rooms with only 1 exit- must be either boss rooms, shops, or sidequest rooms.
    - Rooms with 2 exits are connecting paths between central rooms. They can only have traps.
    - Rooms with 3 or more exits are central rooms. They can contain any combination of traps, encounters, and treasures.
    - There must be at least 3 dead ends in a dungeon path; the boss room, shop, and sidequest room.
   **/
   private RPG_Room up;
   private RPG_Room down;
   private RPG_Room left;
   private RPG_Room right;
   private ArrayList<RPG_Interactable> objects; // contains all characters, traps, treasure/items, and enemies or shopkeeps
   
   public RPG_Room(){}
   public RPG_Room(int roomNumber){ this.roomNumber = roomNumber; }
   public RPG_Room(String id){
      roomID = id;
      String[] idParser = id.split("_");
      roomNumber = Integer.parseInt(idParser[0]);
      roomType = idParser[1];
      // once we add a method for processing object id strings- parseObjects(String id)- add that here
   }
   
   // Getters & Setters
   public String getID() {
      return roomID;
   }
   public void setID(String roomID) {
      this.roomID = roomID;
   }
    
   public String getType() {
      return roomType;
   }
   public void setType(String roomType) {
      this.roomType = roomType;
   }
   
   public boolean[] getExits() {
      return exits;
   }
   public boolean isExit(String direction){
      if(direction.equals("N")){
         return exits[0];
      } else if(direction.equals("E")){
         return exits[1];
      } else if(direction.equals("S")){
         return exits[2];
      } else if(direction.equals("W")){
         return exits[3];
      } else {
         System.out.println("ERROR: Invalid direction!");
         return false;
      }
   }
   public void lockExit(String direction){ // remove/lock the exit in a given direction
      if(direction.equals("N")){
         exits[0] = false;
      } else if(direction.equals("E")){
         exits[1] = false;
      } else if(direction.equals("S")){
         exits[2] = false;
      } else if(direction.equals("W")){
         exits[3] = false;
      } else {
         System.out.println("ERROR: Invalid direction!");
      }
   }
   public void unlockExit(String direction){ // add/unlock an exit in a given direction
      if(direction.equals("N")){
         exits[0] = true;
      } else if(direction.equals("E")){
         exits[1] = true;
      } else if(direction.equals("S")){
         exits[2] = true;
      } else if(direction.equals("W")){
         exits[3] = true;
      } else {
         System.out.println("ERROR: Invalid direction!");
      }
   }
   
   public boolean hasDown(){ return down != null; }
   public boolean hasUp(){ return up != null; }
   public boolean hasLeft(){ return left != null; }
   public boolean hasRight(){ return right != null; }
 
   public RPG_Room getDown(){ return down; }
   public RPG_Room getUp(){ return up; }
   public RPG_Room getLeft(){ return left; }
   public RPG_Room getRight(){ return right; }
   public void setDown(RPG_Room newRoom){
      this.down = newRoom;
      if(!newRoom.getUp().getID().equals(roomID)){ // infinite loop prevention
         newRoom.setUp(this);
      }
   }
   public void setUp(RPG_Room newRoom){
      this.up = newRoom;
      if(!newRoom.getDown().getID().equals(roomID)){ // infinite loop prevention
         newRoom.setDown(this);
      }
   }
   public void setLeft(RPG_Room newRoom){
      this.left = newRoom;
      if(!newRoom.getRight().getID().equals(roomID)){ // infinite loop prevention
         newRoom.setRight(this);
      }
   }
   public void setRight(RPG_Room newRoom){
      this.right = newRoom;
      if(!newRoom.getLeft().getID().equals(roomID)){ // infinite loop prevention
         newRoom.setLeft(this);
      }
   }
   
   public ArrayList<RPG_Interactable> getObjects() {
      return objects;
   }
   public void addObject(RPG_Interactable newObject){
      objects.add(newObject);
   }
   
   public String getDialogue() {
      return dialogue;
   }
   public void setDialogue(String dialogue) {
      this.dialogue = dialogue;
   }
   
   public RPG_Room getRoom(int key){
   // recursive sequential search in down-right order
   // DOES NOT check up or left, so search must start in top left
      if(roomNumber == key){ return this; }
      else if(hasDown()){
         RPG_Room found = down.getRoom(key);
         if(found != null){ return found; }
      } else if(hasRight()){
         RPG_Room found = right.getRoom(key);
         if(found != null){ return found; }
      }
      return null;
   }
   
}

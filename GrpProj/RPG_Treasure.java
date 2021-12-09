/**
@author Lucinder
@file RPG_Treasure.java
Loot pile handler
**/
import java.util.ArrayList;
public class RPG_Treasure extends RPG_Interactable{
   ArrayList<RPG_Item> treasure;
   boolean looted = false;
   public RPG_Treasure(){
      super("Treasure Chest", "The treasure chest is empty.");
      treasure = RPG_TreasureTables.rollTreasure();
   }
   public ArrayList<RPG_Item> getContents(){
      return treasure;
   }
   public void interactionEvent(){
      if(!looted){
         System.out.println("A treasure chest lies on the ground.");
      } else {
         System.out.println("An empty treasure chest lies on the ground.");
      }
   }
   public void interactionEvent(RPG_Player player) throws InterruptedException{
      if(looted){ // if we've looted the chest, display that it's empty and cut the loot method short
         System.out.println("The treasure chest is empty.");
         return;
      }
      System.out.println("You opened the treasure chest...");
      for(RPG_Item i : treasure){
         if(i == null){
            System.out.println("DEBUG MESSAGE - If you're reading this, something is weird with treasure loading!");
         } else {
            if(!i.getName().equals("Gold")){
               System.out.println("Got a " + i.getName() + "!");
            }
            player.addItem(i);
         }
      }
      this.looted = true;
   }
}
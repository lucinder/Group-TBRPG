/**
@author Lucinder
@file TreasureTest.java
**/
import java.util.ArrayList;
public class TreasureTest{
   public static void main(String[] args){
      RPG_Treasure loot1 = new RPG_Treasure();
      for(RPG_Item i : loot1.getContents()){
         System.out.println(i.getName());
      }
   }
}
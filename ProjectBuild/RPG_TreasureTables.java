/**
@author Lucinder
@file RPG_TreasureTables
LOOT TABLES BABY!!!!
Main Treasure Table:
 0 - 2d6*10 GP
 1 - 2d6*20 GP
 2 - 2d6*10 GP + 2d4*25 GP
 3 - 2d6*10 GP + 2d6*50 GP
 4 - 2d6*20 GP + 1d6 * Magic A
 5 - 2d6*10 GP + 2d4*25 GP + 1d6 * Magic A
 6 - 2d6*10 GP + 2d6*50 GP + 1d6 * Magic A
 7 - 2d6*20 GP + 1d4 * Magic B
 8 - 2d6*10 GP + 2d4*25 GP + 1d4 * Magic B
 9 - 2d6*10 GP + 2d6*50 GP + 1d4 * Magic B
 10 - 2d6*20 GP + 1d6 * Magic C
 11 - 2d6*10 GP + 2d4*25 GP + 1d6 * Magic C
 12 - 2d6*10 GP + 2d6*50 GP + 1d6 * Magic C
 13 - 2d6*10 GP + 2d4*25 GP + 1d6 * Magic F
 14 - 2d6*10 GP + 2d6*50 GP + 1d6 * Magic F
 15 - 2d6*10 GP + 2d4*25 GP + 1d6 * Magic G
 16 - 2d6*10 GP + 2d6*50 GP + 1d6 * Magic G
**/
import java.util.ArrayList;
import java.util.HashMap;
public class RPG_TreasureTables{
   private static HashMap<Integer, RPG_Item> magicA = new HashMap();
   private static HashMap<Integer, RPG_Item> magicB = new HashMap();
   private static HashMap<Integer, RPG_Item> magicC = new HashMap();
   private static HashMap<Integer, RPG_Item> magicF = new HashMap();
   private static HashMap<Integer, RPG_Item> magicG = new HashMap();
   public static void fillAll(){
      fillMagicA();
      fillMagicB();
      fillMagicC();
      fillMagicF();
      fillMagicG();
   }
   public static void fillMagicA(){
      magicA.put(0, RPG_Items_List.MAGICITEMS.POTION_HEALING);
      magicA.put(1, RPG_Items_List.MAGICITEMS.SCROLL_CANTRIP);
      magicA.put(2, RPG_Items_List.MAGICITEMS.SCROLL_1);
      magicA.put(3, RPG_Items_List.MAGICITEMS.SCROLL_3);
   }
   public static void fillMagicB(){
      magicB.put(0, RPG_Items_List.MAGICITEMS.POTION_HEALING_1);
      magicB.put(1, RPG_Items_List.MAGICITEMS.POTION_STRENGTH);
      magicB.put(2, RPG_Items_List.MAGICITEMS.SCROLL_1);
      magicB.put(3, RPG_Items_List.MAGICITEMS.SCROLL_3);
      magicB.put(4, RPG_Items_List.ARMORS.SHIELD);
   }
   public static void fillMagicC(){
      magicC.put(0, RPG_Items_List.MAGICITEMS.POTION_HEALING_2);
      magicC.put(1, RPG_Items_List.MAGICITEMS.POTION_STRENGTH_1);
      magicC.put(2, RPG_Items_List.MAGICITEMS.POTION_STRENGTH_2);
      magicC.put(3, RPG_Items_List.MAGICITEMS.SCROLL_5);
      magicC.put(4, RPG_Items_List.MAGICITEMS.SCROLL_6);
   }
   public static void fillMagicF(){
      magicF.put(0, RPG_Items_List.PLACEHOLDER_SIMPLE_1);
      magicF.put(1, RPG_Items_List.PLACEHOLDER_MARTIAL_1);
      magicF.put(2, RPG_Items_List.MAGICITEMS.SHIELD_1);
      magicF.put(3, RPG_Items_List.ARMORS.CHAIN_MAIL);
      magicF.put(4, RPG_Items_List.ARMORS.SCALE_MAIL);
   }
   public static void fillMagicG(){
      magicG.put(0, RPG_Items_List.PLACEHOLDER_SIMPLE_2);
      magicG.put(1, RPG_Items_List.PLACEHOLDER_MARTIAL_2);
      magicG.put(2, RPG_Items_List.MAGICITEMS.SHIELD_2);
      magicG.put(3, RPG_Items_List.ARMORS.HALF_PLATE);
      magicG.put(4, RPG_Items_List.ARMORS.PLATE);
      magicG.put(5, RPG_Items_List.PLACEHOLDER_ARMOR_1);
      magicG.put(6, RPG_Items_List.MAGICITEMS.POISON_DAGGER);
      magicG.put(7, RPG_Items_List.MAGICITEMS.SUN_SWORD);
   }
   public static ArrayList<RPG_Item> rollTreasure(){
      fillAll();
      ArrayList<RPG_Item> treasure = new ArrayList<RPG_Item>();
      int baseGP = RPG_Dice.XdY(2,6)*10;
      int additionalGP = 0;
      int mainResult = (int)(Math.random()*17);
      // on a 0, put nothing
      if(mainResult == 1 || mainResult == 4 || mainResult == 7 || mainResult == 10){
         additionalGP = RPG_Dice.XdY(2,6)*10;
      } else if(mainResult == 2 || mainResult == 5 || mainResult == 8 || mainResult == 11 || mainResult == 13 || mainResult == 15) {
         additionalGP = RPG_Dice.XdY(2,4)*25;
      } else if(mainResult == 3 || mainResult == 6 || mainResult == 9 || mainResult == 12 || mainResult == 14 || mainResult == 16){
         additionalGP = RPG_Dice.XdY(2,6)*50;
      }
      treasure.add(new RPG_Item("Gold",1,baseGP + additionalGP));
      int tableRolls = RPG_Dice.XdY(1,6);
      int tableRollsB = RPG_Dice.XdY(1,4);
      if(mainResult == 4 || mainResult == 5 || mainResult == 6){ // table A
         for(int i = 0; i < tableRolls; i++){
            RPG_Item toAdd = magicA.get((int)(Math.random()*magicA.size()));
            treasure.add(toAdd);
         }
      } else if(mainResult == 7 || mainResult == 8 || mainResult == 9) { // table B
         for(int i = 0; i < tableRollsB; i++){
            RPG_Item toAdd = magicB.get((int)(Math.random()*magicB.size()));
            treasure.add(toAdd);
         }
      } else if(mainResult == 10 || mainResult == 11 || mainResult == 12) { // table C
         for(int i = 0; i < tableRolls; i++){
            RPG_Item toAdd = magicC.get((int)(Math.random()*magicC.size()));
            treasure.add(toAdd);
         }
      } else if(mainResult == 13 || mainResult == 14) { // table F
         for(int i = 0; i < tableRolls; i++){
            RPG_Item toAdd = magicF.get((int)(Math.random()*magicF.size()));
            treasure.add(toAdd);
         }
      } else if(mainResult == 15 || mainResult == 16) { // table G
         for(int i = 0; i < tableRolls; i++){
            RPG_Item toAdd = magicG.get((int)(Math.random()*magicG.size()));
            treasure.add(toAdd);
         }
      }
      return treasure;
   }
}
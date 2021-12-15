/**
@author Lucinder
@file RPG_Shop.java
Shop class to handle shopping events
**/
import java.util.Scanner;
public class RPG_Shop extends RPG_Interactable{
   public static final int TEXTDELAY = 10;
   public static final int LINEDELAY = 0;
   
   private RPG_Item buy1; // weapon for sale
   private RPG_Item buy2; // armor for sale
   private RPG_Item buy3; // misc item for sale
   private RPG_Item[] shopArray = new RPG_Item[3];
   private Scanner input = new Scanner(System.in);
   
   public RPG_Shop(){
      super("Item Shop", "A dark-robed shopkeep tends to his quaint little shop in the corner of the room.");
      randomizeContents();
   }
   
   
   public void shop(RPG_Character player){
      printStaggered("\"Welcome to Ye Ole Dungeon Shoppe, dear adventurer!\"");
      printStaggered("What would you like to do?\n [0] Buy\n [1] Sell\n [2] Exit");
      try{
         String sel = input.nextLine().split(" ")[0];
         int selection = Integer.parseInt(sel);
         if(selection == 0){
            buy(player);
         } else if (selection == 1){
            sell(player);
         } else {
            return; // leave shop
         }
      } catch (Exception e){
         printStaggered("Invalid selection!");
         shop(player);
      }
   }
   
   public void buy(RPG_Character player){ // menu for buying items
      printStaggered("\"What shall ye be buyin\'?\"");
      int i = 0;
      for(RPG_Item item : shopArray){
         printStaggered("["+i +"] " + item.toString()); 
         i++;
      }
      printStaggered("[" + i + "] Go Back");
      try{
         int selection = -1;
         String[] inp = input.nextLine().split(" ");
         for(int n = 0; n <= i; n++){ // registry for selection
            if(contains(inp, ""+n)){ selection = n; }
         }
         if(selection != -1 && selection < i){
            purchase(player, selection);
            buy(player);
         } else if(selection == i) {
            shop(player); // go back to main shop menu
         } else {
            printStaggered("Invalid selection!");
            buy(player);
         }
      } catch (Exception e){
         printStaggered("Invalid selection!");
         buy(player);
      }
   }
   
   public void sell(RPG_Character player){ // menu for selling items
      printStaggered("\"What shall ye be sellin\'?\"");
      int i = 0;
      for(RPG_Item item : player.getInventoryArray()){
         printStaggered("["+i +"] " + item.toString()); 
         i++;
      }
      printStaggered("[" + i + "] Go Back");
      try{
         int selection = -1;
         String[] inp = input.nextLine().split(" ");
         for(int n = 0; n <= i; n++){ // registry for selection
            if(contains(inp, ""+n)){ selection = n; }
         }
         if(selection != -1 && selection < i){
            sellItem(player, selection);
            sell(player);
         } else if(selection == i) {
            shop(player); // go back to main shop menu
         } else {
            printStaggered("Invalid selection!");
            sell(player);
         }
      } catch (Exception e){
         printStaggered("Invalid selection!");
         shop(player);
      }
   }
   
   public void purchase(RPG_Character player, int index){ // puchase an item
      RPG_Item toBuy = shopArray[index];
      if(player.getGP() > toBuy.getValue()){
         player.removeGP(toBuy.getValue());
         printStaggered("Cha-ching!");
         player.addItem(toBuy);
      } else {
         printStaggered("You don\'t have enough gold!");
      }
   }
   
   public void sellItem(RPG_Character player, int index){ // sell an item
      RPG_Item toSell = player.getInventoryArray()[index];
      printStaggered("Cha-ching!");
      player.gainGP(toSell.getValue());
      player.removeItem(player.getItem(toSell.getName())); // double checks for name overlap since modifiers might be different between shop and player
   }
   
   public void randomizeContents(){
      int roll1 = (int)(Math.random()*100);
      int roll2 = (int)(Math.random()*100);
      int roll3 = (int)(Math.random()*100);
      
      // WEAPON RANDOMIZATION
      if(roll1 < 50){ buy1 = RPG_Items_List.PLACEHOLDER_MARTIAL; } // 50% chance to get a normal martial weapon
      else if(roll1 < 75){ buy1 = RPG_Items_List.PLACEHOLDER_MARTIAL_1; } // 25% chance to get a +1 martial weapon
      else if(roll1 < 90){ buy1 = RPG_Items_List.PLACEHOLDER_MARTIAL_2; } // 15% chance to get a +2 martial weapon
      else{buy1 = RPG_Items_List.PLACEHOLDER_MARTIAL_3; } // 10% chance to get a +3 martial weapon
      
      // ARMOR RANDOMIZATION
      if(roll2 < 50){ buy2 = RPG_Items_List.PLACEHOLDER_ARMOR; } // 50% chance to get normal armor
      else if(roll2 < 75){ buy2 = RPG_Items_List.PLACEHOLDER_ARMOR_1; } // 25% chance to get +1 armor
      else if(roll2 < 90){ buy2 = RPG_Items_List.PLACEHOLDER_ARMOR_2; } // 15% chance to get +2 armor
      else{buy2 = RPG_Items_List.PLACEHOLDER_ARMOR_3; } // 10% chance to get +3 armor
      
      // MISC ITEM RANDOMIZATION
      if(roll3 < 15){ buy3 = RPG_Items_List.ARMORS.SHIELD; } // 15% chance for a shield
      else if(roll3 < 20){ buy3 = RPG_Items_List.MAGICITEMS.SHIELD_1; } // 5% chance for a +1 shield
      else if(roll3 < 22){ buy3 = RPG_Items_List.MAGICITEMS.SHIELD_2; } // 2% chance for a +2 shield
      else if(roll3 < 23){ buy3 = RPG_Items_List.MAGICITEMS.SHIELD_3; } // 1% chance for a +3 shield
      else if(roll3 < 43){ buy3 = RPG_Items_List.MAGICITEMS.SCROLL_CANTRIP; } // 20% chance for a cantrip scroll
      else if(roll3 < 58){ buy3 = RPG_Items_List.MAGICITEMS.SCROLL_1; } // 15% chance for a lv1 scroll
      else if(roll3 < 63){ buy3 = RPG_Items_List.MAGICITEMS.SCROLL_2; } // 5% chance for a lv2 scroll
      else if(roll3 < 65){ buy3 = RPG_Items_List.MAGICITEMS.SCROLL_3; } // 2% chance for a lv3 scroll
      else if(roll3 < 66){ buy3 = RPG_Items_List.MAGICITEMS.SCROLL_4; } // 1% chance for a lv4 scroll
      else if(roll3 < 81){ buy3 = RPG_Items_List.MAGICITEMS.POTION_HEALING; } // 15% chance for a potion of healing
      else if(roll3 < 91){ buy3 = RPG_Items_List.MAGICITEMS.POTION_HEALING_1; } // 10% chance for a potion of greater healing
      else if(roll3 < 96){ buy3 = RPG_Items_List.MAGICITEMS.POTION_HEALING_2; }// 5% chance for a potion of superior healing
      else if(roll3 < 98){ buy3 = RPG_Items_List.MAGICITEMS.POTION_HEALING_3; } // 2% chance for a potion of supreme healing
      else{ buy3 = RPG_Items_List.MAGICITEMS.POTION_STRENGTH; }// 2% chance for a potion of strength
      
      shopArray = new RPG_Item[]{buy1, buy2, buy3};
      
      int index = 0;
      for(RPG_Item i : shopArray){ // LOAD PLACEHOLDERS
         if(i == null){
            System.out.println("DEBUG MESSAGE - If you're reading this, something is weird with item loading!");
         } else {
            if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_CANTRIP)){ // spell scroll cantrip
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.Cantrips[(int)(Math.random()*RPG_SpellLists.Cantrips.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_1)){ // spell scroll 1
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FirstLevel[(int)(Math.random()*RPG_SpellLists.FirstLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_2)){ // spell scroll 2
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.SecondLevel[(int)(Math.random()*RPG_SpellLists.SecondLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_3)){ // spell scroll 3
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.ThirdLevel[(int)(Math.random()*RPG_SpellLists.ThirdLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_4)){ // spell scroll 4
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FourthLevel[(int)(Math.random()*RPG_SpellLists.FourthLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_5)){ // spell scroll 5
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FifthLevel[(int)(Math.random()*RPG_SpellLists.FifthLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_6)){ // spell scroll 6
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.SixthLevel[(int)(Math.random()*RPG_SpellLists.SixthLevel.length)]);
               shopArray[index] = new RPG_SpellScroll(selection, i.getValue());
               // skip simple item loading since those aren't for sale
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_1)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(1);
               selection.setName("+1 " + selection.getName());
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_2)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(2);
               selection.setName("+2 " + selection.getName());
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_3)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(3);
               selection.setName("+3 " + selection.getName());
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_1)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(1);
               selection.setName("+1 " + selection.getName());
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_2)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(2);
               selection.setName("+2 " + selection.getName());
               shopArray[index] = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_3)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(3);
               selection.setName("+3 " + selection.getName());
               shopArray[index] = selection;
            }
         }
         index++;
      }
   }
   
   public static void printStaggered(String input) {
      try{
         printStaggered(input, TEXTDELAY);
      } catch (InterruptedException e){} // do nothing on exception
   }
   public static void printStaggered(String input, int delayMS) throws InterruptedException{ // ONLY works for single-line commands
      String[] toPrint = input.split("");
      for(String s : toPrint){
         System.out.print(s);
         Thread.sleep(delayMS);
      }
      System.out.println();
      Thread.sleep(LINEDELAY);
   }
   
   private boolean contains(String[] array, String key){ // helper to check if a string array contains a given string- not case sensitive
      for(String s : array){
         if(s.toLowerCase().equals(key.toLowerCase())){ return true; }
      }
      return false;
   }
   
}
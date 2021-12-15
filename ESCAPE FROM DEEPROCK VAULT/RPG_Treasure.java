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
   public void interactionEvent(RPG_Player player){
      if(looted){ // if we've looted the chest, display that it's empty and cut the loot method short
         System.out.println("The treasure chest is empty.");
         return;
      }
      System.out.println("You opened the treasure chest...");
      for(RPG_Item i : treasure){
         if(i == null){
            System.out.println("DEBUG MESSAGE - If you're reading this, something is weird with treasure loading!");
         } else {
            if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_CANTRIP)){ // spell scroll cantrip
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.Cantrips[(int)(Math.random()*RPG_SpellLists.Cantrips.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_1)){ // spell scroll 1
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FirstLevel[(int)(Math.random()*RPG_SpellLists.FirstLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_2)){ // spell scroll 2
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.SecondLevel[(int)(Math.random()*RPG_SpellLists.SecondLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_3)){ // spell scroll 3
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.ThirdLevel[(int)(Math.random()*RPG_SpellLists.ThirdLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_4)){ // spell scroll 4
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FourthLevel[(int)(Math.random()*RPG_SpellLists.FourthLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_5)){ // spell scroll 5
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.FifthLevel[(int)(Math.random()*RPG_SpellLists.FifthLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.MAGICITEMS.SCROLL_6)){ // spell scroll 6
               RPG_Spell selection = new RPG_Spell(RPG_SpellLists.SixthLevel[(int)(Math.random()*RPG_SpellLists.SixthLevel.length)]);
               i = new RPG_SpellScroll(selection, i.getValue());
            } else if(i.equals(RPG_Items_List.PLACEHOLDER_SIMPLE_1)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE[(int)(Math.random()*RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE.length)]);
               selection.setMagicBonus(1);
               selection.setName("+1 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_SIMPLE_2)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE[(int)(Math.random()*RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE.length)]);
               selection.setMagicBonus(2);
               selection.setName("+2 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_SIMPLE_3)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE[(int)(Math.random()*RPG_Items_List.SIMPLE_WEAPONS.ALLSIMPLE.length)]);
               selection.setMagicBonus(3);
               selection.setName("+3 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_1)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(1);
               selection.setName("+1 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_2)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(2);
               selection.setName("+2 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_MARTIAL_3)){
               RPG_Weapon selection = new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL[(int)(Math.random()*RPG_Items_List.MARTIAL_WEAPONS.ALLMARTIAL.length)]);
               selection.setMagicBonus(3);
               selection.setName("+3 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_1)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(1);
               selection.setName("+1 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_2)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(2);
               selection.setName("+2 " + selection.getName());
               i = selection;
            } else if (i.equals(RPG_Items_List.PLACEHOLDER_ARMOR_3)){
               RPG_Armor selection = new RPG_Armor(RPG_Items_List.ARMORS.ALLARMORS[(int)(Math.random()*RPG_Items_List.ARMORS.ALLARMORS.length)]);
               selection.setMagicBonus(3);
               selection.setName("+3 " + selection.getName());
               i = selection;
            }
            if(!i.getName().equals("Gold")){
               System.out.println("Got a " + i.getName() + "!");
            }
            player.addItem(i);
         }
      }
      this.looted = true;
   }
}
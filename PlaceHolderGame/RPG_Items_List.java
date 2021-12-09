/** 
@author Lucinder
@file RPG_ITEMS_LIST.java
Handler file for many items.
DO NOT MODIFY UNLESS YOU KNOW WHAT YOU'RE DOING.
**/
public class RPG_Items_List{
   public static RPG_Item PLACEHOLDER_SIMPLE = new RPG_Item( // placeholder for a Simple Weapon
      "Simple Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_MARTIAL = new RPG_Item( // placeholder for a Martial Weapon
      "Martial Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_ARCANE = new RPG_Item( // placeholder for an Arcane Focus
      "Arcane Focus",
      1
   );
   public static RPG_Item PLACEHOLDER_DRUIDIC = new RPG_Item( // placeholder for a Druidic Focus
      "Druidic Focus",
      1
   );
   public static RPG_Item PLACEHOLDER_SYMBOL = new RPG_Item( // placeholder for a Holy Symbol
      "Holy Symbol",
      1
   );
   public static RPG_Item PLACEHOLDER_INSTRUMENT = new RPG_Item( // placeholder for a Musical Instrument
      "Musical Instrument",
      1
   );
   public class SIMPLE_WEAPONS{
      public static RPG_Weapon SHORTBOW = new RPG_Weapon(
            "Shortbow",
            1,
            2500,
            new int[]{1,6},
            false,
            true
         );
      public static RPG_Weapon LIGHT_CROSSBOW = new RPG_Weapon(
            "Light Crossbow",
            1,
            2500,
            new int[]{1,8},
            false,
            true
         );
      public static RPG_Weapon DAGGER = new RPG_Weapon(
             "Dagger",
             1,
             200,
             new int[]{1,4},
             true
         );
      public static RPG_Weapon MACE = new RPG_Weapon(
            "Mace",
            1,
            500,
            new int[]{1,6}
         );
      public static RPG_Weapon QUARTERSTAFF = new RPG_Weapon(
            "Quarterstaff",
            1,
            20,
            new int[]{1,6}
         );
      public static RPG_Weapon JAVELIN = new RPG_Weapon(
            "Javelin",
            1,
            50,
            new int[]{1,6},
            true
         );
      public static RPG_Weapon[] ALLSIMPLE = new RPG_Weapon[]{SHORTBOW, LIGHT_CROSSBOW, DAGGER, MACE, QUARTERSTAFF, JAVELIN};
   }
   public class MARTIAL_WEAPONS{
      public static RPG_Weapon SHORTSWORD = new RPG_Weapon(
            "Shortsword",
            1,
            1000,
            new int[]{1,6},
            true
         );
      public static RPG_Weapon RAPIER = new RPG_Weapon(
            "Rapier",
            1,
            2500,
            new int[]{1,8},
            true
         );
      public static RPG_Weapon SCIMITAR = new RPG_Weapon(
            "Scimitar",
            1,
            2500,
            new int[]{1,6},
            true
         );
      public static RPG_Weapon LONGSWORD = new RPG_Weapon(
            "Longsword",
            1,
            11500,
            new int[]{1,10}
         );
      public static RPG_Weapon LONGBOW = new RPG_Weapon(
            "Longbow",
            1,
            5000,
            new int[]{1,8},
            false,
            true
         );
      public static RPG_Weapon[] ALLMARTIAL = new RPG_Weapon[]{SHORTSWORD, RAPIER, SCIMITAR, LONGSWORD, LONGBOW};
   }
   public class ARMORS{
      public static RPG_Armor LEATHER_ARMOR = new RPG_Armor(
            "Leather Armor",
            1,
            1000,
            11
         );
      public static RPG_Armor SCALE_MAIL = new RPG_Armor(
            "Scale Mail",
            1,
            5000,
            14,
            2
         );
      public static RPG_Armor CHAIN_MAIL = new RPG_Armor(
            "Chain Mail",
            1,
            7500,
            16,
            0
         );
      public static RPG_Item SHIELD = new RPG_Item(
            "Shield",
            1,
            1000
         );
   }
   
   /** TOOLS AND PACKS - TO BE REPLACED WITH CONTENTS! **/
   public static RPG_Item THIEVES_TOOLS = new RPG_Item(
            "Thieves\' Tools",
            1,
            2500
         );
   public static RPG_Item EXPLORERS_PACK = new RPG_Item(
            "Explorer\'s Pack",
            1,
            1000
         );
   public static RPG_Item DUNGEONEERS_PACK = new RPG_Item(
            "Dungeoneer\'s Pack",
            1,
            1200
         );
   public static RPG_Item ENTERTAINERS_PACK = new RPG_Item(
            "Entertainer\'s Pack",
            1,
            4000
         );
   public static RPG_Item SPELLBOOK = new RPG_Item(
            "Spellbook",
            1,
            5000
         );
         
}
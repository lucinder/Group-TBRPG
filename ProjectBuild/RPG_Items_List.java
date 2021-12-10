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
   public static RPG_Item PLACEHOLDER_SIMPLE_1 = new RPG_Item( // placeholder for a +1 Simple Weapon
      "+1 Simple Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_MARTIAL_1 = new RPG_Item( // placeholder for a +1 Martial Weapon
      "+1 Martial Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_ARMOR_1 = new RPG_Item( // placeholder for +1 Armor
      "+1 Armor",
      1
   );
   public static RPG_Item PLACEHOLDER_SIMPLE_2 = new RPG_Item( // placeholder for a +2 Simple Weapon
      "+2 Simple Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_MARTIAL_2 = new RPG_Item( // placeholder for a +2 Martial Weapon
      "+2 Martial Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_ARMOR_2 = new RPG_Item( // placeholder for +2 Armor
      "+2 Armor",
      1
   );
   public static RPG_Item PLACEHOLDER_SIMPLE_3 = new RPG_Item( // placeholder for a +3 Simple Weapon
      "+3 Simple Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_MARTIAL_3 = new RPG_Item( // placeholder for a +3 Martial Weapon
      "+3 Martial Weapon",
      1
   );
   public static RPG_Item PLACEHOLDER_ARMOR_3 = new RPG_Item( // placeholder for +3 Armor
      "+3 Armor",
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
            25,
            new int[]{1,6},
            false,
            true
         );
      public static RPG_Weapon LIGHT_CROSSBOW = new RPG_Weapon(
            "Light Crossbow",
            1,
            25,
            new int[]{1,8},
            false,
            true
         );
      public static RPG_Weapon DAGGER = new RPG_Weapon(
             "Dagger",
             1,
             2,
             new int[]{1,4},
             true
         );
      public static RPG_Weapon MACE = new RPG_Weapon(
            "Mace",
            1,
            5,
            new int[]{1,6}
         );
      public static RPG_Weapon QUARTERSTAFF = new RPG_Weapon(
            "Quarterstaff",
            1,
            2,
            new int[]{1,6}
         );
      public static RPG_Weapon JAVELIN = new RPG_Weapon(
            "Javelin",
            1,
            5,
            new int[]{1,6},
            true
         );
      public static RPG_Weapon[] ALLSIMPLE = new RPG_Weapon[]{SHORTBOW, LIGHT_CROSSBOW, DAGGER, MACE, QUARTERSTAFF, JAVELIN};
   }
   public class MARTIAL_WEAPONS{
      public static RPG_Weapon SHORTSWORD = new RPG_Weapon(
            "Shortsword",
            1,
            10,
            new int[]{1,6},
            true
         );
      public static RPG_Weapon RAPIER = new RPG_Weapon(
            "Rapier",
            1,
            25,
            new int[]{1,8},
            true
         );
      public static RPG_Weapon LONGSWORD = new RPG_Weapon(
            "Longsword",
            1,
            115,
            new int[]{1,10}
         );
      public static RPG_Weapon LONGBOW = new RPG_Weapon(
            "Longbow",
            1,
            50,
            new int[]{1,8},
            false,
            true
         );
      public static RPG_Weapon[] ALLMARTIAL = new RPG_Weapon[]{SHORTSWORD, RAPIER, LONGSWORD, LONGBOW};
   }
   public class ARMORS{
      public static RPG_Armor LEATHER_ARMOR = new RPG_Armor(
            "Leather Armor",
            1,
            10,
            11,
            "AC = 11 + DEX modifier."
         );
      public static RPG_Armor SCALE_MAIL = new RPG_Armor(
            "Scale Mail",
            1,
            50,
            14,
            2,
            "AC = 14 + DEX modifier (max 2)."
         );
      public static RPG_Armor CHAIN_MAIL = new RPG_Armor(
            "Chain Mail",
            1,
            75,
            16,
            0,
            "AC = 16."
         );
      public static RPG_Armor HALF_PLATE = new RPG_Armor(
            "Half-Plate Armor",
            1,
            750,
            15,
            2,
            "AC = 15 + DEX modifier (max 2)."
         );
      public static RPG_Armor PLATE = new RPG_Armor(
            "Plate Armor",
            1,
            1500,
            18,
            0,
            "AC = 18."
         );
      public static RPG_Shield SHIELD = new RPG_Shield( // standard shield
            "Shield",
            1,
            10,
            0
         );
      public static RPG_Armor[] ALLARMORS = new RPG_Armor[]{LEATHER_ARMOR, CHAIN_MAIL, SCALE_MAIL, HALF_PLATE, PLATE};
   }
   
   public class MAGICITEMS{ // MAGIC ITEMS including shields, potions, and scrolls
      public static RPG_Potion POTION_HEALING = new RPG_Potion( // heal 2d4+2
         "Potion of Healing",
         1,
         40,
         new int[]{2,4},
         2
      );
      public static RPG_Potion POTION_HEALING_1 = new RPG_Potion( // heal 4d4+4
         "Potion of Greater Healing",
         1,
         300,
         new int[]{4,4},
         4
      );
      public static RPG_Potion POTION_HEALING_2 = new RPG_Potion( // heal 8d4+8
         "Potion of Superior Healing",
         1,
         500,
         new int[]{8,4},
         8
      );
      public static RPG_Item POTION_HEALING_3 = new RPG_Potion( // heal 10d4+20
         "Potion of Supreme Healing",
         1,
         2000,
         new int[]{10,4},
         20
      );
      public static RPG_Item POTION_STRENGTH = new RPG_Potion( // str 21
         "Potion of Strength",
         1,
         100,
         true,
         new int[]{21,0,0,0,0,0}
      );
      public static RPG_Item POTION_STRENGTH_1 = new RPG_Potion( // str 23
         "Potion of Greater Strength",
         1,
         300,
         true,
         new int[]{23,0,0,0,0,0}
      );
      public static RPG_Item POTION_STRENGTH_2 = new RPG_Potion( // str 25
         "Potion of Superior Strength",
         1,
         500,
         true,
         new int[]{25,0,0,0,0,0}
      );
      public static RPG_Item POTION_STRENGTH_3 = new RPG_Potion( // str +27
         "Potion of Supreme Strength",
         1,
         1500,
         true,
         new int[]{27,0,0,0,0,0}
      );
      public static RPG_Item POTION_STRENGTH_4 = new RPG_Potion( // str 29
         "Potion of Exalted Strength",
         1,
         4000,
         true,
         new int[]{29,0,0,0,0,0}
      );
      public static RPG_Item SCROLL_CANTRIP = new RPG_Item(
         "Cantrip Scroll",
         1,
         10
      );
      public static RPG_Item SCROLL_1 = new RPG_Item(
         "Spell Scroll Lv1",
         1,
         100
      );
      public static RPG_Item SCROLL_2 = new RPG_Item(
         "Spell Scroll Lv2",
         1,
         300
      );
      public static RPG_Item SCROLL_3 = new RPG_Item(
         "Spell Scroll Lv3",
         1,
         800
      );
      public static RPG_Item SCROLL_4 = new RPG_Item(
         "Spell Scroll Lv4",
         1,
         2000
      );
      public static RPG_Item SCROLL_5 = new RPG_Item(
         "Spell Scroll Lv5",
         1,
         4000
      );
      public static RPG_Item SCROLL_6 = new RPG_Item(
         "Spell Scroll Lv6",
         1,
         10000
      );
      public static RPG_Shield SHIELD_1 = new RPG_Shield(
         "+1 Shield",
         1,
         10,
         1
      );
      public static RPG_Shield SHIELD_2 = new RPG_Shield(
         "+2 Shield",
         1,
         10,
         2
      );
      public static RPG_Shield SHIELD_3 = new RPG_Shield(
         "+3 Shield",
         1,
         10,
         3
      );
      public static RPG_Weapon POISON_DAGGER = new RPG_Weapon( // +1 dagger, deals poison damage
         "Poison Dagger",
         1,
         4000,
         new int[]{1,4},
         true,
         false,
         1,
         "Poison"
      );
      public static RPG_Weapon SUN_SWORD = new RPG_Weapon( // +2 longsword, deals radiant damage
         "Sun Sword",
         1,
         9000,
         new int[]{1,10},
         false,
         false,
         2,
         "Radiant"
      );
   }
   
   /** TOOLS AND PACKS - TO BE REPLACED WITH CONTENTS! **/
   public static RPG_Item THIEVES_TOOLS = new RPG_Item(
            "Thieves\' Tools",
            1,
            25
         );
   public static RPG_Item EXPLORERS_PACK = new RPG_Item(
            "Explorer\'s Pack",
            1,
            10
         );
   public static RPG_Item DUNGEONEERS_PACK = new RPG_Item(
            "Dungeoneer\'s Pack",
            1,
            12
         );
   public static RPG_Item ENTERTAINERS_PACK = new RPG_Item(
            "Entertainer\'s Pack",
            1,
            40
         );
   public static RPG_Item SPELLBOOK = new RPG_Item(
            "Spellbook",
            1,
            50
         );
         
}
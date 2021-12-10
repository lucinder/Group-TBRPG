/** 
@author Lucinder
@file RPG_Classes_List.java
Handler file for all SRD classes and their features.
DO NOT MODIFY UNLESS YOU KNOW WHAT YOU'RE DOING.
**/
import java.util.HashMap;
public class RPG_Classes_List{
   // SOURCE: https://5e.tools/classes.html
   public static int[] MANA_HALFCASTER = {0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}; // HALF CASTER MANA DISTRIBUTION
   // STAT ALLOCATING "AI"
      // Max Str > Con > Wis = fighter, barbarian = default
      // Max Str > Cha > Con = paladin = {0,5,2}
      // Max Dex > Wis > Con = monk, ranger = {1,4,2}
      // Max Dex > Cha > Int = rogue = {1,5,3}
      // Max Wis > Con > Str = cleric = {4,2,0}
      // Max Wis > Cha > Int = druid = {4,5,3}
      // Max Int > Dex > Wis = wizard, artificer = {3,1,4}
      // Max Cha > Con > Wis = warlock, sorcerer = {5,2,4}
      // Max Cha > Dex > Wis = bard = {5,1,4}
   // ARTIFICER
   public static RPG_Spellcaster ARTIFICER = new RPG_Spellcaster(
      "Artificer",
      8,
      new int[]{4,8,12,16,19},
      new int[]{3,1,4},
      new RPG_Item[]{
         RPG_Items_List.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_Items_List.ARMORS.SCALE_MAIL,
         RPG_Items_List.THIEVES_TOOLS,
         RPG_Items_List.DUNGEONEERS_PACK
      },
      MANA_HALFCASTER,
      3
   );
   // BARBARIAN
   public static RPG_Class BARBARIAN = new RPG_Class(
      "Barbarian",
      12,
      new int[]{4,8,12,16,19},
      new int[]{},
      new RPG_Item[]{
         RPG_Items_List.PLACEHOLDER_MARTIAL,
         RPG_Items_List.EXPLORERS_PACK
      }
   );
   // BARD
   public static RPG_Spellcaster BARD = new RPG_Spellcaster(
      "Bard",
      8,
      new int[]{4,8,12,16,19},
      new int[]{5,1,4},
      new RPG_Item[]{
         RPG_Items_List.MARTIAL_WEAPONS.RAPIER,
         RPG_Items_List.ENTERTAINERS_PACK,
         RPG_Items_List.PLACEHOLDER_INSTRUMENT,
         RPG_Items_List.ARMORS.LEATHER_ARMOR,
      }
   );
   // CLERIC
   public static RPG_Spellcaster CLERIC = new RPG_Spellcaster(
      "Cleric",
      8,
      new int[]{4,8,12,16,19},
      new int[]{4,2,0},
      new RPG_Item[]{
         RPG_Items_List.SIMPLE_WEAPONS.MACE,
         RPG_Items_List.ARMORS.SCALE_MAIL,
         RPG_Items_List.ARMORS.SHIELD,
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.PLACEHOLDER_SYMBOL
      },
      4
   );
   // DRUID
   public static RPG_Spellcaster DRUID = new RPG_Spellcaster(
      "Druid",
      8,
      new int[]{4,8,12,16,19},
      new int[]{4,5,3},
      new RPG_Item[]{
         RPG_Items_List.ARMORS.SHIELD,
         RPG_Items_List.MARTIAL_WEAPONS.LONGSWORD,
         RPG_Items_List.ARMORS.LEATHER_ARMOR,
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.PLACEHOLDER_DRUIDIC
      },
      4
   );
   // FIGHTER
   public static RPG_Class FIGHTER = new RPG_Class(
      "Fighter",
      10,
      new int[]{4,6,8,12,14,16,19},
      new int[]{},
      new RPG_Item[]{
         RPG_Items_List.PLACEHOLDER_MARTIAL,
         RPG_Items_List.ARMORS.SHIELD,
         RPG_Items_List.ARMORS.CHAIN_MAIL,
         RPG_Items_List.EXPLORERS_PACK
      }
   );
   // MONK
   public static RPG_Spellcaster MONK = new RPG_Spellcaster(
      "Monk",
      8,
      new int[]{4,8,12,16,19},
      new int[]{1,4,2},
      new RPG_Item[]{
         RPG_Items_List.PLACEHOLDER_SIMPLE,
         RPG_Items_List.EXPLORERS_PACK,
      },
      MANA_HALFCASTER,
      4
   );
   // PALADIN
   public static RPG_Spellcaster PALADIN = new RPG_Spellcaster(
      "Paladin",
      10,
      new int[]{4,8,12,16,19},
      new int[]{0,5,2},
      new RPG_Item[]{
         RPG_Items_List.PLACEHOLDER_MARTIAL,
         RPG_Items_List.ARMORS.SHIELD,
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.ARMORS.CHAIN_MAIL,
         RPG_Items_List.PLACEHOLDER_SYMBOL
      },
      MANA_HALFCASTER
   );
   // RANGER
   public static RPG_Spellcaster RANGER = new RPG_Spellcaster(
      "Ranger",
      10,
      new int[]{4,8,12,16,19},
      new int[]{1,4,2},
      new RPG_Item[]{
         RPG_Items_List.ARMORS.SCALE_MAIL,
         RPG_Items_List.MARTIAL_WEAPONS.LONGBOW,
         RPG_Items_List.EXPLORERS_PACK
      },
      MANA_HALFCASTER,
      4
   );
   // ROGUE
   public static RPG_Class ROGUE = new RPG_Class(
      "Rogue",
      8,
      new int[]{4,8,10,12,16,19},
      new int[]{1,5,3},
      new RPG_Item[]{
         RPG_Items_List.MARTIAL_WEAPONS.RAPIER,
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.ARMORS.LEATHER_ARMOR,
         RPG_Items_List.THIEVES_TOOLS
      }
   );
   // SORCERER
   public static RPG_Spellcaster SORCERER = new RPG_Spellcaster(
      "Sorcerer",
      6,
      new int[]{4,8,12,16,19},
      new int[]{5,2,4},
      new RPG_Item[]{
         RPG_Items_List.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_Items_List.PLACEHOLDER_ARCANE,
         RPG_Items_List.EXPLORERS_PACK,
      }
   );
   // WARLOCK
   public static RPG_Spellcaster WARLOCK = new RPG_Spellcaster(
      "Warlock",
      8,
      new int[]{4,8,12,16,19},
      new int[]{5,2,4},
      new RPG_Item[]{
         RPG_Items_List.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_Items_List.PLACEHOLDER_ARCANE,
         RPG_Items_List.DUNGEONEERS_PACK,
         RPG_Items_List.ARMORS.LEATHER_ARMOR
      }
   );
   // WIZARD
   public static RPG_Spellcaster WIZARD = new RPG_Spellcaster(
      "Wizard",
      6,
      new int[]{4,8,12,16,19},
      new int[]{3,1,4},
      new RPG_Item[]{
         RPG_Items_List.SIMPLE_WEAPONS.QUARTERSTAFF,
         RPG_Items_List.PLACEHOLDER_ARCANE,
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.SPELLBOOK
      },
      3
   );
   
   // FULL CLASS MAP
   public static HashMap<String, RPG_Class> CLASSES = new HashMap(13);
   public RPG_Classes_List(){ // noooooo i dont wanna make a constructor nooooooo
      // CLASSES.put("ARTIFICER",ARTIFICER);
      CLASSES.put("BARBARIAN",BARBARIAN);
      CLASSES.put("BARD",BARD);
      CLASSES.put("CLERIC",CLERIC);
      CLASSES.put("DRUID",DRUID);
      CLASSES.put("FIGHTER",FIGHTER);
      CLASSES.put("MONK",MONK);
      CLASSES.put("PALADIN",PALADIN);
      CLASSES.put("RANGER",RANGER);
      CLASSES.put("ROGUE",ROGUE);
      CLASSES.put("SORCERER",SORCERER);
      CLASSES.put("WARLOCK",WARLOCK);
      CLASSES.put("WIZARD",WIZARD);
   }
}
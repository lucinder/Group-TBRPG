/** 
@author Lucinder
@file RPG_Classes_List.java
Handler file for all SRD classes and their features.
DO NOT MODIFY UNLESS YOU KNOW WHAT YOU'RE DOING.
**/
import java.util.HashMap;
public class RPG_Classes_List{
   // SOURCE: https://5e.tools/classes.html
   public static int[] MANA_HALFCASTER = {0,5,5,10,10,15,15,20,20,25,25,30,30,35,35,40,40,45,45,50}; // HALF CASTER MANA DISTRIBUTION
   public static int[] MANA_MONK = {0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}; // MONK MANA DISTRIBUTION
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
         RPG_Items_List.EXPLORERS_PACK,
         RPG_Items_List.ARMORS.SHIELD,
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

=======
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Druidic",
            "You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden messages. You and others who know this language automatically spot such a message. Others spot the message\'s presence with a successful DC 15 Wisdom (Perception) check but can't decipher it without magic.",
            1
         ),
         new RPG_Class.ClassFeature(
            "Wild Shape",
            "Starting at 2nd level, you can use your action to magically assume the shape of a beast that you have seen before. You can use this feature twice. You regain expended uses when you finish a short or long rest.\nYour druid level determines the beasts you can transform into, as shown in the Beast Shapes table. At 2nd level, for example, you can transform into any beast that has a challenge rating of 1/4 or lower that doesn\'t have a flying or swimming speed.\nBeast Shapes\nLevel	Max. CR	Limitations	                  Example\n2nd	1/4	   No flying or swimming speed   Wolf\n4th	1/2	   No flying speed	            Crocodile\n8th	1	      �	                           Giant eagle\n\nYou can stay in a beast shape for a number of hours equal to half your druid level (rounded down). You then revert to your normal form unless you expend another use of this feature. You can revert to your normal form earlier by using a bonus action on your turn. You automatically revert if you fall unconscious, drop to 0 hit points, or die.\nWhile you are transformed, the following rules apply:\n * Your game statistics are replaced by the statistics of the beast, but you retain your alignment, personality, and Intelligence, Wisdom, and Charisma scores. You also retain all of your skill and saving throw proficiencies, in addition to gaining those of the creature. If the creature has the same proficiency as you and the bonus in its stat block is higher than yours, use the creature\'s bonus instead of yours. If the creature has any legendary or lair actions, you can\'t use them.\n * When you transform, you assume the beast\'s hit points and Hit Dice. When you revert to your normal form, you return to the number of hit points you had before you transformed. However, if you revert as a result of dropping to 0 hit points, any excess damage carries over to your normal form. For example, if you take 10 damage in animal form and have only 1 hit point left, you revert and take 9 damage. As long as the excess damage doesn\'t reduce your normal form to 0 hit points, you aren\'t knocked unconscious.\n * You can\'t cast spells, and your ability to speak or take any action that requires hands is limited to the capabilities of your beast form. Transforming doesn\'t break your concentration on a spell you\'ve already cast, however, or prevent you from taking actions that are part of a spell, such as call lightning, that you\'ve already cast.\n * You retain the benefit of any features from your class, race, or other source and can use them if the new form is physically capable of doing so. However, you can\'t use any of your special senses, such as darkvision, unless your new form also has that sense.\n * You choose whether your equipment falls to the ground in your space, merges into your new form, or is worn by it. Worn equipment functions as normal, but the DM decides whether it is practical for the new form to wear a piece of equipment, based on the creature\'s shape and size. Your equipment doesn\'t change size or shape to match the new form, and any equipment that the new form can\'t wear must either fall to the ground or Merge with it. Equipment that merges with the form has no effect until you leave the form.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Timeless Body",
            "Starting at 18th level, the primal magic that you wield causes you to age more slowly. For every 10 years that pass, your body ages only 1 year.",
            18
         ),
         new RPG_Class.ClassFeature(
            "Beast Spells",
            "Beginning at 18th level, you can cast many of your druid spells in any shape you assume using Wild Shape. You can perform the somatic and verbal components of a druid spell while in a beast shape, but you aren\'t able to provide material components.",
            18
         ),
         new RPG_Class.ClassFeature(
            "Archdruid",
            "At 20th level, you can use your Wild Shape an unlimited number of times.\nAdditionally, you can ignore the verbal and somatic components of your druid spells, as well as any material components that lack a cost and aren\'t consumed by a spell. You gain this benefit in both your normal shape and your beast shape from Wild Shape.",
            20
         )
      },

      new RPG_Item[]{
         RPG_Items_List.ARMORS.SHIELD,
         RPG_Items_List.MARTIAL_WEAPONS.SCIMITAR,
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
      MANA_MONK,
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
      CLASSES.put("ARTIFICER",ARTIFICER);
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
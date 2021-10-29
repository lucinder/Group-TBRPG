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
   public static int[] MANA_THIRDCASTER = {}; // [WIP] THIRD CASTER MANA DISTRIBUTION
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
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Magical Tinkering",
            "At 1st level, you learn how to invest a spark of magic into mundane objects. To use this ability, you must have thieves\' tools or artisan\'s tools in hand. You then touch a Tiny nonmagical object as an action and give it one of the following magical properties of your choice:\n * The object sheds bright light in a 5-foot radius and dim light for an additional 5 feet.\n * Whenever tapped by a creature, the object emits a recorded message that can be heard up to 10 feet away. You utter the message when you bestow this property on the object, and the recording can be no more than 6 seconds long.\n * The object continuously emits your choice of an odor or a nonverbal sound (wind, waves, chirping, or the like). The chosen phenomenon is perceivable up to 10 feet away.\n * A static visual effect appears on one of the object\'s surfaces. This effect can be a picture, up to 25 words of text, lines and shapes, or a mixture of these elements, as you like.\nThe chosen property lasts indefinitely. As an action, you can touch the object and end the property early.\nYou can bestow magic on multiple objects, touching one object each time you use this feature, though a single object can only bear one property at a time. The maximum number of objects you can affect with this feature at one time is equal to your Intelligence modifier (minimum of one object). If you try to exceed your maximum, the oldest property immediately ends, and then the new property applies.",
            1
         ),
         // SUBCLASS features, and features that UNLOCK subclasses (i.e. Artificer 3rd level- "Artificer Specialist", are not included!
         new RPG_Class.ClassFeature(
            "The Right Tool for the Job",
            "At 3rd level, you learn how to produce exactly the tool you need: with thieves\' tools or artisan\'s tools in hand, you can magically create one set of artisan\'s tools in an unoccupied space within 5 feet of you. This creation requires 1 hour of uninterrupted work, which can coincide with a short or long rest. Though the product of magic, the tools are nonmagical, and they vanish when you use this feature again.",
            3
         ),
         // ABILITY SCORE IMPROVEMENTS are also not included in the class feature list!
         new RPG_Class.ClassFeature(
            "Tool Expertise",
            "Starting at 6th level, your proficiency bonus is doubled for any ability check you make that uses your proficiency with a tool.",
            6
         ),
         new RPG_Class.ClassFeature(
            "Flash of Genius",
            "Starting at 7th level, you gain the ability to come up with solutions under pressure. When you or another creature you can see within 30 feet of you makes an ability check or a saving throw, you can use your reaction to add your Intelligence modifier to the roll.\nYou can use this feature a number of times equal to your Intelligence modifier (minimum of once). You regain all expended uses when you finish a long rest.",
            7
         ),
         new RPG_Class.ClassFeature(
            "Magic Item Adept",
            "When you reach 10th level, you achieve a profound understanding of how to use and make magic items:\n * You can attune to up to four magic items at once.\n * If you craft a magic item with a rarity of common or uncommon, it takes you a quarter of the normal time, and it costs you half as much of the usual gold.",
            10
         ),
         new RPG_Class.ClassFeature(
            "Spell-Storing Item",
            "At 11th level, you learn how to store a spell in an object. Whenever you finish a long rest, you can touch one simple or martial weapon or one item that you can use as a spellcasting focus, and you store a spell in it, choosing a 1st- or 2nd-level spell from the artificer spell list that requires 1 action to cast (you needn't have it prepared).\nWhile holding the object, a creature can take an action to produce the spell's effect from it, using your spellcasting ability modifier. If the spell requires concentration, the creature must concentrate. The spell stays in the object until it\'s been used a number of times equal to twice your Intelligence modifier (minimum of twice) or until you use this feature again to store a spell in an object.",
            11
         ),
         new RPG_Class.ClassFeature(
            "Magic Item Savant",
            "At 14th level, your skill with magic items deepens more:\n * You can attune to up to five magic items at once.\n * You ignore all class, race, spell, and level requirements on attuning to or using a magic item.",
            14
         ),
         new RPG_Class.ClassFeature(
            "Magic Item Master",
            "Starting at 18th level, you can attune to up to six magic items at once.",
            18
         ),
         new RPG_Class.ClassFeature(
            "Soul of Artifice",
            "At 20th level, you develop a mystical connection to your magic items, which you can draw on for protection:\n * You gain a +1 bonus to all saving throws per magic item you are currently attuned to.\n * If you\'re reduced to 0 hit points but not killed outright, you can use your reaction to end one of your artificer infusions, causing you to drop to 1 hit point instead of 0.",
            20
         )
      },
      new RPG_Item[]{ 
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_ITEMS_LIST.ARMORS.SCALE_MAIL,
         RPG_ITEMS_LIST.THIEVES_TOOLS,
         RPG_ITEMS_LIST.DUNGEONEERS_PACK
      },
      MANA_HALFCASTER,
      "Intelligence"
   );
   // BARBARIAN
   public static RPG_Class BARBARIAN = new RPG_Class(
      "Barbarian",
      12,
      new int[]{4,8,12,16,19},
      new int[]{},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Rage",
            "In battle, you fight with primal ferocity. On your turn, you can enter a rage as a bonus action.\nWhile raging, you gain the following benefits if you aren\'t wearing heavy armor:\n * You have advantage on Strength checks and Strength saving throws.\n * When you make a melee weapon attack using Strength, you gain a +2 bonus to the damage roll. This bonus increases as you level.\n * You have resistance to bludgeoning, piercing, and slashing damage.\nIf you are able to cast spells, you can\'t cast them or concentrate on them while raging.\nYour rage lasts for 1 minute. It ends early if you are knocked unconscious or if your turn ends and you haven\'t attacked a hostile creature since your last turn or taken damage since then. You can also end your rage on your turn as a bonus action.\nOnce you have raged the maximum number of times for your barbarian level, you must finish a long rest before you can rage again. You may rage 2 times at 1st level, 3 at 3rd, 4 at 6th, 5 at 12th, and 6 at 17th.",
            1
         ),
         new RPG_Class.ClassFeature(
           "Unarmored Defense",
           "While you are not wearing any armor, your Armor Class equals 10 + your Dexterity modifier + your Constitution modifier. You can use a shield and still gain this benefit.",
           1 
         ),
         new RPG_Class.ClassFeature(
            "Danger Sense",
            "At 2nd level, you gain an uncanny sense of when things nearby aren\'t as they should be, giving you an edge when you dodge away from danger. You have advantage on Dexterity saving throws against effects that you can see, such as traps and spells. To gain this benefit, you can\'t be blinded, deafened, or incapacitated.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Reckless Attack",
            "Starting at 2nd level, you can throw aside all concern for defense to attack with fierce desperation. When you make your first attack on your turn, you can decide to attack recklessly. Doing so gives you advantage on melee weapon attack rolls using Strength during this turn, but attack rolls against you have advantage until your next turn.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Extra Attack",
            "Beginning at 5th level, you can attack twice, instead of once, whenever you take the Attack action on your turn.",
            5
         ),
         new RPG_Class.ClassFeature(
            "Fast Movement",
            "Starting at 5th level, your speed increases by 10 feet while you aren\'t wearing heavy armor.",
            5
         ),
         new RPG_Class.ClassFeature(
            "Feral Instinct",
            "By 7th level, your instincts are so honed that you have advantage on initiative rolls.Additionally, if you are surprised at the beginning of combat and aren\'t incapacitated, you can act normally on your first turn, but only if you enter your rage before doing anything else on that turn.",
            7
         ),
         new RPG_Class.ClassFeature(
            "Brutal Critical",
            "Beginning at 9th level, you can roll one additional weapon damage die when determining the extra damage for a critical hit with a melee attack.\nThis increases to two additional dice at 13th level and three additional dice at 17th level.",
            9
         ),
         new RPG_Class.ClassFeature(
            "Relentless Rage",
            "Starting at 11th level, your rage can keep you fighting despite grievous wounds. If you drop to 0 hit points while you\'re raging and don\'t die outright, you can make a DC 10 Constitution saving throw. If you succeed, you drop to 1 hit point instead.\nEach time you use this feature after the first, the DC increases by 5. When you finish a short or long rest, the DC resets to 10.",
            11
         ),
         new RPG_Class.ClassFeature(
            "Presistent Rage",
            "Beginning at 15th level, your rage is so fierce that it ends early only if you fall unconscious or if you choose to end it.",
            15
         ),
         new RPG_Class.ClassFeature(
            "Indomitable Might",
            "Beginning at 18th level, if your total for a Strength check is less than your Strength score, you can use that score in place of the total.",
            18
         ),
         new RPG_Class.ClassFeature(
            "Primal Champion",
            "At 20th level, you embody the power of the wilds. Your Strength and Constitution scores increase by 4. Your maximum for those scores is now 24.",
            20
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.PLACEHOLDER_MARTIAL,
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.EXPLORERS_PACK
      }
   );
   // BARD
   public static RPG_Spellcaster BARD = new RPG_Spellcaster(
      "Bard",
      8,
      new int[]{4,8,12,16,19},
      new int[]{5,1,4},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Bardic Inspiration",
            "You can inspire others through stirring words or music. To do so, you use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you. That creature gains one Bardic Inspiration die, a d6.\nOnce within the next 10 minutes, the creature can roll the die and add the number rolled to one ability check, attack roll, or saving throw it makes. The creature can wait until after it rolls the d20 before deciding to use the Bardic Inspiration die, but must decide before the DM says whether the roll succeeds or fails. Once the Bardic Inspiration die is rolled, it is lost. A creature can have only one Bardic Inspiration die at a time.\nYou can use this feature a number of times equal to your Charisma modifier (a minimum of once). You regain any expended uses when you finish a long rest.\nYour Bardic Inspiration die changes when you reach certain levels in this class. The die becomes a d8 at 5th level, a d10 at 10th level, and a d12 at 15th level.",
            1
         ),
         new RPG_Class.ClassFeature(
            "Jack of All Trades",
            "Starting at 2nd level, you can add half your proficiency bonus, rounded down, to any ability check you make that doesn\'t already include your proficiency bonus.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Song of Rest",
            "Beginning at 2nd level, you can use soothing music or oration to help revitalize your wounded allies during a short rest. If you or any friendly creatures who can hear your performance regain hit points by spending Hit Dice at the end of the short rest, each of those creatures regains an extra 1d6 hit points.\nThe extra hit points increase when you reach certain levels in this class: to 1d8 at 9th level, to 1d10 at 13th level, and to 1d12 at 17th level.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Expertise",
            "At 3rd level, choose two of your skill proficiencies. Your proficiency bonus is doubled for any ability check you make that uses either of the chosen proficiencies.\nAt 10th level, you can choose another two skill proficiencies to gain this benefit.",
            3  
         ),
         new RPG_Class.ClassFeature(
            "Font of Inspiration",
            "Beginning when you reach 5th level, you regain all of your expended uses of Bardic Inspiration when you finish a short or long rest.",
            5
         ),
         new RPG_Class.ClassFeature(
            "Countercharm",
            "At 6th level, you gain the ability to use musical notes or words of power to disrupt mind-influencing effects. As an action, you can start a performance that lasts until the end of your next turn. During that time, you and any friendly creatures within 30 feet of you have advantage on saving throws against being frightened or charmed. A creature must be able to hear you to gain this benefit. The performance ends early if you are incapacitated or silenced or if you voluntarily end it (no action required).",
            6
         ),
         new RPG_Class.ClassFeature(
            "Magical Secrets",
            "By 10th level, you have plundered magical knowledge from a wide spectrum of disciplines. Choose two spells from any classes, including this one. A spell you choose must be of a level you can cast, as shown on the Bard table, or a cantrip.\nThe chosen spells count as bard spells for you and are included in the number in the Spells Known column of the Bard table.\nYou learn two additional spells from any classes at 14th level and again at 18th level.",
            10
         ),
         new RPG_Class.ClassFeature(
            "Superior Inspiration",
            "At 20th level, when you roll initiative and have no uses of Bardic Inspiration left, you regain one use.",
            20
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.RAPIER,
         RPG_ITEMS_LIST.ENTERTAINERS_PACK,
         RPG_ITEMS_LIST.PLACEHOLDER_INSTRUMENT,
         RPG_ITEMS_LIST.ARMORS.LEATHER_ARMOR,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER
      }
   );
   // CLERIC
   public static RPG_Spellcaster CLERIC = new RPG_Spellcaster(
      "Cleric",
      8,
      new int[]{4,8,12,16,19},
      new int[]{4,2,0},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Channel Divinity",
            "At 2nd level, you gain the ability to channel divine energy directly from your deity, using that energy to fuel magical effects. You start with two such effects: Turn Undead and an effect determined by your domain. Some domains grant you additional effects as you advance in levels, as noted in the domain description.\nWhen you use your Channel Divinity, you choose which effect to create. You must then finish a short or long rest to use your Channel Divinity again.\nSome Channel Divinity effects require saving throws. When you use such an effect from this class, the DC equals your cleric spell save DC.\nBeginning at 6th level, you can use your Channel Divinity twice between rests, and beginning at 18th level, you can use it three times between rests. When you finish a short or long rest, you regain your expended uses.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Turn Undead",
            "As an action, you present your holy symbol and speak a prayer censuring the undead. Each undead that can see or hear you within 30 feet of you must make a Wisdom saving throw. If the creature fails its saving throw, it is turned for 1 minute or until it takes any damage.\nA turned creature must spend its turns trying to move as far away from you as it can, and it can\'t willingly move to a space within 30 feet of you. It also can\'t take reactions. For its action, it can use only the Dash action or try to escape from an effect that prevents it from moving. If there\'s nowhere to move, the creature can use the Dodge action.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Destroy Undead",
            "Starting at 5th level, when an undead of CR 1/2 or lower fails its saving throw against your Turn Undead feature, the creature is instantly destroyed.\nThe maximum CR of affected undead increases to CR 1 at 8th level, CR 2 at 11th level, CR 3 at 14th level, and CR 4 at 17th level.",
            5
         ),
         new RPG_Class.ClassFeature(
            "Divine Intervention",
            "Beginning at 10th level, you can call on your deity to intervene on your behalf when your need is great.\nImploring your deity\'s aid requires you to use your action. Describe the assistance you seek, and roll percentile dice. If you roll a number equal to or lower than your cleric level, your deity intervenes. The DM chooses the nature of the intervention; the effect of any cleric spell or cleric domain spell would be appropriate. If your deity intervenes, you can\'t use this feature again for 7 days. Otherwise, you can use it again after you finish a long rest.\nAt 20th level, your call for intervention succeeds automatically, no roll required.",
            10
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.MACE,
         RPG_ITEMS_LIST.ARMORS.SCALE_MAIL,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.ARMORS.SHIELD,
         RPG_ITEMS_LIST.PLACEHOLDER_SYMBOL
      },
      new int[]{3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5}
   );
   // DRUID
   public static RPG_Spellcaster DRUID = new RPG_Spellcaster(
      "Druid",
      8,
      new int[]{4,8,12,16,19},
      new int[]{4,5,3},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Druidic",
            "You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden messages. You and others who know this language automatically spot such a message. Others spot the message\'s presence with a successful DC 15 Wisdom (Perception) check but can't decipher it without magic.",
            1
         ),
         new RPG_Class.ClassFeature(
            "Wild Shape",
            "Starting at 2nd level, you can use your action to magically assume the shape of a beast that you have seen before. You can use this feature twice. You regain expended uses when you finish a short or long rest.\nYour druid level determines the beasts you can transform into, as shown in the Beast Shapes table. At 2nd level, for example, you can transform into any beast that has a challenge rating of 1/4 or lower that doesn\'t have a flying or swimming speed.\nBeast Shapes\nLevel	Max. CR	Limitations	                  Example\n2nd	1/4	   No flying or swimming speed   Wolf\n4th	1/2	   No flying speed	            Crocodile\n8th	1	      —	                           Giant eagle\n\nYou can stay in a beast shape for a number of hours equal to half your druid level (rounded down). You then revert to your normal form unless you expend another use of this feature. You can revert to your normal form earlier by using a bonus action on your turn. You automatically revert if you fall unconscious, drop to 0 hit points, or die.\nWhile you are transformed, the following rules apply:\n * Your game statistics are replaced by the statistics of the beast, but you retain your alignment, personality, and Intelligence, Wisdom, and Charisma scores. You also retain all of your skill and saving throw proficiencies, in addition to gaining those of the creature. If the creature has the same proficiency as you and the bonus in its stat block is higher than yours, use the creature\'s bonus instead of yours. If the creature has any legendary or lair actions, you can\'t use them.\n * When you transform, you assume the beast\'s hit points and Hit Dice. When you revert to your normal form, you return to the number of hit points you had before you transformed. However, if you revert as a result of dropping to 0 hit points, any excess damage carries over to your normal form. For example, if you take 10 damage in animal form and have only 1 hit point left, you revert and take 9 damage. As long as the excess damage doesn\'t reduce your normal form to 0 hit points, you aren\'t knocked unconscious.\n * You can\'t cast spells, and your ability to speak or take any action that requires hands is limited to the capabilities of your beast form. Transforming doesn\'t break your concentration on a spell you\'ve already cast, however, or prevent you from taking actions that are part of a spell, such as call lightning, that you\'ve already cast.\n * You retain the benefit of any features from your class, race, or other source and can use them if the new form is physically capable of doing so. However, you can\'t use any of your special senses, such as darkvision, unless your new form also has that sense.\n * You choose whether your equipment falls to the ground in your space, merges into your new form, or is worn by it. Worn equipment functions as normal, but the DM decides whether it is practical for the new form to wear a piece of equipment, based on the creature\'s shape and size. Your equipment doesn\'t change size or shape to match the new form, and any equipment that the new form can\'t wear must either fall to the ground or merge with it. Equipment that merges with the form has no effect until you leave the form.",
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
         RPG_ITEMS_LIST.ARMORS.SHIELD,
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.SCIMITAR,
         RPG_ITEMS_LIST.ARMORS.LEATHER_ARMOR,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.PLACEHOLDER_DRUIDIC
      }
   );
   // FIGHTER
   public static RPG_Class FIGHTER = new RPG_Class(
      "Fighter",
      10,
      new int[]{4,6,8,12,14,16,19},
      new int[]{},
      new RPG_Class.ClassFeature[]{
         // FIGHTING STYLE is not listed
         new RPG_Class.ClassFeature(
            "Second Wind",
            "You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d10 + your fighter level.\nOnce you use this feature, you must finish a short or long rest before you can use it again.",
            1
         ),
         new RPG_Class.ClassFeature(
            "Action Surge",
            "Starting at 2nd level, you can push yourself beyond your normal limits for a moment. On your turn, you can take one additional action.\nOnce you use this feature, you must finish a short or long rest before you can use it again. Starting at 17th level, you can use it twice before a rest, but only once on the same turn.",
            2
         ),
         new RPG_Class.ClassFeature(
            "Extra Attack",
            "Beginning at 5th level, you can attack twice, instead of once, whenever you take the Attack action on your turn.\nThe number of attacks increases to three when you reach 11th level in this class and to four when you reach 20th level in this class.",
            5
         ),
         new RPG_Class.ClassFeature(
            "Indomitable",
            "Beginning at 9th level, you can reroll a saving throw that you fail. If you do so, you must use the new roll, and you can\'t use this feature again until you finish a long rest.\nYou can use this feature twice between long rests starting at 13th level and three times between long rests starting at 17th level.",
            9
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.PLACEHOLDER_MARTIAL,
         RPG_ITEMS_LIST.ARMORS.SHIELD,
         RPG_ITEMS_LIST.ARMORS.CHAIN_MAIL,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_ITEMS_LIST.EXPLORERS_PACK
      }
   );
   // MONK
   public static RPG_Class MONK = new RPG_Class(
      "Monk",
      8,
      new int[]{4,8,12,16,19},
      new int[]{1,4,2},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Unarmored Defense",
            "",
            1
         ),
         new RPG_Class.ClassFeature(
            "Martial Arts",
            "",
            1
         ),
         new RPG_Class.ClassFeature(
            "Ki",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Flurry of Blows",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Patient Defense",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Step of the Wind",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Unarmored Movement",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Deflect Missiles",
            "",
            3
         ),
         new RPG_Class.ClassFeature(
            "Slow Fall",
            "",
            4
         ),
         new RPG_Class.ClassFeature(
            "Extra Attack",
            "",
            5
         ),
         new RPG_Class.ClassFeature(
            "Stunning Strike",
            "",
            5
         ),
         new RPG_Class.ClassFeature(
            "Ki-Empowered Strikes",
            "",
            6
         ),
         new RPG_Class.ClassFeature(
            "Evasion",
            "",
            7
         ),
         new RPG_Class.ClassFeature(
            "Stillness of Mind",
            "",
            7
         ),
         new RPG_Class.ClassFeature(
            "Purity of Body",
            "",
            10
         ),
         new RPG_Class.ClassFeature(
            "Tongue of the Sun and Moon",
            "",
            13
         ),
         new RPG_Class.ClassFeature(
            "Diamond Soul",
            "",
            14
         ),
         new RPG_Class.ClassFeature(
            "Timeless Body",
            "",
            15
         ),
         new RPG_Class.ClassFeature(
            "Empty Body",
            "",
            18
         ),
         new RPG_Class.ClassFeature(
            "Perfect Self",
            "",
            20
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
      }
   );
   // PALADIN
   public static RPG_Spellcaster PALADIN = new RPG_Spellcaster(
      "Paladin",
      10,
      new int[]{4,8,12,16,19},
      new int[]{0,5,2},
      new RPG_Class.ClassFeature[]{
      
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.PLACEHOLDER_MARTIAL,
         RPG_ITEMS_LIST.ARMORS.SHIELD,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.JAVELIN,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.JAVELIN,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.JAVELIN,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.JAVELIN,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.JAVELIN,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.ARMORS.CHAIN_MAIL,
         RPG_ITEMS_LIST.PLACEHOLDER_SYMBOL
      },
      MANA_HALFCASTER
   );
   // RANGER
   public static RPG_Spellcaster RANGER = new RPG_Spellcaster(
      "Ranger",
      10,
      new int[]{4,8,12,16,19},
      new int[]{1,4,2},
      new RPG_Class.ClassFeature[]{
      
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.ARMORS.SCALE_MAIL,
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.SHORTSWORD,
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.SHORTSWORD,
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.LONGBOW,
         RPG_ITEMS_LIST.EXPLORERS_PACK
      },
      MANA_HALFCASTER,
      "Wisdom"
   );
   // ROGUE
   public static RPG_Class ROGUE = new RPG_Class(
      "Rogue",
      8,
      new int[]{4,8,10,12,16,19},
      new int[]{1,5,3},
      new RPG_Class.ClassFeature[]{
      
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.MARTIAL_WEAPONS.RAPIER,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.SHORTBOW,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.ARMORS.LEATHER_ARMOR,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER,
         RPG_ITEMS_LIST.THIEVES_TOOLS
      }
   );
   // SORCERER
   public static RPG_Spellcaster SORCERER = new RPG_Spellcaster(
      "Sorcerer",
      6,
      new int[]{4,8,12,16,19},
      new int[]{5,2,4},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Font of Magic",
            "",
            2
         ),
         new RPG_Class.ClassFeature(
            "Metamagic", // 1st metamagic
            "At 3rd level, you gain the ability to twist your spells to suit your needs. You gain two of the following Metamagic options of your choice. You gain another one at 10th and 17th level.\nYou can use only one Metamagic option on a spell when you cast it, unless otherwise noted.",
            3
         ),
         new RPG_Class.ClassFeature(
            "Metamagic", // 2nd metamagic
            "At 3rd level, you gain the ability to twist your spells to suit your needs. You gain two of the following Metamagic options of your choice. You gain another one at 10th and 17th level.\nYou can use only one Metamagic option on a spell when you cast it, unless otherwise noted.",
            3
         ),
         new RPG_Class.ClassFeature(
            "Metamagic", // 3rd metamagic
            "At 10th level, you learn an additional metamagic option.",
            10
         ),
         new RPG_Class.ClassFeature(
            "Sorcerous Restoration",
            "At 20th level, you regain 4 expended sorcery points whenever you finish a short rest.",
            20
         ),
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_ITEMS_LIST.PLACEHOLDER_ARCANE,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER
      }
   );
   // WARLOCK
   public static RPG_Spellcaster WARLOCK = new RPG_Spellcaster(
      "Warlock",
      8,
      new int[]{4,8,12,16,19},
      new int[]{5,2,4},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Eldritch Invocations",
            "In your study of occult lore, you have unearthed eldritch invocations, fragments of forbidden knowledge that imbue you with an abiding magical ability.\nAt 2nd level, you gain two eldritch invocations of your choice. A list of the available options can be found on the Optional Features page. When you gain certain warlock levels, you gain additional invocations of your choice, as shown in the Invocations Known column of the Warlock table:\n\nLevel | Invocations Known\n  1   | ---\n  2   |  2\n  3   |  2\n  4   |  2\n  5   |  3\n  6   |  3 \n  7   |  4\n  8   |  4\n  9   |  5\n  10  |  5\n  11  |  5\n  12  |  6\n  13  |  6\n  14  |  6\n  15  |  7\n  16  |  7\n  17  |  7\n  18  |  8\n  19  |  8\n  20  |  8\nAdditionally, when you gain a level in this class, you can choose one of the invocations you know and replace it with another invocation that you could learn at that level.\nIf an eldritch invocation has prerequisites, you must meet them to learn it. You can learn the invocation at the same time that you meet its prerequisites. A level prerequisite refers to your level in this class.",
            2
         ),
         new RPG_Class.ClassFeature( // use special logic for this similar to fighting style selection
            "Pact Boon",
            "At 3rd level, your otherworldly patron bestows a gift upon you for your loyal service. You gain one of the following features of your choice.",
            3
         ),
         new RPG_Class.ClassFeature(
            "Mystic Arcanum",
            "At 11th level, your patron bestows upon you a magical secret called an arcanum. Choose one 6th-level spell from the warlock spell list as this arcanum.\nYou can cast your arcanum spell once without expending a spell slot. You must finish a long rest before you can do so again.\nAt higher levels, you gain more warlock spells of your choice that can be cast in this way: one 7th-level spell at 13th level, one 8th-level spell at 15th level, and one 9th-level spell at 17th level. You regain all uses of your Mystic Arcanum when you finish a long rest.",
            11
         ),
         new RPG_Class.ClassFeature(
            "Eldritch Master",
            "At 20th level, you can draw on your inner reserve of mystical power while entreating your patron to regain expended spell slots. You can spend 1 minute entreating your patron for aid to regain all your expended spell slots from your Pact Magic feature. Once you regain spell slots with this feature, you must finish a long rest before you can do so again.",
            20
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.LIGHT_CROSSBOW,
         RPG_ITEMS_LIST.PLACEHOLDER_ARCANE,
         RPG_ITEMS_LIST.DUNGEONEERS_PACK,
         RPG_ITEMS_LIST.ARMORS.LEATHER_ARMOR,
         RPG_ITEMS_LIST.PLACEHOLDER_SIMPLE,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER,
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.DAGGER
      }
   );
   // WIZARD
   public static RPG_Spellcaster WIZARD = new RPG_Spellcaster(
      "Wizard",
      6,
      new int[]{4,8,12,16,19},
      new int[]{3,1,4},
      new RPG_Class.ClassFeature[]{
         new RPG_Class.ClassFeature(
            "Arcane Recovery",
            "You have learned to regain some of your magical energy by studying your spellbook. Once per day when you finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.\nFor example, if you\'re a 4th-level wizard, you can recover up to two levels worth of spell slots.\nYou can recover either a 2nd-level spell slot or two 1st-level spell slots.",
            1
         ),
         new RPG_Class.ClassFeature(
            "Spell Mastery",
            "At 18th level, you have achieved such mastery over certain spells that you can cast them at will. Choose a 1st-level wizard spell and a 2nd-level wizard spell that are in your spellbook. You can cast those spells at their lowest level without expending a spell slot when you have them prepared. If you want to cast either spell at a higher level, you must expend a spell slot as normal.\nBy spending 8 hours in study, you can exchange one or both of the spells you chose for different spells of the same levels.",
            18
         ),
         new RPG_Class.ClassFeature(
            "Signature Spells",
            "When you reach 20th level, you gain mastery over two powerful spells and can cast them with little effort. Choose two 3rd-level wizard spells in your spellbook as your signature spells. You always have these spells prepared, they don\'t count against the number of spells you have prepared, and you can cast each of them once at 3rd level without expending a spell slot. When you do so, you can\'t do so again until you finish a short or long rest.\nIf you want to cast either spell at a higher level, you must expend a spell slot as normal.",
            20
         )
      },
      new RPG_Item[]{
         RPG_ITEMS_LIST.SIMPLE_WEAPONS.QUARTERSTAFF,
         RPG_ITEMS_LIST.PLACEHOLDER_ARCANE,
         RPG_ITEMS_LIST.EXPLORERS_PACK,
         RPG_ITEMS_LIST.SPELLBOOK
      },
      "Intelligence"
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
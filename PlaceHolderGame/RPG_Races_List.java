import java.util.Map;
import java.util.HashMap;
public class RPG_Races_List{
   public static RPG_Race.RaceFeature DARKVISION = new RPG_Race.RaceFeature(
      "Darkvision",
      "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light."
   );
   public class STANDARD_RACES{
      public static RPG_Race HUMAN = new RPG_Race(
         "Human",
         new int[]{1, 1, 1, 1, 1, 1},
         new RPG_Race.RaceFeature[]{} // humans get no additional race features
      );
      public static RPG_Race ELF = new RPG_Race(
         "Elf",
         new int[]{0,2,0,0,0,0,1}, // +2 dex, +1 any other
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Fey Ancestry",
               "You have advantage on saving throws against being charmed, and magic can't put you to sleep."
            )
         }
      );
      public static RPG_Race DWARF = new RPG_Race(
         "Dwarf",
         new int[]{0,0,2,0,0,0,1}, // +2 con, +1 any other
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Dwarven Resilience",
               "You have advantage on saving throws against poison, and you have resistance to poison damage."
            )
         }
      );
      public static RPG_Race DRAGONBORN = new RPG_Race(
         "Dragonborn",
         new int[]{2,0,0,0,0,1}, // +2 str +1 cha
         new RPG_Race.RaceFeature[]{
            new RPG_Race.RaceFeature(
               "Draconic Ancestry",
               "You have draconic ancestry. Choose one type of dragon from the Draconic Ancestry table. Your breath weapon and damage resistance are determined by the dragon type, as shown in the table:\n\n  DRAGON  | DAMAGE TYPE | BREATH WEAPON\n =======================================\n  Black   |  Acid       |  Dex save\n  Blue    |  Lightning  |  Dex save\n  Brass   |  Fire       |  Dex save\n  Bronze  |  Lightning  |  Dex save\n  Copper  |  Acid       |  Dex save\n  Gold    |  Fire       |  Dex save\n  Green   |  Poison     |  Con save\n  Red     |  Fire       |  Dex save\n  Silver  |  Cold       |  Con save\n  White   |  Cold       |  Con save\n\nWhen you use your breath weapon, each creature in the area of the exhalation must make a saving throw, the type of which is determined by your draconic ancestry. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level, and 5d6 at 16th level.\nAfter you use your breath weapon, you can\'t use it again until you complete a short or long rest."
            ),
            new RPG_Race.RaceFeature(
               "Damage Resistance",
               "You have resistance to the damage type associated with your draconic ancestry."
            )
         }
      );
      public static RPG_Race GNOME = new RPG_Race(
         "Gnome",
         new int[]{0,0,0,2,0,0,1}, // int +2, any other +1
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Gnome Cunning",
               "You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic."
            )
         }
      );
      public static RPG_Race GOBLIN = new RPG_Race(
         "Goblin",
         new int[]{0,2,1,0,0,0}, // dex +2 con +1
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Nimble Escape",
               "You can take the Disengage or Hide action as a bonus action on each of your turns."
            )
         }
      );
      // Got rid of Half-Elves due to feature overlap
      public static RPG_Race HALF_ORC = new RPG_Race(
         "Half-Orc",
         new int[]{2,0,1,0,0,0}, // str +2, con +1
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature( // "[Character] did not succumb!"
               "Relentless Endurance",
               "When you are reduced to 0 hit points but not killed outright, you can drop to 1 hit point instead. You can\'t use this feature again until you finish a long rest."
            ),
            new RPG_Race.RaceFeature(
               "Savage Attacks",
               "When you score a critical hit with a melee weapon attack, you can roll one of the weapon's damage dice one additional time and add it to the extra damage of the critical hit."
            )
         }
      );
      public static RPG_Race HALFLING = new RPG_Race(
         "Halfling",
         new int[]{0,2,0,0,0,0,1}, // dex +2 any other +1
         new RPG_Race.RaceFeature[]{
            new RPG_Race.RaceFeature(
               "Lucky",
               "When you roll a 1 on an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll."
            ),
            new RPG_Race.RaceFeature(
               "Brave",
               "You have advantage on saving throws against being frightened."
            )
         }
      );
      public static RPG_Race TIEFLING = new RPG_Race(
         "Tiefling",
         new int[]{0,0,0,1,0,2}, // cha +2 int +1
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Hellish Resistance",
               "You have resistance to fire damage."
            ),
            new RPG_Race.RaceFeature(
               "Infernal Legacy", // REPLACE THE SPELLS HERE WITH SPELLS IN THIS SYSTEM!
               "You know the thaumaturgy cantrip. Once you reach 3rd level, you can cast the hellish rebuke spell as a 2nd-level spell; you must finish a long rest in order to cast the spell again using this trait. Once you reach 5th level, you can also cast the darkness spell; you must finish a long rest in order to cast the spell again using this trait. Charisma is your spellcasting ability for these spells."
            )
         }
      );
   }
   /** EXOTIC RACES- TO BE IMPLEMENTED
   public class EXOTIC_RACES{
      public static RPG_Race AARAKOCRA = new RPG_Race( // aarakocra = bird people
         "Aarakocra",
         new int[]{},
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race AASIMAR = new RPG_Race( // aasmiar = angel jr
         "Aasimar",
         new int[]{},
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race BUGBEAR = new RPG_Race(
         "Bugbear",
         new int[]{2,1,0,0,0,0}, // str +2 dex +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race CENTAUR = new RPG_Race(
         "Centaur",
         new int[]{2,0,0,0,1,0}, // str +2 wis +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race CHANGELING = new RPG_Race(
         "Changeling",
         new int[]{0,0,0,0,0,2,1}, // cha +2 any other +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race DHAMPIR = new RPG_Race( // dhampir = vampire jr
         "Dhampir",
         new int[]{0,0,0,0,0,0,2,1}, // any +2, any +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race FAIRY = new RPG_Race(
         "Fairy",
         new int[]{0,0,0,0,0,0,2,1}, // any +2, any +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race FIRBOLG = new RPG_Race(
         "Firbolg",
         new int[]{1,0,0,0,2,0}, // wis +2, str +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race GENASI = new RPG_Race( // genasi = genie jr, elemental focused
         "Genasi",
         new int[]{0,0,2,0,0,0,1}, // con +2, any other +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race GITHZERAI = new RPG_Race( // githzerai = psychic space elves
         "Githzerai",
         new int[]{0,0,0,1,2,0}, // wis +2 int +1
         new RPG_Race.RaceFeature[]{
         
         }
      );
      public static RPG_Race GITHYANKI = new RPG_Race( // githyanki = angry psychic space elves
         "Githyanki",
         new int[]{2,0,0,1,0,0}, // str +2 int +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race GOLIATH = new RPG_Race( // grung = frog people
         "Goliath",
         new int[]{2,0,1,0,0,0}, // str +2 con +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race GRUNG = new RPG_Race( // grung = frog people
         "Grung",
         new int[]{0,2,1,0,0,0}, // dex +2 con +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race HEXBLOOD = new RPG_Race( // hexbloods = witch types
         "Hexblood",
         new int[]{0,0,0,0,0,0,2,1}, // any +2, any +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race HOBGOBLIN = new RPG_Race(
         "Hobgoblin",
         new int[]{0,0,2,1,0,0}, // con +2, int +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race KALASHTAR = new RPG_Race( // kalashtar = people with ghost friends :)
         "Kalashtar",
         new int[]{0,0,0,0,2,1}, // wis +2 cha +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race KENKU = new RPG_Race( // kenku = flightless bird people
         "Kenku",
         new int[]{0,2,0,0,1,0}, // dex +2 wis +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race KOBOLD = new RPG_Race(
         "Kobold",
         new int[]{0,0,0,0,0,0,2,1}, // any +2 any +1
         new RPG_Race.RaceFeature[]{
            DARKVISION,
            new RPG_Race.RaceFeature(
               "Draconic Legacy",
               "You have advantage on saving throws to avoid or end the frightened condition on yourself."
            ),
            new RPG_Race.RaceFeature(
               "Draconic Roar",
               "As a bonus action, you let out a draconic roar at your enemies within 10 feet of you. Until the end of your next turn, you and your allies have advantage on attack rolls against any of those enemies who could hear the roar. You can use this trait a number of times equal to your proficiency bonus, and you regain all expended uses when you finish a long rest."
            )
         }
      );
      public static RPG_Race LEONIN = new RPG_Race( // leonin = lion people
         "Leonin",
         new int[]{1,0,2,0,0,0}, // con +2 str +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race LIZARDFOLK = new RPG_Race(
         "Lizardfolk",
         new int[]{0,0,2,0,1,0}, // con +2 wis +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race LOXODON = new RPG_Race( // loxodon = elephant people
         "Loxodon",
         new int[]{0,0,2,0,1,0}, // con +2 wis +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race MERFOLK = new RPG_Race(
         "Merfolk",
         new int[]{0,0,0,0,2,1}, // wis +2 cha +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race MINOTAUR = new RPG_Race(
         "Minotaur",
         new int[]{2,0,1,0,0,0}, // str +2 con +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race ORC = new RPG_Race(
         "Orc",
         new int[]{2,0,1,0,0,0}, // str +2 con +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race REBORN = new RPG_Race(
         "Reborn",
         new int[]{0,0,0,0,0,0,2,1}, // any +2 any +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race SATYR = new RPG_Race(
         "Satyr",
         new int[]{0,1,0,0,0,2}, // cha +2 dex + 1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race SHIFTER = new RPG_Race( // shifters = werefolk
         "Shifter",
         new int[]{0,2,0,0,0,0,1}, // dex +2 any +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race TABAXI = new RPG_Race( // tabaxi = catpeople
         "Tabaxi",
         new int[]{0,2,0,0,0,1}, // dex +2 cha +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race TORTLE = new RPG_Race( // tortle = turtle people
         "Tortle",
         new int[]{2,0,0,0,1,0}, // str +2 wis +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race TRITON = new RPG_Race( // triton = legged merfolk
         "Triton",
         new int[]{1,0,1,0,0,1}, // str +1 con +1 cha +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race WARFORGED = new RPG_Race( 
         "Warforged",
         new int[]{0,0,2,0,0,0,1}, // con +2 any other +1
         new RPG_Race.RaceFeature[]{
         }
      );
      public static RPG_Race YUAN_TI = new RPG_Race( // yuan-ti = snake people
         "Yuan-Ti",
         new int[]{0,0,0,1,0,2}, // cha +2 int +1
         new RPG_Race.RaceFeature[]{
         }
      );
   }
   **/
   public static HashMap<String,RPG_Race> BASIC_RACES = new HashMap(9);
   public RPG_Races_List(){
      BASIC_RACES.put("HUMAN",STANDARD_RACES.HUMAN);
      BASIC_RACES.put("ELF",STANDARD_RACES.ELF);
      BASIC_RACES.put("DWARF",STANDARD_RACES.DWARF);
      BASIC_RACES.put("DRAGONBORN",STANDARD_RACES.DRAGONBORN);
      BASIC_RACES.put("GNOME",STANDARD_RACES.GNOME);
      BASIC_RACES.put("GOBLIN",STANDARD_RACES.GOBLIN);
      BASIC_RACES.put("HALF-ORC",STANDARD_RACES.HALF_ORC);
      BASIC_RACES.put("HALFLING",STANDARD_RACES.HALFLING);
      BASIC_RACES.put("TIEFLING",STANDARD_RACES.TIEFLING);
      /**
      for(Map.Entry<String,RPG_Race> entry : BASIC_RACES.entrySet()){
         System.out.println("\n" + entry.getValue());
      }
      **/
   }
}
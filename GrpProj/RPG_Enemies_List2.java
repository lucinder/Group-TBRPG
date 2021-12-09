public class RPG_Enemies_List2{
   static RPG_Enemy DUMMY = new RPG_Enemy(
      "Dummy",
      30,
      new int[]{10,10,10,10,10,10},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING)
      },
      new RPG_Item[]{},
      200
   );
   static RPG_Enemy ANGRYDUMMY = new RPG_Enemy(
      "Mad Dummy",
      30,
      new int[]{16,11,14,8,10,12},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING),
         new RPG_Attack(RPG_Character.UNARMED_STRIKE)
      },
      new RPG_Item[]{},
      200
   );
   static RPG_Enemy BOSSDUMMYEASY = new RPG_Enemy(
      "Boss Dummy",
      40,
      new int[]{18,13,16,10,12,14},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING)
      },
      new RPG_Item[]{},
      1100,
      true
   );
   static RPG_Enemy BOSSDUMMY = new RPG_Enemy(
      "Boss Dummy",
      40,
      new int[]{18,13,16,10,12,14},
      14,
      new RPG_Action[]{
         new RPG_Attack(RPG_Character.UNARMED_STRIKE)
      },
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.SHORTSWORD),
         new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.SHORTBOW)
      },
      1100,
      true
   );
   
   static RPG_Enemy BOSSDUMMYHARD = new RPG_Enemy(
      "Boss Dummy",
      60,
      new int[]{18,13,16,10,12,14},
      16,
      new RPG_Action[]{},
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.LONGSWORD)
      },
      1100,
      true
   );

   //Skeleton
   //Dmg Immunities: poison
   //Dmg Resistences: 
   //Dmg Vulnerabilities: Damage?
   //Condition Immunities: exhaustion, poisoned
   //CR:1/4
   static RPG_Enemy Skeleton = new RPG_Enemy(
      "Skeleton",
      13,
      new int[]{10, 14, 15, 6, 8, 5},
      13,
      new RPG_Action[]{},
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.SHORTSWORD),
         new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.SHORTBOW)
      }, 
      50,
      false
   );

   //Zombie
   //Dmg immunities: poison 
   //Dmg Resistances:
   //Dmg vulnerabilities:
   //Condition Immunities: poisoned 
   //CR: 1/4
   //Notes: Undead Fortitude, if it would be reduced to 0 hp from a non-crit or non
   //radient dmg attack, makes a con save with DC 5 + Dmg taken. On success, drops to
   //1 hp instead.
   static RPG_Enemy Zombie = new RPG_Enemy(
      "Zombie",
      22,
      new int[]{13, 6, 16, 3, 6, 5},
      8,
      new RPG_Action[]{
         new RPG_Attack("Slam", "Damage", new int[]{1, 6}, 3)
      },
      new RPG_Item[]{
      }, 
      50,
      false
   );

   //Slime
   //Dmg immunities: 
   //Dmg Resistances: acid, cold, fire
   //Dmg vulnerabilities:
   //Condition Immunities: blinded, charmed, deafened, exhaustion, frightened, prone
   //CR: 1/2
   //Notes: Should have 1d6 + 1 Damage, and 2d6 acid, but can only have one dmg type
   //Any non-magic weapon that hits it gives it a cumulative -1 to dmg rolls
   //Once -5, the weapon is destroyed.
   //Pseudopod atk also reduces the AC of armor by 1. When the AC reaches 10, it is destroyed
   static RPG_Enemy Slime  = new RPG_Enemy(
      "Slime",
      22,
      new int[]{12, 6, 16, 1, 6, 2},
      8,
      new RPG_Action[]{
         new RPG_Attack("Pseudopod", "Acid", new int[]{3, 6}, 1, 3)
      },
      new RPG_Item[]{
      }, 
      100,
      false
   );

   //Ghoul
   //Dmg immunities: poison
   //Dmg Resistances: 
   //Dmg vulnerabilities:
   //Condition Immunities: charmed, exhaustion, poisoned 
   //CR: 1
   //Notes: Claws atk requires a non-elf or non-undead to make a DC 10 Con save
   //vs. being paralyzed for 1 minute. Can reroll save at the end of their turn
   static RPG_Enemy Ghoul = new RPG_Enemy(
      "Ghoul",
      22,
      new int[]{13, 15, 10, 7, 10, 6},
      12,
      new RPG_Action[]{
         new RPG_Attack("Bite", "Damage", new int[]{2, 6}, 2, 2),
         new RPG_Attack("Claws", "Damage", new int[]{2, 4}, 2, 4)
      },
      new RPG_Item[]{
      }, 
      200,
      false
   );

   //Specter
   //Dmg immunities: necrotic, poison
   //Dmg Resistances: acid, fire, cold, lightning, thunder, Dmg
   //Dmg vulnerabilities: 
   //Condition Immunities: charmed, exhaustion, grappled, paralyzed, petrified, poisoned
   //prone, restrined, unconscious 
   //CR: 1
   //Life Drain also requires a DC 10 Con save vs. reducing its hit point max by the dmg dealt
   //Has sunlight sensitivity
   static RPG_Enemy Specter = new RPG_Enemy(
      "Specter",
      22,
      new int[]{1, 14, 11, 10, 10, 11},
      12,
      new RPG_Action[]{
         new RPG_Attack("Life Drain", "Necrotic", new int[]{3, 6}, 0, 4)
      },
      new RPG_Item[]{
      }, 
      200,
      false
   );

   //Necromancer (Reflavored Kraul Death Priest)
   //Dmg immunities: 
   //Dmg Resistances: 
   //Dmg vulnerabilities: charmed, frightened
   //Condition Immunities: 
   //CR: 4
   //Pack tactics, Feed on Death, Hive Mind, Spider Climb
   //Chill touch prevents target from healing until start of caster's next turn
   //Ray of Sickness also forced a Con save vs being poisoned
   //Vampiric Touch heals caster for half of damage dealt
   static RPG_Enemy Necromancer = new RPG_Enemy(
      "Necromancer",
      65,
      new int[]{16, 12, 14, 12, 15, 10},
      14,
      new RPG_Action[]{
         new RPG_Attack("Chill Touch", "Necrotic", new int[]{1, 8}, 0, 4),
         new RPG_SaveAttack("Poison Spray", "Poison", new int[]{1, 12}, 12, 2),
         new RPG_Attack("Ray of Sickness", "Poison", new int[]{2, 8}, 0, 4),
         new RPG_SaveAttack("Blight", "Necrotic", new int[]{8, 8}, 12, 2),
         new RPG_Attack("Vampiric Touch", "Necrotic", new int[]{3, 6}, 0, 4)
      },
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.QUARTERSTAFF)
      }, 
      1100,
      true
   );

   public static RPG_Enemy[] test = new RPG_Enemy[]{DUMMY, ANGRYDUMMY};

   public static RPG_Enemy[] floorOne = new RPG_Enemy[]{Zombie, Skeleton, Slime, Specter, Ghoul};
}
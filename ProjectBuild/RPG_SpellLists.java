public class RPG_SpellLists {
    static RPG_Spell ChillTouch = new RPG_Spell(
        "Chill Touch",
        "Necrotic",
        new int[]{1, 8},
        0
    );

    static RPG_Spell EldritchBlast = new RPG_Spell(
        "Eldritch Blast",
        "Force",
        new int[]{1,10},
        0
    );

    static RPG_Spell FireBolt = new RPG_Spell(
        "Fire Bolt",
        "Fire",
        new int[]{1, 10},
        0
    );
    static RPG_Spell ShockingGrasp = new RPG_Spell(
      "Shocking Grasp",
      "Lightning",
      new int[]{1,8},
      0
    );

    static RPG_Spell PoisonSpray = new RPG_Spell(
        "Poison Spray",
        "Poison",
        new int[]{1, 12},
        0
    );

    static RPG_Spell RayOfFrost = new RPG_Spell(
        "Ray of Frost",
        "Cold",
        new int[]{1, 8},
        0
    );
    
    static RPG_Spell FlurryOfBlows = new RPG_Spell(
      "Flurry of Blows",
      "Damage",
      new int[]{3,4},
      1
    );

    static RPG_Spell CureWounds = new RPG_Spell(
        "Cure Wounds",
        "Healing",
        new int[]{1, 8},
        2
    );

    static RPG_Spell MagicMissile = new RPG_Spell(
        "Magic Missile",
        "Force",
        new int[]{3, 4},
        2
    );

    static RPG_Spell RayOfSickness = new RPG_Spell(
        "Ray of Sickness",
        "Poison",
        new int[]{2, 8},
        2
    );

    static RPG_Spell Fireball = new RPG_Spell(
        "Fireball",
        "Fire",
        new int[]{8, 6},
        6
    );
    
    static RPG_Spell LightningBolt = new RPG_Spell(
      "Lightning Bolt",
      "Lightning",
      new int[]{8,6},
      6
    );

    static RPG_Spell VampiricTouch = new RPG_Spell(
        "Vampiric Touch",
        "Necrotic",
        new int[]{3, 6},
        6
    );
    
    static RPG_Spell Thunderwave = new RPG_Spell( 
      "Thunderwave",
      "Thunder",
      new int[]{2,8},
      2
    );
    
    static RPG_Spell BreathOfWinter = new RPG_Spell( // CoC monk variant
      "Breath of Winter",
      "Cold",
      new int[]{8,8},
      6
    );
    
    static RPG_Spell ConeOfCold = new RPG_Spell(
      "Cone of Cold",
      "Cold",
      new int[]{8,8},
      10
    );
    
    static RPG_Spell GreaterCure = new RPG_Spell( // like a mass cure wounds, but self only
      "Greater Cure Wounds",
      "Healing",
      new int[]{3,8},
      10
    );
    
    static RPG_Spell Heal = new RPG_Spell(
      "Heal",
      "Healing",
      new int[]{70,1}, // should always heal 70HP,
      12
    );
    
    static RPG_Spell Disintegrate = new RPG_Spell(
      new RPG_Attack("Disintegrate","Force",new int[]{10,6},40),
      12
    );
    
    static RPG_Spell Shatter = new RPG_Spell(
      "Shatter",
      "Thunder",
      new int[]{3,8},
      4
    );
    
    static RPG_Spell GongOfTheSummit = new RPG_Spell( // monk shatter override
      "Gong of the Summit",
      "Thunder",
      new int[]{3,8},
      3
    );
    
    static RPG_Spell WallOfFire = new RPG_Spell(
      "Wall of Fire",
      "Fire",
      new int[]{10,8},
      8
    );
    
    static RPG_Spell RiverOfFlames = new RPG_Spell( // monk wall of fire override
      "River of Flames",
      "Fire",
      new int[]{10,8},
      5
    );
    static RPG_Spell WaterWhip = new RPG_Spell(
      "Water Whip",
      "Damage",
      new int[]{3,10},
      2
    );
    static RPG_Spell BurningHands = new RPG_Spell(
      "Burning Hands",
      "Fire",
      new int[]{3,6},
      2
    );
    static RPG_Spell CinderStrike = new RPG_Spell( // monk burning hands
      "Cinder Strike",
      "Fire",
      new int[]{3,6},
      2
    );
    static RPG_Spell Frostbite = new RPG_Spell(
      "Frostbite",
      "Cold",
      new int[]{1,6},
      0
    );
    static RPG_Spell ThornWhip = new RPG_Spell(
      "Thorn Whip",
      "Damage",
      new int[]{1,6},
      0
    );
    static RPG_Spell Moonbeam = new RPG_Spell(
      "Moonbeam",
      "Radiant",
      new int[]{2,10},
      4
    );
    static RPG_Spell TidalWave = new RPG_Spell(
      "Tidal Wave",
      "Damage",
      new int[]{4,8},
      6
    );
    static RPG_Spell EruptingEarth = new RPG_Spell(
      "Erupting Earth",
      "Damage",
      new int[]{3,12},
      6
    );
    static RPG_Spell Blight = new RPG_Spell(
      "Blight",
      "Necrotic",
      new int[]{8,8},
      8
    );
    static RPG_Spell InsectPlague = new RPG_Spell(
      "Insect Plague",
      "Damage",
      new int[]{4,10},
      10
    );
    static RPG_Spell Sunbeam = new RPG_Spell(
      "Sunbeam",
      "Radiant",
      new int[]{12,8},
      12
    );
    static RPG_Spell DivineSmite = new RPG_Spell(
      "Divine Smite",
      "Radiant",
      new int[]{2,8},
      2
    );
    static RPG_Spell BlindingSmite = new RPG_Spell(
      "Blinding Smite",
      "Radiant",
      new int[]{4,8},
      6
    );
    static RPG_Spell StaggeringSmite = new RPG_Spell(
      "Staggering Smite",
      "Psychic",
      new int[]{6,6},
      8
    );
    static RPG_Spell DestructiveWave = new RPG_Spell(
      "Destructive Wave",
      "Necrotic",
      new int[]{10,6},
      10
    );
    
    public static RPG_Spell[] Cantrips  = new RPG_Spell[]{EldritchBlast, ChillTouch, FireBolt, ShockingGrasp, PoisonSpray, RayOfFrost, Frostbite,ThornWhip};
    public static RPG_Spell[] FirstLevel  = new RPG_Spell[]{CureWounds, MagicMissile, RayOfSickness,Thunderwave, BurningHands,DivineSmite};
    public static RPG_Spell[] SecondLevel  = new RPG_Spell[]{Shatter,Moonbeam};
    public static RPG_Spell[] ThirdLevel  = new RPG_Spell[]{Fireball,VampiricTouch,LightningBolt,TidalWave,EruptingEarth,BlindingSmite};
    public static RPG_Spell[] FourthLevel  = new RPG_Spell[]{WallOfFire,Blight,StaggeringSmite};
    public static RPG_Spell[] FifthLevel  = new RPG_Spell[]{ConeOfCold,GreaterCure,InsectPlague,DestructiveWave};
    public static RPG_Spell[] SixthLevel  = new RPG_Spell[]{Heal,Disintegrate,Sunbeam};
    
    public static RPG_Spell[] WizardSpells = new RPG_Spell[]{ChillTouch,FireBolt, MagicMissile, CureWounds, Fireball};
    public static RPG_Spell[] MonkSpells  = new RPG_Spell[]{FlurryOfBlows, Thunderwave, WaterWhip, GongOfTheSummit, BreathOfWinter,RiverOfFlames};
    public static RPG_Spell[] WarlockSpells  = new RPG_Spell[]{ChillTouch,EldritchBlast,RayOfSickness,VampiricTouch,Blight};
    public static RPG_Spell[] SorcererSpells  = new RPG_Spell[]{RayOfFrost,FireBolt,PoisonSpray,BurningHands,Thunderwave,Shatter,LightningBolt,Fireball,WallOfFire};
    public static RPG_Spell[] ClericSpells = new RPG_Spell[]{CureWounds, GreaterCure, Heal,Sunbeam};
    public static RPG_Spell[] DruidSpells  = new RPG_Spell[]{PoisonSpray,Frostbite,ThornWhip,Thunderwave,Moonbeam,TidalWave,EruptingEarth,InsectPlague,Sunbeam};
    public static RPG_Spell[] PaladinSpells  = new RPG_Spell[]{DivineSmite, BlindingSmite,StaggeringSmite,DestructiveWave};
}
public class RPG_SpellsList {
    static RPG_Spell ChillTouch = new RPG_Spell(
        "Chill Touch",
        "Necrotic",
        new int[]{1, 8},
        0
    );

    static RPG_Spell EldritchBlast = new RPG_Spell(
        "Eldritch Blast",
        "Force",
        new int[]{1, 10},
        0
    );

    static RPG_Spell FireBolt = new RPG_Spell(
        "Fire Bolt",
        "Fire",
        new int[]{1, 10},
        0
    );

    static RPG_Spell PoisonSpray = new RPG_Spell(
        "Poison Spray",
        "Poison",
        new int[]{1, 12},
        0,
        true,
        2,
        10, //Placeholder DC
        false
    );

    static RPG_Spell RayOfFrost = new RPG_Spell(
        "Ray of Frost",
        "Cold",
        new int[]{1, 8},
        0
    );

    static RPG_Spell CureWounds = new RPG_Spell(
        "Cure Wounds",
        "Healing",
        new int[]{1, 8},
        1,
        3, //Placeholder spellcasting mod
        0
    );

    static RPG_Spell MagicMissile = new RPG_Spell(
        "Magic Missile",
        "Force",
        new int[]{3, 4},
        1,
        3, 
        999 //+999 toHit to make sure it never misses ;)
    );

    static RPG_Spell RayOfSickness = new RPG_Spell(
        "Ray of Sickness",
        "Poison",
        new int[]{2, 8},
        1
    );

    static RPG_Spell Fireball = new RPG_Spell(
        "Fireball",
        "Fire",
        new int[]{8, 6},
        3,
        true,
        1,
        13,
        true
    );

    static RPG_Spell VampiricTouch = new RPG_Spell(
        "Vampiric Touch",
        "Necrotic",
        new int[]{3, 6},
        3
    );
}

public class RPG_Spell extends RPG_Attack{
   // Spell Slot system has been replaced by Mana to reflect a different implementation
   private int manaCost = 2;
   private int castStat = 5; // cha default casting stat
   public RPG_Spell(){
      super("Eldritch Blast", "A beam of crackling energy streaks toward a creature within range. Make a ranged spell attack against the target. On a hit, the target takes 1d10 force damage.\nThe spell creates more than one beam when you reach higher levels: two beams at 5th level, three beams at 11th level, and four beams at 17th level. You can direct the beams at the same target or at different ones. Make a separate attack roll for each beam.");
   }
   
   public RPG_Spell(String name, String description){
      super(name, description);
   }
   public RPG_Spell(String name, String description, int manaCost){
      super(name, description);
      this.manaCost = manaCost;
   }
   public RPG_Spell(String name, String description, int manaCost, int castingStat){
      super(name, description);
      this.manaCost = manaCost;
      this.castStat = castingStat;
   }
   // getters + setters
   public int getManaCost(){ return manaCost; }
   public boolean canAfford(int currentMana){ return manaCost <= currentMana; }
}
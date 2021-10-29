public class RPG_Spell{
   // Spell Slot system has been replaced by Mana to reflect a different implementation
   private String name;
   private String description;
   private int manaCost = 2;
   public RPG_Spell(){
      name = "Eldritch Blast";
      description = "A beam of crackling energy streaks toward a creature within range. Make a ranged spell attack against the target. On a hit, the target takes 1d10 force damage.\nThe spell creates more than one beam when you reach higher levels: two beams at 5th level, three beams at 11th level, and four beams at 17th level. You can direct the beams at the same target or at different ones. Make a separate attack roll for each beam.";
   }
   
   public RPG_Spell(String name, String description){
      this.name = name;
      this.description = description;
   }
   public RPG_Spell(String name, String description, int manaCost){
      this.name = name;
      this.description = description;
      this.manaCost = manaCost;
   }
   // getters + setters
   public String getName(){ return name; }
   public String getDescription(){ return description; }
   public int getManaCost(){ return manaCost; }
   public boolean canAfford(int currentMana){ return manaCost <= currentMana; }
}
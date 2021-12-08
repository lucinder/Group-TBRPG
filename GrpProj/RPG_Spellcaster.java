/**
@author Lucinder
@file RPG_Spellcaster.java
A class to store Spellcasting features for caster classes.
**/
public class RPG_Spellcaster extends RPG_Class{
   private int castingAbility = 5; // default cha caster
   private int[] mana = {10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 150}; // MP per level - default linear progression
   public RPG_Spell[][] spellList;
   public RPG_Spellcaster(){
      super();
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int[] manaDistribution){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.mana = manaDistribution;
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int castingAbility){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.castingAbility = castingAbility;
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int[] manaDistribution, int castingAbility){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.mana = manaDistribution;
      this.castingAbility = castingAbility;
   }
   
   // getters + setters
   public int getCastingAbility(){
      return castingAbility;
   }
   public int getMana(){ // default mana for level 1
      return mana[0];
   }
   public int getMana(int characterLevel){ // return array of all spell slots at character's given level
      return mana[characterLevel - 1];
   }
}
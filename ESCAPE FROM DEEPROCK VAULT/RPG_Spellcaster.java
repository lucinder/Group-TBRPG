/**
@author Lucinder
@file RPG_Spellcaster.java
A class to store Spellcasting features for caster classes.
**/
public class RPG_Spellcaster extends RPG_Class{
   private int castingAbility = 5; // default cha caster
   private int[] mana = {5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45, 47, 50, 60}; // MP per level - default linear progression
   public RPG_Spell[] spellList = new RPG_Spell[]{};
   public RPG_Spellcaster(){
      super();
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      registerSpellList();
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int[] manaDistribution){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.mana = manaDistribution;
      registerSpellList();
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int castingAbility){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.castingAbility = castingAbility;
      registerSpellList();
   }
   public RPG_Spellcaster(String name, int hitDie, int[] abilityScoreImprovementLevels, int[] priorityStats, RPG_Item[] startingEquipment, int[] manaDistribution, int castingAbility){
      super(name, hitDie, abilityScoreImprovementLevels, priorityStats, startingEquipment);
      this.mana = manaDistribution;
      this.castingAbility = castingAbility;
      registerSpellList();
   }
   
   // getters + setters
   public void registerSpellList(){
      if(this.getName().equals("Cleric")){ spellList = RPG_SpellLists.ClericSpells; }
      if(this.getName().equals("Monk")){ spellList = RPG_SpellLists.MonkSpells; }
      if(this.getName().equals("Warlock")){ spellList = RPG_SpellLists.WarlockSpells; }
      if(this.getName().equals("Wizard")){ spellList = RPG_SpellLists.WizardSpells; }
      if(this.getName().equals("Sorcerer")){ spellList = RPG_SpellLists.SorcererSpells; }
      if(this.getName().equals("Paladin")){ spellList = RPG_SpellLists.PaladinSpells; }
      if(this.getName().equals("Druid") || this.getName().equals("Ranger")){ spellList = RPG_SpellLists.DruidSpells; } // recycle druid spells for rangers
   }
   public RPG_Spell[] getSpellList(){ return getSpells(); }
   public void printSpellList(){
      System.out.println("SPELL LIST:");
      for(RPG_Spell a : spellList){
         System.out.println(a.getSpell().getName() + " --- " + a.getCost());
      }
   }
   public RPG_Spell[] getSpells(){ return spellList; }
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
/**
@author Lucinder
@file RPG_Spell.java
This used to extend Attack, but it's better as a container class for 1 attack and 1 mana cost.
**/
public class RPG_Spell {
   // Spell Slot system has been replaced by Mana to reflect a different implementation
   private int manaCost = 0; // cantrip cost
   private RPG_Attack spell;
   public RPG_Spell(){
      this.spell = new RPG_Attack("Eldritch Blast","Force",new int[]{1,10});
   }
   
   public RPG_Spell(String name, String type, int[] dice){
      this.spell = new RPG_Attack(name,type,dice);
   }
   public RPG_Spell(String name, String type, int[] dice, int manaCost){
      this.spell = new RPG_Attack(name,type,dice);
      this.manaCost = manaCost;
   }
   public RPG_Spell(RPG_Attack newAttack, int manaCost){
      this.spell = newAttack;
      this.manaCost = manaCost;
   }
   public RPG_Spell(RPG_Spell other){
      this.spell = new RPG_Attack(other.getSpell());
      this.manaCost = other.getCost();
   }
   // getters + setters
   public String getName(){ return spell.getName(); }
   public void setName(String newName){ spell.setName(newName); }
   public int getCost(){ return manaCost; }
   public int getManaCost(){ return manaCost; }
   public RPG_Attack getSpell(){ return spell; }
   public boolean canAfford(int currentMana){ return manaCost <= currentMana; }
}
/**
@author Lucinder
@file RPG_SpellScroll.java
**/
public class RPG_SpellScroll extends RPG_Weapon{
   RPG_Spell storedSpell;
   // constructor overrides
   public RPG_SpellScroll(RPG_Spell spell){
      super("Scroll of " + spell.getSpell().getName(), 1, 300, spell.getSpell().getDice());
      this.storedSpell = new RPG_Spell(spell);
      storedSpell.setName("Scroll of " + storedSpell.getName()); // simplify casting logic for nomenclature
   }
   public RPG_SpellScroll(RPG_Spell spell, int value){
      super("Scroll of " + spell.getSpell().getName(), 1, value, spell.getSpell().getDice());
      this.storedSpell = new RPG_Spell(spell);
      storedSpell.setName("Scroll of " + storedSpell.getName()); // simplify casting logic for nomenclature
   }
   
   // getters setters
   public RPG_Spell getSpell(){ return storedSpell; }
   public void setSpell(RPG_Spell newSpell){ storedSpell = newSpell; }
}
/**
@author Lucinder
@file RPG_SpellScroll.java
**/
public class RPG_SpellScroll extends RPG_Weapon{
   RPG_Spell storedSpell;
   // constructor overrides
   public RPG_SpellScroll(RPG_Spell spell){
      super(spell.getName(), 1, 300, spell.getDice());
      this.storedSpell = spell;
   }
   
   // getters setters
   public RPG_Spell getSpell(){ return storedSpell; }
   public void setSpell(RPG_Spell newSpell){ storedSpell = newSpell; }
}
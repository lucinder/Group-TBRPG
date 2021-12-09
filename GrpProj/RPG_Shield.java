/**
@author Lucinder
@file RPG_Shield.java
Same as a normal item, just has a getter for AC bonus and holds a magic bonus.
**/

public class RPG_Shield extends RPG_Item{
   public int magicBonus = 0;
   public RPG_Shield(String name, int quantity, int value){ // default shield has 0 magic bonus
      super(name, quantity, value, "Raises AC by 2.");
   }
   public RPG_Shield(String name, int quantity, int value, int magicBonus){
      super(name, quantity, value, "Raises AC by " + 2 + magicBonus);
      this.magicBonus = magicBonus;
   }
   public int getMagicBonus(){ return magicBonus; }
   public int getACBonus(){ return 2 + magicBonus; }
}
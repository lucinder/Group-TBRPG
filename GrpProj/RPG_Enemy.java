/**
@author Lucinder
@file RPG_Enemy.java
ENEMY class
Handles deciding a proficiency bonus based on CR
To be added: action randomization (selection) and execution in-battle
**/
public class RPG_Enemy extends RPG_Character{
   private static double CR = 0.25; // enemy CR: default 1/4
   private boolean pacified = false;
   public RPG_Enemy(){super();}
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, double CR){
      super(name, hpMax, stats, AC, actions);
      this.CR = CR;
      setDialogue("A wild " + name + " appeared!");
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Item[] inventory, double CR){
      super(name, hpMax, stats, AC, inventory);
      this.CR = CR;
      setDialogue("A wild " + name + " appeared!");
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, RPG_Item[] inventory, double CR){
      super(name, hpMax, stats, AC, actions, inventory);
      this.CR = CR;
      setDialogue("A wild " + name + " appeared!");
   }
   public static int getProficiencyBonus(){
      return 2 + (int)(sgn(CR - 1) * (Math.abs(CR - 1)/4));
   }
   private static int sgn(double x){ // get the signature of x
      if(x < 0){ return -1;}
      else if (x > 0) { return 1; }
      return 0;
   }
   public void doAction(RPG_Character target){ // choose a random action to perform, then perform it
      RPG_Action action = super.getActions().get((int)(Math.random()*(super.getActions().size())));
      action.act(this, target);
   }
   public boolean isPacified(){ return pacified; } // is the enemy pacified (beat fight without killing)?
   public void pacify(){ pacified = true; } // pacify the enemy
}
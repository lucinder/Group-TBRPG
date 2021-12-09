/**
@author Lucinder
@file RPG_Enemy.java
ENEMY class
Handles deciding a proficiency bonus based on CR
The xp map is really finicky sometimes. Yell at it until it works, I say.
**/
import java.util.ArrayList;
public class RPG_Enemy extends RPG_Character{
   private RPG_XPMap xpmap = new RPG_XPMap();
   private int XP = 10; // enemy XP: default 10
   private boolean isBoss = false;
   private boolean pacified = false;
   public RPG_Enemy(){super(); defaultSettings();}
   public RPG_Enemy(RPG_Enemy toCopy){ // copy constructor
      super(toCopy.getName(), toCopy.getMaxHP(), toCopy.getStats(), toCopy.getAC(), toCopy.getActionsArray(), toCopy.getInventoryArray());
      // defaultSettings();
      this.XP = toCopy.getXP();
      setProficiencyBonus(this.getProficiencyBonus());
      this.isBoss = toCopy.getIfBoss();
      setDialogue("A wild " + toCopy.getName() + " appeared!");
      if(this.isBoss){
         setDialogue("The mighty " + toCopy.getName() + " blocks the path!");
      }
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, int XP){
      super(name, hpMax, stats, AC, actions);
      // defaultSettings();
      this.XP = XP;
      setProficiencyBonus(this.getProficiencyBonus());
      setDialogue("A wild " + name + " appeared!");
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Item[] inventory, int XP){
      super(name, hpMax, stats, AC, inventory);
      // defaultSettings();
      this.XP = XP;
      setProficiencyBonus(this.getProficiencyBonus());
      setDialogue("A wild " + name + " appeared!");
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, RPG_Item[] inventory, int XP){
      super(name, hpMax, stats, AC, actions, inventory);
      // defaultSettings();
      this.XP = XP;
      setProficiencyBonus(this.getProficiencyBonus());
      setDialogue("A wild " + name + " appeared!");
   }
   public RPG_Enemy(String name, int hpMax, int[] stats, int AC, RPG_Action[] actions, RPG_Item[] inventory, int XP, boolean isBoss){
      super(name, hpMax, stats, AC, actions, inventory);
      // defaultSettings();
      this.XP = XP;
      setProficiencyBonus(this.getProficiencyBonus());
      this.isBoss = isBoss;
      setDialogue("A wild " + name + " appeared!");
      // System.out.println("TEST- is boss? " + this.isBoss);
      if(this.isBoss){
         setDialogue("The mighty " + name + " blocks the path!");
      }
   }
   private void defaultSettings(){ // sets all fields to their defaults. mostly for debugging
      this.xpmap = new RPG_XPMap();
      this.XP = 10;
      setProficiencyBonus(this.getProficiencyBonus());
      this.isBoss = false;
      this.pacified = false;
      loadInventory(); // inventory loading must be done AFTER the xp map is added so that proficiency bonuses can be calculated!
   }
   public boolean getIfBoss(){ return isBoss; }
   public int getXP(){ return this.XP; }
   public double getCR(){ 
      this.xpmap = new RPG_XPMap();
      // System.out.println("DEBUG- XP = " + this.XP+", so CR = " + xpmap.map.get(this.XP));
      return (xpmap.map.get(this.XP));
   }
   public int getProficiencyBonus(){
      double CR = getCR();
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
   public double getHPPercent(){
      return 100.0*(getCurrentHP()/((double)getMaxHP()));
   }
   public boolean isPacified(){ return pacified; } // is the enemy pacified (beat fight without killing)?
   public void pacify(){ // pacify the enemy 
      pacified = true; 
      setDialogue("A friendly " + getName() + " greets you as you pass through!");
   } 
   public void die(){ setDialogue("The corpse of a " + getName() + " lays rotting."); }
}
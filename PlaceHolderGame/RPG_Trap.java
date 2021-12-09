/**
@author AboudS, modified by Lucinder for integration
@file RPG_Trap.java
**/
public class RPG_Trap extends RPG_Interactable{
   private RPG_Attack attack;
   private boolean isDisabled = false;
   public RPG_Trap() { // no arg constructor- defaults to pit trap
      super("Pit Trap", " triggered!");
      this.attack = new RPG_SaveAttack("Pit Trap", "Damage", new int[]{1,6}, 10, 1); // Pit trap- DC 10 dex save, 1d6 damage
   }

    public RPG_Trap(String name, RPG_Attack attack){ // full arg constructor
        super(name, " triggered!");
        this.attack = attack;
    }
    
    public RPG_Trap(RPG_Trap other){ // copy constructor
      super(other.getName(), " triggered!");
      this.attack = other.getAttack();
      this.isDisabled = other.getIfDisabled();
    }

    public boolean getIfDisabled() {
        return this.isDisabled;
    }

    public RPG_Attack getAttack() {
        return this.attack;
    }
    
    public void trigger(RPG_Character target){
      if(!isDisabled){ // if the trap is disabled, don't trigger it
         attack.act(this.getName(), target, true);
      }
    }
    
    public void disable(){
      System.out.println("You disabled a " + this.getName() + "!");
      isDisabled = true;
    }
    
    public void interactionEvent(){
      // System.out.println("A " + this.getName() + this.getDialogue());
      // NO NEED FOR THE ABOVE- it's already done when the trap is triggered
    }
}
/**
@author Lucinder
@file RPG_Action.java
Baseline action class. Holds and utilizes a name and description.
**/
public class RPG_Action{
   private String name; // Action name
   private String description; // Action description
   
   public RPG_Action(){ this.name = "Nothing"; this.description = " did nothing."; }
   public RPG_Action(RPG_Action other){ this.name = other.getName(); this.description = other.getDesc(); }
   public RPG_Action(String name){ this.name = name; this.description = " did nothing."; }
   public RPG_Action(String name, String desc){ this.name = name; this.description = desc; }
   
   public String getName(){ return this.name; }
   public String getDesc(){ return this.description; }
   
   public void setName(String newName){ this.name = newName; }
   public void setDesc(String newDesc){ this.description = newDesc; }
   
   public void act(RPG_Character user, RPG_Character target){ say(user.getName()); } // discard target for generic actions
   public void say(String user){ System.out.println(user + description); }
}
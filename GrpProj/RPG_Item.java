/**
@author Lucinder
@file RPG_Item.java
Simple Item class for a character generator.
Includes name, quantity, and value (CP) fields.
**/

public class RPG_Item extends RPG_Interactable{
   public static final String DEFAULT_USE = "But nothing happened!";
   private String name; // item name
   private String desc = "";
   private int qty = 1; // item quantity
   private int value = 0; // OPTIONAL- value in CP
   public RPG_Item(){
      super();
      name = "DummyItem";
      desc = "If you can read this, something went wrong!";
   }
   public RPG_Item(String name){
      super(name, DEFAULT_USE);
      this.name = name;
   }
   public RPG_Item(String name, int quantity){
      super(name, DEFAULT_USE);
      this.name = name;
      qty = quantity;
   }
   public RPG_Item(String name, int quantity, int value){
      super(name, DEFAULT_USE);
      this.name = name;
      qty = quantity;
      this.value = value;
   }
   public RPG_Item(String name, int quantity, int value, String description){
      super(name, DEFAULT_USE);
      this.name = name;
      qty = quantity;
      this.value = value;
      this.desc = description;
   }
   // getters/setters
   public String getName(){ return name; }
   public void setName(String newName){ name = newName; }
   public int getQuantity(){ return qty; }
   public void setQuantity(int newQty){ if(newQty <= 0){ return; } qty = newQty; }
   public void incrementQuantity(){ qty++; }
   public int getValue(){ return value; }
   public void setValue(int newValue){ if(newValue < 0){ return; } value = newValue; } 
   // tostring
   public String toString(){
      String output = name;
      output += "\n"+ value + " GP.\n" + desc;
      return output;
   }
}
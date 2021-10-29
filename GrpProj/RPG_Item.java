/**
@author Lucinder
@file RPG_Item.java
Simple Item class for a character generator.
Includes name, quantity, and value (CP) fields.
**/

public class RPG_Item{
   private String name; // item name
   private int qty = 1; // item quantity
   private int value = 0; // OPTIONAL- value in CP
   public RPG_Item(){
      name = "Placeholder";
   }
   public RPG_Item(String name){
      this.name = name;
   }
   public RPG_Item(String name, int quantity){
      this.name = name;
      qty = quantity;
   }
   public RPG_Item(String name, int quantity, int value){
      this.name = name;
      qty = quantity;
      this.value = value;
   }
   // getters/setters
   public String getName(){ return name; }
   public void setName(String newName){ name = newName; }
   public int getQuantity(){ return qty; }
   public void setQuantity(int newQty){ if(newQty <= 0){ return; } qty = newQty; }
   public void incrementQuantity(){ qty++; }
   public int getValue(){ return value; }
   public void setValue(int newValue){ if(newValue < 0){ return; } value = newValue; } 
   public double getValueSP(){ return value/10.0; }
   public double getValueGP(){ return value/100.0; }
   // tostring
   public String toString(){
      String output = name;
      output += "\n"+ value + " CP.";
      return output;
   }
}
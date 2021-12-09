/**
@author Lucinder
@file RPG_Dice.java
A package full of dice-rolling-related methods for D&D!
Public methods currently include:
+ int[] roll(x,y) --> returns results of an XdY roll as an array
+ int[] roll_khZ(x,y,z) --> returns results of an XdYkhZ roll as an array
+ int[] roll_klZ(x,y,z) --> returns results of an XdYklZ roll as an array
+ String arrToString(arr) --> simple array to string converter, meant for displaying roll arrays in {A, B, C,...} format
+ String rollToString(results), rollToString(x,y,results), rollToString(x,y,z,isKH,results) --> different String conversions for rolls specifically (provides roll results AND total)
+ int XdY(x,y) --> returns the total of an XdY roll's results 
+ int XdYkhZ(x,y,z) --> returns the total of an XdYkhZ roll's results
+ int XdYklZ(x,y,z) --> returns the total of an XdYklZ roll's results
+ int sum(int[] arr) --> returns the sum of an int array (helper method)
+ int[] randchar() --> returns an array of 6 4d6kh3 rolls (playable character base stat array)
**/
public class RPG_Dice{
   public static int[] roll(int x, int y){ // generates an XdY int array
      int[] results = new int[x];
      for(int i = 0; i < x; i++){
         results[i] = (int)(Math.random()*y) + 1;
      }
      return results;
   }
   public static String rollToString(int[] results){ // returns roll as a string with results in line 1, total in line 2
      return "Roll results: " + arrToString(results) + "\n Total: " + sum(results);
   }
   public static String rollToString(int x, int y, int[] results){ // simple modification of the roll to string to replace "Roll" with the roll value
      String output = rollToString(results);
      output = x + "d" + y + output.substring(4,output.length()); // replace "Roll" with actual roll XdY value
      return output;
   }
   public static String rollToString(int x, int y, int z, boolean isKH, int[] results){ // roll to string "Roll" replacer modified for kh- and kl- rolls
      String output = rollToString(results);
      if(isKH){ // Keep-Highest rolls
         output = x + "d" + y + "kh" + z + output.substring(4,output.length());
      } else { // Keep-lowest rolls
         output = x + "d" + y + "kl" + z + output.substring(4,output.length());
      }
      return output;
   }
   public static int XdY(int x, int y){ // returns total of an XdY roll
      if(x < 1 || y <= 0){ return 0; }
      int[] rollResults = roll(x,y);
      return sum(rollResults);
   }
   
   public static int[] roll_khZ(int x, int y, int z){ // XdY keep-highest Z
      if(z >= x || z < 1){ System.out.println("Invalid Keep-Highest value!"); return roll(x,y); } // handle invalid zs
      int[] rollResults = roll(x,y); // get base roll
      int dropCount = x - z;
      for(int i = 0; i < dropCount; i++){
         rollResults = dropLowest(rollResults); // drop lowest result until array is size Z
      }
      return rollResults;
   }
   public static int[] roll_klZ(int x, int y, int z){ // XdY keep-lowest Z
      if(z >= x || z < 1){ System.out.println("Invalid Keep-Lowest value!"); return roll(x,y); } // handle invalid zs
      int[] rollResults = roll(x,y); // get base roll
      int dropCount = x - z;
      for(int i = 0; i < dropCount; i++){
         rollResults = dropHighest(rollResults); // drop highest result until array is size Z
      }
      return rollResults;
   }
   
   public static int XdYkhZ(int x, int y, int z){ // returns total of an XdYkhZ roll
      int[] rollResults = roll_khZ(x,y,z);
      return sum(rollResults);
   }
   public static int XdYklZ(int x, int y, int z){ // returns total of an XdYklZ roll
      int[] rollResults = roll_klZ(x,y,z);
      return sum(rollResults);
   }
   
   public static int[] randchar(){ // generate 6-stat array for a new playable character!
      int[] result = new int[6];
      for(int i = 0; i < 6; i++){
         result[i] = XdYkhZ(4,6,3); // add 4d6kh3 to the stat array
      }
      return result;
   }
   
   public static int sum(int[] arr){ // helper method to get sum of int array
      int total = 0;
      for(int i: arr){
         total += i;
      }
      return total;
   }
   private static int[] dropLowest(int[] arr){ // helper method to return an array minus its lowest value
      int lowest = 0; // @precondition arr.length >= 1
      for(int i = 1; i < arr.length; i++){
         if(arr[i] < arr[lowest]){
            lowest = i;
         }
      }
      int[] newArr = new int[arr.length - 1];
      for(int i=0,j=0; i < arr.length; i++){
         if(i != lowest){
            newArr[j] = arr[i];
            j++;
         }
      }
      return newArr;
   }
   private static int[] dropHighest(int[] arr){ // same as dropLowest, but eliminates highest value from array instead
      int highest = 0; // @precondition arr.length >= 1
      for(int i = 1; i < arr.length; i++){
         if(arr[i] > arr[highest]){
            highest = i;
         }
      }
      int[] newArr = new int[arr.length - 1];
      for(int i=0,j=0; i < arr.length; i++){
         if(i != highest){
            newArr[j] = arr[i];
            j++;
         }
      }
      return newArr;
   }
   public static String arrToString(int[] arr){ // simple toString for an int array
      String result = "{";
      for(int i = 0; i < arr.length; i++){
         result += arr[i];
         if(i != arr.length - 1){
            result += ", ";
         }
      }
      return result + "}";
   }
   // modifier calculator
   public static int getModifier(int stat){
      if(stat < 10){ // why does this work? i have no clue
         // something to do with rounding up for <10 and rounding down for >=10?
         return(int)((stat - 11)/2);
      }
      return(int)((stat - 10)/2);
   }
   /**
   public static void main(String[] args){ // just here to run tests! comment me out when you're done!
      int[] roll1 = roll(1,20);
      int[] roll2 = roll(5,12);
      int[] roll3 = roll_khZ(4,6,3);
      System.out.println(rollToString(1,20,roll1));
      System.out.println(rollToString(roll2));
      System.out.println(rollToString(4,6,3,true,roll3));
      System.out.println(rollToString(4,6,3,false,roll_klZ(4,6,3)));
      int[] chara = randchar();
      System.out.println("Random character stat array: " +  arrToString(chara) + "\n Total: " + sum(chara));
   }
   **/
}
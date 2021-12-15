import java.util.Arrays;
public class RPG_Buff extends RPG_Attack{
   private int[] dice = {0,0}; // Buffs/nerfs use no dice by default
   private int[] statModifiers = {0,0,0,0,0,0}; // no stat modifiers by default
   private boolean isOverride = false; // do the stat mods REPLACE the initial stats or add to them?
   private String[] typesGained = {}; // type resistances gained (Nerf subclass treats these as weaknesses)
   public RPG_Buff(){ super("StandardBuff","Buff"); }
   public RPG_Buff(String name, String type){ super(name, type); }
   public RPG_Buff(String name, int[] statModifiers){
      super(name, "Buff");
      this.statModifiers = statModifiers;
   }
   public RPG_Buff(String name, int[] statModifiers, boolean isOverride){
      super(name, "Buff");
      this.statModifiers = statModifiers;
      this.isOverride = isOverride;
   }
   public RPG_Buff(String name, String type, int[] statModifiers){
      super(name, type);
      this.statModifiers = statModifiers;
   }
   public RPG_Buff(String name, String[] resistancesGained){
      super(name, "Buff");
      this.typesGained = resistancesGained;
   }
   public RPG_Buff(String name, String type, String[] resistancesGained){
      super(name, type);
      this.typesGained = resistancesGained;
   }
   public RPG_Buff(String name, int[] statModifiers, String[] resistancesGained){
      super(name, "Buff");
      this.statModifiers = statModifiers;
      this.typesGained = resistancesGained;
   }
   public RPG_Buff(String name, String type, int[] statModifiers, String[] resistancesGained){
      super(name, type);
      this.statModifiers = statModifiers;
      this.typesGained = resistancesGained;
   }
   public RPG_Buff(RPG_Potion buffPotion){
      super(buffPotion.getName(), "Buff");
      this.isOverride = buffPotion.getIfOverride();
      this.statModifiers = buffPotion.getStatMods();
   }
   public boolean getIfOverride(){ return isOverride; }
   public int[] getStatModifiers(){ return statModifiers; }
   public String[] getTypes(){ return typesGained; }
   public boolean equals(Object otherItem){
      if(otherItem instanceof RPG_Buff){
         RPG_Buff other = (RPG_Buff)otherItem;
         return getName().equals(other.getName()) && isOverride == other.getIfOverride() && super.getType().equals(other.getType()) && Arrays.equals(statModifiers, other.getStatModifiers()) && Arrays.equals(typesGained, other.getTypes());
      }
      return false;
   }
   public void act(RPG_Character user, RPG_Character target){
      this.act(target); // send down the line
   }
   public void act(RPG_Character target){
      if(!isOverride){
         if(getStatModifiers()[0] != 0){ // add stat modifiers
            target.addToStr(getStatModifiers()[0]);
         } else if (getStatModifiers()[1] != 0){
            target.addToDex(getStatModifiers()[1]);
         } else if(getStatModifiers()[2] != 0){
            target.addToCon(getStatModifiers()[2]);
         } else if(getStatModifiers()[3] != 0){
            target.addToInt(getStatModifiers()[3]);
         } else if(getStatModifiers()[4] != 0){
            target.addToWis(getStatModifiers()[4]);
         } else if(getStatModifiers()[5] != 0){
            target.addToCha(getStatModifiers()[5]);
         } else {
            target.addMaxHP(getStatModifiers()[6]);
         }
      } else {
         if(getStatModifiers()[0] != 0){ // add stat replacements
            target.setStr(getStatModifiers()[0]);
         } else if (getStatModifiers()[1] != 0){
            target.setDex(getStatModifiers()[1]);
         } else if(getStatModifiers()[2] != 0){
            target.setCon(getStatModifiers()[2]);
         } else if(getStatModifiers()[3] != 0){
            target.setInt(getStatModifiers()[3]);
         } else if(getStatModifiers()[4] != 0){
            target.setWis(getStatModifiers()[4]);
         } else if(getStatModifiers()[5] != 0){
            target.setCha(getStatModifiers()[5]);
         } // no override for max HP
      }
      if(getTypes().length > 0){ // add type weaknesses
         for(String s : getTypes()){
            target.addResistance(s);
         }
      }
      say(target.getName());
   }
   public void say(String target){
      System.out.print(target + " used the " + super.getName() + ". ");
      if(!isOverride){
         if(statModifiers[0] != 0){
            System.out.print(target + "\'s STR increased by " + statModifiers[0] + "! ");
         } else if (statModifiers[1] != 0){
            System.out.print(target + "\'s DEX increased by " + statModifiers[1] + "! ");
         } else if(statModifiers[2] != 0){
            System.out.print(target + "\'s CON increased by " + statModifiers[2] + "! ");
         } else if(statModifiers[3] != 0){
            System.out.print(target + "\'s INT increased by " + statModifiers[3] + "! ");
         } else if(statModifiers[4] != 0){
            System.out.print(target + "\'s WIS increased by " + statModifiers[4] + "! ");
         } else if(statModifiers[5] != 0){
            System.out.print(target + "\'s CHA increased by " + statModifiers[5] + "! ");
         } else {
            System.out.print(target + "\'s maximum HP increased by " + statModifiers[6] + "! ");
         }
      } else {
         if(statModifiers[0] != 0){
            System.out.print(target + "\'s STR became " + statModifiers[0] + "! ");
         } else if (statModifiers[1] != 0){
            System.out.print(target + "\'s DEX became " + statModifiers[1] + "! ");
         } else if(statModifiers[2] != 0){
            System.out.print(target + "\'s CON became " + statModifiers[2] + "! ");
         } else if(statModifiers[3] != 0){
            System.out.print(target + "\'s INT became " + statModifiers[3] + "! ");
         } else if(statModifiers[4] != 0){
            System.out.print(target + "\'s WIS became " + statModifiers[4] + "! ");
         } else if(statModifiers[5] != 0){
            System.out.print(target + "\'s CHA became " + statModifiers[5] + "! ");
         } 
      }
      if(typesGained.length > 0){
         System.out.print(target + " gained resistance to ");
         String toPrint = "";
         for(String s : typesGained){
            toPrint += s + ", ";
         }
         toPrint = toPrint.substring(0, toPrint.length()-2);
         System.out.print(toPrint + ".");
      }
      System.out.println();
      // return statModifiers;
   }
}
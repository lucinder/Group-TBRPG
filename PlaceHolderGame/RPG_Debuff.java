public class RPG_Debuff extends RPG_Buff{ // debuff- soft-overrides constructors (passes different action type + replaces a parameter name), say() function (replacing gains with losses)
   public RPG_Debuff(){ super("StandardDebuff","Debuff"); }
   public RPG_Debuff(String name, int[] statModifiers){
      super(name, "Debuff", statModifiers);
   }
   public RPG_Debuff(String name, String[] weaknessesGained){
      super(name, "Debuff", weaknessesGained);
   }
   public RPG_Debuff(String name, int[] statModifiers, String[] weaknessesGained){
      super(name, "Debuff", statModifiers, weaknessesGained);
   }
   public void act(RPG_Character target){
      if(super.getStatModifiers()[0] != 0){ // add stat modifiers
         target.addToStr(super.getStatModifiers()[0]);
      } else if (super.getStatModifiers()[1] != 0){
         target.addToDex(super.getStatModifiers()[1]);
      } else if(super.getStatModifiers()[2] != 0){
         target.addToCon(super.getStatModifiers()[2]);
      } else if(super.getStatModifiers()[3] != 0){
         target.addToInt(super.getStatModifiers()[3]);
      } else if(super.getStatModifiers()[4] != 0){
         target.addToWis(super.getStatModifiers()[4]);
      } else if(super.getStatModifiers()[5] != 0){
         target.addToCha(super.getStatModifiers()[5]);
      } else {
         target.addMaxHP(super.getStatModifiers()[6]);
      }
      if(super.getTypes().length > 0){ // add type weaknesses
         for(String s : super.getTypes()){
            target.addWeakness(s);
         }
      }
   }
   public int[] say(String user, String target){
      System.out.print(user + " used " + getName() + " on the " + target + "! ");
      if(super.getStatModifiers()[0] != 0){
         System.out.print(target + "\'s STR decreased by " + Math.abs(super.getStatModifiers()[0]) + "! ");
      } else if (super.getStatModifiers()[1] != 0){
         System.out.print(target + "\'s DEX decreased by " + Math.abs(super.getStatModifiers()[1]) + "! ");
      } else if(super.getStatModifiers()[2] != 0){
         System.out.print(target + "\'s CON decreased by " + Math.abs(super.getStatModifiers()[2]) + "! ");
      } else if(super.getStatModifiers()[3] != 0){
         System.out.print(target + "\'s INT decreased by " + Math.abs(super.getStatModifiers()[3]) + "! ");
      } else if(super.getStatModifiers()[4] != 0){
         System.out.print(target + "\'s WIS decreased by " + Math.abs(super.getStatModifiers()[4]) + "! ");
      } else if(super.getStatModifiers()[5] != 0){
         System.out.print(target + "\'s CHA decreased by " + Math.abs(super.getStatModifiers()[5]) + "! ");
      } else {
         System.out.print(target + "\'s maximum HP decreased by " + Math.abs(super.getStatModifiers()[6]) + "! ");
      }
      if(super.getTypes().length > 0){
         System.out.print(target + " became weak to ");
         String toPrint = "";
         for(String s : super.getTypes()){
            toPrint += s + ", ";
         }
         toPrint = toPrint.substring(0, toPrint.length()-2);
         System.out.println(toPrint + ".");
      }
      return super.getStatModifiers();
   }

}
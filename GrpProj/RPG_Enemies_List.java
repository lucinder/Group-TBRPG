public class RPG_Enemies_List{
   static RPG_Enemy DUMMY = new RPG_Enemy(
      "Dummy",
      30,
      new int[]{10,10,10,10,10,10},
      10,
      new RPG_Character.RPG_Action[]{
         RPG_Character.DONOTHING
      },
      new RPG_Item[]{},
      0
   );
   static RPG_Enemy ANGRYDUMMY = new RPG_Enemy(
      "Mad Dummy",
      30,
      new int[]{16,11,14,8,10,12},
      10,
      new RPG_Character.RPG_Action[]{
         RPG_Character.DONOTHING,
         RPG_Character.UNARMED_STRIKE
      },
      new RPG_Item[]{},
      0.125
   );

   public static RPG_Enemy[] test = new RPG_Enemy[]{DUMMY, ANGRYDUMMY};
}
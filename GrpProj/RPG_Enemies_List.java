public class RPG_Enemies_List{
   static RPG_Enemy DUMMY = new RPG_Enemy(
      "Dummy",
      30,
      new int[]{10,10,10,10,10,10},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING)
      },
      new RPG_Item[]{},
      200
   );
   static RPG_Enemy ANGRYDUMMY = new RPG_Enemy(
      "Mad Dummy",
      30,
      new int[]{16,11,14,8,10,12},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING),
         new RPG_Attack(RPG_Character.UNARMED_STRIKE)
      },
      new RPG_Item[]{},
      200
   );
   static RPG_Enemy BOSSDUMMYEASY = new RPG_Enemy(
      "Boss Dummy",
      40,
      new int[]{18,13,16,10,12,14},
      10,
      new RPG_Action[]{
         new RPG_Action(RPG_Character.DONOTHING)
      },
      new RPG_Item[]{},
      1100,
      true
   );
   static RPG_Enemy BOSSDUMMY = new RPG_Enemy(
      "Boss Dummy",
      40,
      new int[]{18,13,16,10,12,14},
      14,
      new RPG_Action[]{
         new RPG_Attack(RPG_Character.UNARMED_STRIKE)
      },
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.SHORTSWORD),
         new RPG_Weapon(RPG_Items_List.SIMPLE_WEAPONS.SHORTBOW)
      },
      1100,
      true
   );
   
   static RPG_Enemy BOSSDUMMYHARD = new RPG_Enemy(
      "Boss Dummy",
      60,
      new int[]{18,13,16,10,12,14},
      16,
      new RPG_Action[]{},
      new RPG_Item[]{
         new RPG_Weapon(RPG_Items_List.MARTIAL_WEAPONS.LONGSWORD)
      },
      1100,
      true
   );

   public static RPG_Enemy[] test = new RPG_Enemy[]{DUMMY, ANGRYDUMMY};
}
public class NewCombatTest{
   public static void main(String[] args) throws Exception{
      // RPG_Player player = new RPG_Player();
      RPG_Player player = RPG_Character_Builder.buildCharacter();
      RPG_Combat combat = new RPG_Combat(player);
      // combat.add(new RPG_Enemy(RPG_Enemies_List.Skeleton));
      combat.add(new RPG_Enemy(RPG_Enemies_List.Slime));
      combat.start();
   }
}
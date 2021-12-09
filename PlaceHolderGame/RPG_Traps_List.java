public class RPG_Traps_List{
   public static RPG_Trap PIT_TRAP = new RPG_Trap(
      "Pit Trap",
      new RPG_SaveAttack("Pit Trap", "Damage", new int[]{1,6}, 10, 1)
   );
   public static RPG_Trap ARROW_TRAP = new RPG_Trap(
      "Arrow Trap",
      new RPG_Attack("Arrow Trap","Damage", new int[]{1,10}, 0, 8)
   );
   public static RPG_Trap SPIKE_TRAP = new RPG_Trap(
      "Spike Trap",
      new RPG_Attack("Spike Trap","Damage", new int[]{1,10}, 0, 8)
   );
   public static RPG_Trap[] BASICTRAPS = new RPG_Trap[]{PIT_TRAP, ARROW_TRAP, SPIKE_TRAP};
}
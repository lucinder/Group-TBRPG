import java.util.HashMap;
public class RPG_XPMap{
   public HashMap<Integer, Double> map = new HashMap();
   public RPG_XPMap(){ fillXPMap(); }
   public void fillXPMap(){ // fill xp table with up to CR 20
      map.put(10, 0.0);
      map.put(25, 0.125);
      map.put(50, 0.25);
      map.put(100, 0.5);
      map.put(200, 1.0);
      map.put(450, 2.0);
      map.put(700, 3.0);
      map.put(1100, 4.0);
      map.put(1800, 5.0);
      map.put(2300, 6.0);
      map.put(2900, 7.0);
      map.put(3900, 8.0);
      map.put(5000, 9.0);
      map.put(5900, 10.0);
      map.put(7200, 11.0);
      map.put(8400, 12.0);
      map.put(10000, 13.0);
      map.put(11500, 14.0);
      map.put(13000, 15.0);
      map.put(15000, 16.0);
      map.put(18000, 17.0);
      map.put(20000, 18.0);
      map.put(22000, 19.0);
      map.put(25000, 20.0);
   }
}
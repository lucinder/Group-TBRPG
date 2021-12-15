/**
@author Lucinder
Demo file written for full gameplay with Debug mode available
**/
import java.io.*;
public class GAME{
   static boolean debug = false;
   public static void main(String[] args) throws IOException,InterruptedException,Exception{
      RPG_Game.start(debug);
   }
}
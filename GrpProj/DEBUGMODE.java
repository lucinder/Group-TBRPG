/**
@author Lucinder
Debug mode launcher, for gameplay testing.
**/
import java.io.*;
public class DEBUGMODE{
   public static void main(String[] args) throws IOException,InterruptedException, Exception{
      // RPG_Game.start(false);
      RPG_Game.start(true); // start game in debug mode
   }
}
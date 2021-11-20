/**
 * @author aboud
 * This is a game client to test the
 * game at its stages. Currently, it's
 * written in peseudocde
 */

public class GameClient {
    public static void main(string args[]) {
        RPG_Room startingRoom = new RPG_Room();
        startingRoom.setID("1_startingRoom");
        startingRoom.setDialogue("Welcome to the PlaceHolder game, prepare for some fun!");
        startingRoom.setType("Starting room, informs player of how to play");
        // startingRoom.addObject(); --Once intractable is developed, field will be used
        System.out.println("Welcome to the PlaceHolder game! " + startingRoom.getDialogue());
    }
}

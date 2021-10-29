///
public class BaseRoom {
    private String roomName = "";
    private String dialogue = "";
    private BaseRoom leftRoom = null;
    private BaseRoom rightRoom = null;
    private Trap trap = null;

    // Getters & Setters
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public BaseRoom getLeftRoom() {
        BaseRoom temp = leftRoom;
        return temp;
    }
    public void setLeftRoom(BaseRoom leftRoom) {
        this.leftRoom = leftRoom;
    }
    public BaseRoom getRightRoom() {
        BaseRoom temp = rightRoom;
        return temp;
    }
    public void setRightRoom(BaseRoom rightRoom) {
        this.rightRoom = rightRoom;
    }

    public Trap getTrap() {
        return trap;
    }

    public void setTrap(Trap trap) {
        this.trap = trap;
    }

    public String getDialogue() {
        return dialogue;
    }
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }
}

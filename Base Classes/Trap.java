public class Trap {
    private String dialogue;
    private int attack;

    public Trap() {

    }

    public Trap(String d, int a) {
        this.dialogue = d;
        this.attack = a;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
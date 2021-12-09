import java.util.*;

public class ShopKeep {
    String dialogue = "";
    String shopKeepName = "";
    ArrayList<String> listOfWeapons = new ArrayList<>(); //String will be replace with weapon object type
    int healthRegen = 0;

    public String getDialogue() {
        return dialogue;
    }
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }
    public String getShopKeepName() {
        return shopKeepName;
    }
    public void setShopKeepName(String shopKeepName) {
        this.shopKeepName = shopKeepName;
    }

    public int getHealthRegen() {
        return healthRegen;
    }
    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

    public void addWeapon(){
    
    }
}

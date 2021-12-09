public class RPG_Interactable{
   private String name;
   private String dialogue;
   public RPG_Interactable(){
      this.name = "DummyObject";
      this.dialogue = "Hello world!";
   }
   public RPG_Interactable(String name, String dialogue){
      this.name = name;
      this.dialogue = dialogue;
   }
   public void interactionEvent(){
      System.out.println(dialogue);
   }
   public void interactionEvent(String eventKey){
      if(eventKey.equals("use")){
         System.out.println("You used the " + name + ".");
         interactionEvent();
      } else {
         System.out.println("You weren\'t sure whether you could " + eventKey + " with  the " + name + ".");
      }
   }
   public String getName(){
      return this.name;
   }
   public String getDialogue(){
      return this.dialogue;
   }
   public void setDialogue(String newDialogue){
      this.dialogue = newDialogue;
   }
   public void addDialogueLine(String newLine){
      this.dialogue += "\n" + newLine;
   }
}
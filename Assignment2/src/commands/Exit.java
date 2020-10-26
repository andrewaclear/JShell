package Commands;

public class Exit extends Command {

  public Exit() {
    String desc = "Quit the program ";
    
    String iden = "exit";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

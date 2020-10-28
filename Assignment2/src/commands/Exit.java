package commands;

public class Exit extends Command {

  public static String badInput = "exit: doesn't take any arguments: ";

  public Exit() {
    String desc = "Quit the program ";
    
    String iden = "exit";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }

  public static boolean close() {
    return false;
  }
}

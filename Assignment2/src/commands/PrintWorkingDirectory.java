package Commands;

public class PrintWorkingDirectory extends Command {

  public PrintWorkingDirectory() {
    String desc = "Print the current working directory"
        + " (including the whole path).  ";
    
    String iden = "pwd";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

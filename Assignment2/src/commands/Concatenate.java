package Commands;

public class Concatenate extends Command {

  public Concatenate() {
    String desc = "Display the contents of FILE1 and other files"
        + " (i.e. File2 ....) concatenated in the shell.";
    
    String iden = "cat";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

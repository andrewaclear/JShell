package commands;

public class Concatenate extends Command {

  public Concatenate() {
    this.setIdentifier("cat");
    this.setDescription("Display the contents of FILE1 and other files"
        + " (i.e. File2 ....) concatenated in the shell.");
  }
}

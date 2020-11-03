package commands;

public class PushDirectory extends Command {

  public PushDirectory() {
    this.setIdentifier("pushd");
    this.setDescription("Saves the current working directory by pushing"
        + " onto directory stack \nand then changes the new current working"
        + " directory to DIR. The push \nmust be consistent as per the"
        + " LIFO behavior of a stack. The pushd \ncommand saves the old"
        + " current working directory in directory stack \nso that it can"
        + " be returned to at any time (via the popd command). \nThe size"
        + " of the directory stack is dynamic and dependent on the \npushd"
        + " and the popd commands.");
  }
}

package commands;

import data.*;
import runtime.ErrorHandler;

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
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("too many arguments");
    this.setMissingOperand("must provide a directory to navigate to");
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {

    FileSystemNode node = fSystem.getFileSystemNode(tokens[1]);
    if (node != null) {
      cache.pushDirectoryStack(fSystem.getCurrentDirectory().getPath());
      fSystem.setCurrentDirectory(node);
    } else {
      ErrorHandler.badInput(this, "invalid directory");
    }
    return true;
  }
}

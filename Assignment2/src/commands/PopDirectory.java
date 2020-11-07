package commands;

import data.*;
import runtime.ErrorHandler;

public class PopDirectory extends Command {
  
  public PopDirectory() {
    this.setIdentifier("popd");
    this.setDescription("Remove the top entry from the directory stack,"
      + " and cd into it. \nThe removal must be consistent as per the LIFO"
      + " behavior of  a \nstack. The popd command removes the top"
      + " most directory from \nthe directory stack and makes it the"
      + " current working directory. \nIf there is no directory onto"
      + " the stack, then give appropriate \nerror message. ");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("doesn't take any arguments");
    this.setMissingOperand("");
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    
    try {
      String path = cache.popDirectoryStack();
      fSystem.setCurrentDirectory(fSystem.getFileSystemNode(path));  
    } catch (Exception e) {
      ErrorHandler.badInput(this, "directory stack is empty");
    }

    return true;
  }
}

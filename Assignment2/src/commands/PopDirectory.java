// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
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

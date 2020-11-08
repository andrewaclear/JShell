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

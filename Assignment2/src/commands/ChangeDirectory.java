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

import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import data.Cache;

/**
 * Change directory to DIR, which may be relative to the current directory or
 * may be a full path. As with Unix, .. means a parent directory and a . means
 * the current directory. The directory must be /, the forward slash. The root
 * of the file system is a single slash: /.
 */
public class ChangeDirectory extends Command {

  /**
   * Constructor for ChangeDirectory class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public ChangeDirectory() {
    this.setIdentifier("cd");

    // ChangeDirectory must have two tokens
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);

    // Error Handling
    this.setErrorTooManyArguments("only one parameter is accepted");
    this.setMissingOperand("only accepts one parameter");

    // Description
    this.setDescription("Change directory to DIR, which may be relative to"
        + " the current directory\nor may be a full path. As with Unix, .."
        + " means a parent directory and a . \nmeans the current directory."
        + " The directory must be /, the forward slash. \nThe foot of the"
        + " file system is a single slash: /.");
  }

  /**
   * The run method of ChangeDirectory changes the currentDirectory of the
   * fileSystem to the given path tokens[1] if it is a valid/appropriate path in
   * fileSystem, otherwise, give back an error message. In any case, return true
   * after being done.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write to the
   *        file structure.
   * @param cache, stores the history and directory stack of the running
   *        terminal
   * @return returns a boolean true signal the shell to continue running
   */
  @Override
  public boolean run(String[] tokens, JShell shell) {

    FileSystemNode targetNode = null;

    // Set targetNode to the FileSystemNode that the path leads to
    targetNode = shell.getfSystem().getFileSystemNode(tokens[1]);

    // Check if the targetNode is in the root
    if (targetNode != null) {
      // Set the current Directory to the targetNode
      shell.getfSystem().setCurrentDirectory(targetNode);
    }

    return true;

  }

}

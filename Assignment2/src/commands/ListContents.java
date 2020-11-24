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

import java.util.List;
import data.*;
import driver.JShell;
import io.StandardOutput;
import runtime.ErrorHandler;

/**
 * Lists the contents of the current or given directory(s).
 */
public class ListContents extends Command {

  /**
   * Constructor for ListContents class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public ListContents() {
    this.setIdentifier("ls");
    // there is no max number of arguments (set when call)
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("");
    this.setDescription("If no paths are given, print the contents"
        + " (file or directory) of the \ncurrent directory, with a new line"
        + " following each of the content \n(file or directory). Otherwise,"
        + " for each path p, the order listed:  \n If p specifies a"
        + " file, print p \n If p specifies a directory, print p, a"
        + " colon, then the contents of that directory, then an extra"
        + " new line. \n If p does not exist, print a suitable message.");
  }

  /**
   * List the sub folders and files at the given path in the file system. Prints
   * an error if the path does not exist in the file system.
   * 
   * @param token, string of a token of a path entered by the user, used to
   *        print as a heading to it's contents when given
   * @param fSystem, instance of FileSystem
   * @param path, string of a path to a folder, a file, or nothing (where an
   *        error message is printed)
   * @return returns a boolean true if it exits in the fSystem and has contents
   *         and false otherwise
   */
  private String listSubFoldersFiles(String path, JShell shell) {

    String output = "";
    FileSystem fSystem = shell.getfSystem();

    // try to get folder at path
    FileSystemNode node = fSystem.getFileSystemNode(path);

    // if it exits
    if (node != null) {
      // print the given path with a colon when given
      if (!path.equals("."))
        output += path + ":\n";

      // get subFolders
      List<FileSystemNode> subFolders = node.getChildren();
      // print out sub-folders
      for (int i = 0; i < subFolders.size(); i++) {
        output += subFolders.get(i).getDirectory().getDirectoryName() + "\n";
      }

      // get files
      List<File> files = node.getDirectory().getFiles();
      // print out files in directory
      for (int i = 0; i < files.size(); i++) {
        output += files.get(i).getFileName() + "\n";
      }

    // check if the token is just a file
    } else {
      // try to get parent folder of file
      FileSystemNode subNode = fSystem.getSemiFileSystemNode(path);
      // the file name would be the last part after the last / in the path
      String fileName = fSystem.getPathLastEntry(path);

      // if the subfolder and file exits
      if (subNode != null && subNode.getDirectory().getFile(fileName) != null) {
        // print the name of the the file
        output += path + "\n";
      } else {
        // non-existant or empty
        ErrorHandler.badInput(this,
            "Cannot access \"" + path + "\": No such file or directory");
        return null;
      }
    }

    return output.substring(0, output.length()-1);
  }

  /**
   * If no paths are given, print the contents (file or directory) of the
   * current directory, with a new line following each of the content (file or
   * directory). Otherwise, for each path p, the order listed: • If p
   * specifies a file, print p • If p specifies a directory, print p, a colon,
   * then the contents of that directory, then an extra new line. • If p does
   * not exist, print a suitable message.
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
    boolean containsArrow = StandardOutput.containsArrow(tokens);
    int shift = containsArrow? - 2 : 0;

    //TODO
    // if (tokens[1].equals("-R")) tokens

    // output sting to be sent to StandardOutput
    String output = "";
    // print out current directory case, no given paths
    if (tokens.length == 1 || (containsArrow && tokens.length == 3)) {
      output = listSubFoldersFiles(".", shell);
    } else {
      String partOutput = "";
      // go through the given paths, stop if an invalid path is found
      for (int i = 1; i < tokens.length+shift && partOutput != null; i++) {
        // list contents, stop if there are none or path is bad
        partOutput = listSubFoldersFiles(tokens[i], shell);
        // print an extra line when listing given directories after first one
        if (partOutput != null) {
          if (i != 1) output += "\n\n";
          output += partOutput;
        }
      }
    }

    if (output != null && !output.equals("")) 
      StandardOutput.println(tokens, output, shell);

    return true;
  }

}

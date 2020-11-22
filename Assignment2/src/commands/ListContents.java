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
   * Tries to get the file system node at the given path. Return null if it does
   * not exits.
   * 
   * @param path, string of path to node
   * @param fSystem, an instance of FileSystem
   * @return returns the file system node if it exits, and null otherwise
   */
  private FileSystemNode tryGetFileSystemNode(String path, FileSystem fSystem) {
    try {
      FileSystemNode node = fSystem.getFileSystemNode(path);
      return node;
    } catch (Exception e) {
      return null;
    }
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
  private boolean listSubFoldersFiles(String token, FileSystem fSystem,
      String path) {

    // try to get folder at path
    FileSystemNode node = tryGetFileSystemNode(path, fSystem);
    boolean notEmpty = false;

    // if it exits
    if (node != null) {
      // print the given path with a colon when given
      if (!token.equals(""))
        StandardOutput.println(token + ":");

      // get subFolders
      List<FileSystemNode> subFolders = node.getChildren();
      // print out sub-folders
      for (int i = 0; i < subFolders.size(); i++) {
        StandardOutput
            .println(subFolders.get(i).getDirectory().getDirectoryName());
      }

      // get files
      List<File> files = node.getDirectory().getFiles();
      // print out files in directory
      for (int i = 0; i < files.size(); i++) {
        StandardOutput.println(files.get(i).getFileName());
      }

      // set notEmpty if there are folders and/or files
      if (subFolders.size() > 0 || files.size() > 0)
        notEmpty = true;

    } else {
      // check if the token is just a file

      // everything up to the last slash is a folder path
      String subPath = path.substring(0, path.lastIndexOf('/') + 1);
      // try to get this folder
      FileSystemNode subNode = tryGetFileSystemNode(subPath, fSystem);
      // the file name would be the last part after the last / in the path
      String fileName = path.substring(path.lastIndexOf('/') + 1);

      // if the subfolder and file exits
      if (subNode != null && subNode.getDirectory().getFile(fileName) != null) {
        // this files exists so notEmpty
        notEmpty = true;
        // print the name of the the file
        StandardOutput.println(token);
      } else {
        // non-existant or empty
        ErrorHandler.badInput(this,
            "cannot access '" + token + "': no such file or directory");
        return false;
      }
    }

    // if it's empty print the error
    if (!notEmpty)
      ErrorHandler.badInput(this,
          "no files or directories in the current directory");

    return true;
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
    String path;
    // success will be set to false if nothing is found
    boolean success = true;
    // print out current directory case, no given paths
    if (tokens.length == 1) {
      path = fSystem.getCurrentDirectory().getPath();
      success = listSubFoldersFiles("", fSystem, path);
    } else {
      // go through the given paths, stop if an invalid path is found
      for (int i = 1; i < tokens.length && success; i++) {
        // print an extra line when listing given directories after first one
        if (success && i != 1)
          StandardOutput.print("\n");
        // absolute path, current directory, or parent directory
        if (tokens[i].charAt(0) == '/' || tokens[i].equals(".")
            || tokens[i].equals(".."))
          path = tokens[i];
        // relative path
        else if (fSystem.getCurrentDirectory().getPath().equals("/")) 
          path = '/' + tokens[i];
        else path = fSystem.getCurrentDirectory().getPath() + '/' + tokens[i];
        // list contents, stop if there are none or path is bad
        success = listSubFoldersFiles(tokens[i], fSystem, path);

      }
    }
    return true;
  }

}

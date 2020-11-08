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

public class ListContents extends Command {

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

  private FileSystemNode tryGetFileSystemNode(String path, FileSystem fSystem) {
    try {
      FileSystemNode node = fSystem.getFileSystemNode(path);
      return node;
    } catch (Exception e) {
      return null;
    }
  }
  
  private boolean listSubFolders(String token, FileSystem fSystem, String path) {
    
    FileSystemNode node = tryGetFileSystemNode(path, fSystem);

    if (node != null) {
      // print the given p with a colon when given
      if (!token.equals("")) StandardOutput.println(token+":");

      // get subFolders
      List<FileSystemNode> subFolders = node.getChildren();
      // print out sub-folders
      for (int i=0; i<subFolders.size(); i++)   {
        StandardOutput.println(subFolders.get(i).getDirectory().
        getDirectoryName());
      }

      // get files
      List<File> files = node.getDirectory().getFiles();
      // print out files in directory
      for (int i=0; i<files.size(); i++) {
        StandardOutput.println(files.get(i).getFileName());
      }

    } else {
      // check if the token is just a file
      String subPath = path.substring(0, path.lastIndexOf('/')+1);
      FileSystemNode subNode = tryGetFileSystemNode(subPath, fSystem);
      String fileName = path.substring(path.lastIndexOf('/')+1);

      // System.out.println("Path:"+path+" subpath:"+subPath+" filename"+fileName);

      if (subNode != null && subNode.getDirectory().getFile(fileName) != null) {
        // List<File> files = subNode.getDirectory().getFiles();
        // String file = path.substring(path.lastIndexOf('/'));
        // if (files.indexOf(file) >= 0) StandardOutput.println(token);
        StandardOutput.println(token);
      } else {
        ErrorHandler.badInput(this, "cannot access '"+token
                                    +"': No such file or directory");
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    String path;
    // print out current directory case
    if (tokens.length == 1) {
      path = fSystem.getCurrentDirectory().getPath();
      listSubFolders("", fSystem, path);
    } else {
      for (int i = 1; i < tokens.length; i++) {
        // absolute path
        if (tokens[i].charAt(0) == '/') path = tokens[i];
        // relative path
        else path = fSystem.getCurrentDirectory().getPath()+tokens[i];

        if (listSubFolders(tokens[i], fSystem, path)) {
          // print an extra line when listing given directories
          StandardOutput.print("\n");
        }
      }    
    }

    return true;
  }

}

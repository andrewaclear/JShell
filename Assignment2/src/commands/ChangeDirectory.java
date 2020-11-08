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
import data.FileSystemNode;

public class ChangeDirectory extends Command {
  
  public ChangeDirectory() {
    this.setIdentifier("cd");
    
    //ChangeDirectory must have two tokens
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
    
    //Error Handling
    this.setErrorTooManyArguments("only one parameter is accepted");
    this.setMissingOperand("only accepts one parameter");
    
    //Description
    this.setDescription("Change directory to DIR, which may be relative to"
      + " the current directory\nor may be a full path. As with Unix, .."
      + " means a parent directory and a . \nmeans the current directory."
      + " The directory must be /, the forward slash. \nThe foot of the"
      + " file system is a single slash: /.");
  }
  
  @Override
  public boolean run(String[] tokens, FileSystem system, Cache cache) {
    
    FileSystemNode targetNode = null;
    
    if (tokens[1].equals("..")) {
      
      targetNode = system.getCurrentDirectory().getParent();
      
    } else if (tokens[1].equals(".")) {
      
      targetNode = system.getCurrentDirectory();
      
    } else {
    
      //Set targetNode to the FileSystemNode that the path leads to
      targetNode = system.getFileSystemNode(tokens[1]);
      
    }
    //Check if the targetNode is in the root
    if (targetNode != null)
    {
      System.out.println("CD: now at " + targetNode.getPath());
      //Set the current Directory to the targetNode
      system.setCurrentDirectory(targetNode);
      
    } else {
      
      //TODO: Add error of invalid path
      System.out.println("CD: Bad path");
      
    }
    
    return true;
    
  }
  
}

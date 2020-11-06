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

package commands;

import data.FileSystem;
import data.FileSystemNode;

public class ChangeDirectory extends Command {
  
  public ChangeDirectory() {
    this.setIdentifier("cd");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
    this.setDescription("Change directory to DIR, which may be relative to"
      + " the current directory\nor may be a full path. As with Unix, .."
      + " means a parent directory and a . \nmeans the current directory."
      + " The directory must be /, the forward slash. \nThe foot of the"
      + " file system is a single slash: /. ");
  }
  
  public boolean run(String[] tokens, FileSystem root) {
    
    //Set targetNode to the FileSystemNode that the path leads to
    FileSystemNode targetNode = root.getFileSystemNode(tokens[0]);
    
    //Check if the targetNode is in the root
    if (targetNode != null)
    {
      
      //Set the current Directory to the targetNode
      root.setCurrentDirectory(targetNode);
      
    } else {
      
      //TODO: Add error of invalid path
      
    }
    
    return true;
    
  }
  
}

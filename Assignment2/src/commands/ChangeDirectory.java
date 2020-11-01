package commands;

import data.FileSystem;
import data.FileSystemNode;

public class ChangeDirectory extends Command {
  
  public ChangeDirectory() {
    this.setIdentifier("cd");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
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

package commands;

import data.FileSystem;
import data.FileSystemNode;

public class ChangeDirectory extends Command {
  
  public ChangeDirectory() {
    this.setIdentifier("cd");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
  }
  
  public void doCommand(String path, FileSystem system) {
    
    //set note to the FileSystemNode that the path leads to if any
    FileSystemNode node = system.getFileSystemNode(path);
    
    //set the FileSystem current_directory to node
    if (node != null)
    {
      system.setCurrentDirectory(node);
    }
    
  }
  
}

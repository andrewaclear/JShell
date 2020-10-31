package commands;

import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectory extends Command {
  
  //Given two parameters, makes two directories
  
  public MakeDirectory() {
    this.setIdentifier("mkdir");
    this.setIdentifier("mkdir");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
  }
  
  public void DoCommand(String path1, String path2, FileSystem structure) {
    FileSystemNode target_node1=null, target_node2=null; 
    
    target_node1 = structure.getFileSystemNode(path1);
    target_node2 = structure.getFileSystemNode(path2);
    
    
    
  }
  
}

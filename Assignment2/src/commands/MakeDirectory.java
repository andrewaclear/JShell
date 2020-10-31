package commands;

import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectory extends Command {
  
  //Given two parameters, makes two directories
  
  public MakeDirectory() {
    String desc = "This command takes in two arguments only."
        + " Create directories, each of which may be relative"
        + " to the current directory or may be a full path."
        + " If creating DIR1 results in any kind of error,"
        + " do not proceed with creating DIR 2. However, if DIR1"
        + " was created successfully, and DIR2 creation results in an error,"
        + " then give back an error specific to DIR2. ";
    
    String iden = "mkdir";
    this.setDescription(desc);
    this.setIdentifier(iden);
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

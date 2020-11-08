package commands;

import data.Cache;
import data.Directory;
import data.FileSystem;
import data.FileSystemNode;
import io.StandardOutput;

public class MakeDirectory extends Command {
  
  //Constructor
  public MakeDirectory() {
    this.setIdentifier("mkdir");
    
    //MakeDirectory must have three tokens
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(3);
    
    //Error Handling
    this.setErrorTooManyArguments("only two parameters are accepted");
    this.setMissingOperand(" only accepts two parameters");
    
    //Description
    this.setDescription("This command takes in two arguments only."
        + " Create directories, \neach of which may be relative"
        + " to the current directory or may \nbe a full path."
        + " If creating DIR1 results in any kind of error,"
        + " \ndo not proceed with creating DIR 2. However, if DIR1"
        + " was created \nsuccessfully, and DIR2 creation results in an error,"
        + " then give \nback an error specific to DIR2. ");
  }
  
  //MakeDirectory given two parameters, makes two directories
  @Override
  public boolean run(String[] tokens, FileSystem fileSystem, Cache cache) {
    
    FileSystemNode targetNode1 = null, targetNode2 = null; 

    targetNode1 = fileSystem.getSemiFileSystemNode(tokens[1]);
    
    targetNode2 = fileSystem.getSemiFileSystemNode(tokens[2]);
    
    //Check if path1 is valid
    if (targetNode1 != null & !targetNode1.isChildInside(fileSystem.getPathLastEntry(tokens[1]))) {
      
      //Check if the Directory already exists in the children of FileSystemNode given by path1
        
      //Add Directory to the FileSystemNode given by path1
      targetNode1.addChild(new FileSystemNode(new Directory(fileSystem.getPathLastEntry(tokens[1]))));
      
      System.out.println("Sucessfully added Directory " + fileSystem.getPathLastEntry(tokens[1]) + " at " + targetNode1.getPath());
        
        //Check if path2 is valid
        if (targetNode2 != null & !targetNode2.isChildInside(fileSystem.getPathLastEntry(tokens[2]))) {
          
           //Check if the Directory already exists in the children of FIleSystemNode given by path1
                 
           //Add Directory to the FileSystemNode given by path2
           targetNode2.addChild(new FileSystemNode(new Directory(fileSystem.getPathLastEntry(tokens[2]))));
           
           System.out.println("Sucessfully added Directory " + fileSystem.getPathLastEntry(tokens[2]) + " at " + targetNode2.getPath());
         
        }
    }
   
    return true;
    
  }
  
}

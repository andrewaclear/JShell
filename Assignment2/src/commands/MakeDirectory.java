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
    
    //actualPath1 is the array of the Directories path1 traverses
    //actualPath2 is the array of the Directories path2 traverses
    String[] splitPath1;
    String[] splitPath2;
    
    FileSystemNode targetNode1 = null, targetNode2 = null; 
    
    //The targetPath1 is path1 excluding the last Directory
    //actualPath1 will be an array of subpaths in tokens[0]
    if (tokens[1].charAt(0) == '/') {
      
      splitPath1 = tokens[1].substring(1).split("/");

      
    } else {
    
      splitPath1 = tokens[1].split("/");
    
    }

    
    targetNode1 = fileSystem.getSemiFileSystemNode(tokens[1]);
    
    //The targetPath2 is path2 excluding the last Directory
    if (tokens[2].charAt(0) == '/') {
      
      splitPath2 = tokens[2].substring(1).split("/");

      
    } else {
    
      splitPath2 = tokens[2].split("/");
    
    }
    
    targetNode2 = fileSystem.getSemiFileSystemNode(tokens[2]);
    
    //Check if path1 is valid
    if (targetNode1 != null) {
      
      //Check if the Directory already exists in the children of FileSystemNode given by path1
      if (targetNode1.isChildInside(splitPath1[splitPath1.length - 1])) {
        
        // TODO:Add error of invalid, the Directory is already inside
        /*StandardOutput.println("The Directory " + splitPath1[splitPath1.length - 1] 
            + " already exists at " + targetNode1.getPath());
        */
        
      } 
      else {
        
        //Add Directory to the FileSystemNode given by path1
        targetNode1.addChild(new FileSystemNode(new Directory(splitPath1[splitPath1.length - 1])));
        
        System.out.println("Sucessfully added Directory " + splitPath1[splitPath1.length - 1] + " at " + targetNode1.getPath());
        
        //Check if path2 is valid
        if (targetNode2 != null) {
          
          //Check if the Directory already exists in the children of FIleSystemNode given by path1
          if (targetNode2.isChildInside(splitPath2[splitPath2.length - 1])) {
            
            // TODO:Add error of invalid, the Directory is already inside
            /*
            StandardOutput.println("The Directory " + splitPath2[splitPath2.length - 1] 
                + " already exists at " + targetNode2.getPath());
            */
            
             } 
             else {
               
               //Add Directory to the FileSystemNode given by path2
               targetNode2.addChild(new FileSystemNode(new Directory(splitPath2[splitPath2.length - 1])));
               
               System.out.println("Sucessfully added Directory " + splitPath2[splitPath2.length - 1] + " at " + targetNode2.getPath());
               
             }
        } 
        else {
          
         // TODO:Add error of invalid path2
            
        }
          
      
        }
      
      }
    
    // TODO:Add error of invalid path1
   
    return true;
    
  }
  
}

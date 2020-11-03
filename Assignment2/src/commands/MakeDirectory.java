package commands;

import data.Directory;
import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectory extends Command {
  
  //Constructor
  public MakeDirectory() {
    this.setIdentifier("mkdir");
    
    //MakeDirectory must have two arguments
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
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
  public boolean run(String[] tokens, FileSystem root) {
    
    //actualPath1 is the array of the Directories path1 traverses
    //actualPath2 is the array of the Directories path2 traverses
    String actualPath1[] = tokens[0].split("/");
    String actualPath2[] = tokens[1].split("/");
    
    String targetPath1 = "", targetPath2 = "";
    
    //The targetPath1 is path1 excluding the last Directory
    for (int i = 0; i < actualPath1.length - 2; i ++) {
      targetPath1 = targetPath1 + actualPath1[i] + "/";
    }
    
    targetPath1 += actualPath1[actualPath1.length - 2];
    
    //Add "/" to the actualPath1 if path1 refers to a full path
    if (tokens[0].charAt(0) == '/') {
      targetPath1 = "/" + actualPath1;
    }
    
    //The targetPath2 is path2 excluding the last Directory
    for (int i = 0; i < actualPath2.length - 2; i ++) {
      targetPath2 = targetPath2 + actualPath2[i] + "/";
    }
    
    targetPath2 += actualPath2[actualPath2.length - 2];
    
    
  //Add "/" to the actualPath1 if path2 refers to a full path
    if (tokens[1].charAt(0) == '/') {
      targetPath2 = "/" + actualPath2;
    }
    
    //Get the FileSystemNodes that we need to add a Directory in
    FileSystemNode targetNode1 = null, targetNode2 = null; 
    
    targetNode1 = root.getFileSystemNode(targetPath1);
    targetNode2 = root.getFileSystemNode(targetPath2);
    
    //Check if path1 is valid
    if (targetNode1 != null) {
      
      //Check if the Directory already exists in the children of FileSystemNode given by path1
      if (targetNode1.isChildInside(actualPath1[actualPath1.length - 1])) {
        
     // TODO:Add error of invalid already Directory inside
        
      } 
      else {
        
        //Add Directory to the FileSystemNode given by path1
        targetNode1.addChild(new FileSystemNode(new Directory(actualPath1[actualPath1.length - 1])));
        
        //Check if path2 is valid
        if (targetNode2 != null) {
          
          //Check if the Directory already exists in the children of FIleSystemNode given by path1
          if (targetNode2.isChildInside(actualPath2[actualPath2.length - 1])) {
            
            // TODO:Add error of invalid already Directory inside
            
             } 
             else {
               
               //Add Directory to the FileSystemNode given by path2
               targetNode2.addChild(new FileSystemNode(new Directory(actualPath2[actualPath2.length - 1])));
               
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

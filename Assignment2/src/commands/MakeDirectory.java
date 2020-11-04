package commands;

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
  public boolean run(String[] tokens, FileSystem system) {
    
    //actualPath1 is the array of the Directories path1 traverses
    //actualPath2 is the array of the Directories path2 traverses
    String[] actualPath1;
    String[] actualPath2;
    
    String targetPath1 = "", targetPath2 = "";
    
    FileSystemNode targetNode1 = null, targetNode2 = null; 
    
    //The targetPath1 is path1 excluding the last Directory
    //actualPath1 will be an array of subpaths in tokens[0]
    if (tokens[1].charAt(0) == '/') {
      
      actualPath1 = tokens[1].substring(1).split("/");
      targetPath1 = "/";
      
    } else {
    
      actualPath1 = tokens[1].split("/");
    
    }
    
    //Check if token[0] referred to the current Directory
    if (actualPath1.length != 1) {
    
      for (int i = 0; i < actualPath1.length - 2; i ++) {
        targetPath1 = targetPath1 + actualPath1[i] + "/";
      }
      
      targetPath1 += actualPath1[actualPath1.length - 2];
      
      targetNode1 = system.getFileSystemNode(targetPath1);
      
    } else {
      
      if (tokens[1].charAt(0) == '/') {
        targetPath1 += actualPath1[0];
        targetNode1 = system.getFileSystemNode(targetPath1);;
      } else {
        targetNode1 = system.getCurrentDirectory();
      }
    }
    
    
    //The targetPath2 is path2 excluding the last Directory
    if (tokens[2].charAt(0) == '/') {
      
      actualPath2 = tokens[2].substring(1).split("/");
      targetPath2 = "/";
      
    } else {
    
      actualPath2 = tokens[2].split("/");
    
    }
    
    if (actualPath2.length != 1) {
    
      for (int i = 0; i < actualPath2.length - 2; i ++) {
      targetPath2 = targetPath2 + actualPath2[i] + "/";
      }
      
      targetPath2 += actualPath2[actualPath2.length - 2];
      
      targetNode2 = system.getFileSystemNode(targetPath2);
    
    }  else {
      
      if (tokens[2].charAt(0) == '/') {
        targetPath2 += actualPath2[0];
        targetNode2 = system.getFileSystemNode(targetPath2);;
      } else {
        targetNode2 = system.getCurrentDirectory();
      }
      
    }

    
    //Check if path1 is valid
    if (targetNode1 != null) {
      
      //Check if the Directory already exists in the children of FileSystemNode given by path1
      if (targetNode1.isChildInside(actualPath1[actualPath1.length - 1])) {
        
        // TODO:Add error of invalid, the Directory is already inside
        StandardOutput.println(actualPath1[actualPath1.length - 1] + " already exists at " 
        + targetPath1 + "/");
        
        
      } 
      else {
        
        //Add Directory to the FileSystemNode given by path1
        targetNode1.addChild(new FileSystemNode(new Directory(actualPath1[actualPath1.length - 1])));
        
        System.out.println("Sucessfully added " + actualPath1[actualPath1.length - 1]);
        
        //Check if path2 is valid
        if (targetNode2 != null) {
          
          //Check if the Directory already exists in the children of FIleSystemNode given by path1
          if (targetNode2.isChildInside(actualPath2[actualPath2.length - 1])) {
            
            // TODO:Add error of invalid, the Directory is already inside
            StandardOutput.println(actualPath2[actualPath2.length - 1] + " already exists at " 
            + targetPath2 + "/");
            
             } 
             else {
               
               //Add Directory to the FileSystemNode given by path2
               targetNode2.addChild(new FileSystemNode(new Directory(actualPath2[actualPath2.length - 1])));
               
               System.out.println("Sucessfully added " + actualPath2[actualPath2.length - 1]);
               
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

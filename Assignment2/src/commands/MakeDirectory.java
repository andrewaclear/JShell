package commands;

import data.Directory;
import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectory extends Command {
  
  //Given two parameters, makes two directories
  
  public MakeDirectory() {
    this.setIdentifier("mkdir");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
  }
  
  public void run(String path1, String path2, FileSystem root) {
    
    FileSystemNode targetNode1 = null, targetNode2 = null; 
    
    String actualPath1[] = path1.split("/");
    String actualPath2[] = path2.split("/");
    
    String targetPath1 = "", targetPath2 = "";
    
  //The targetPath1 is path1 excluding the last Directory
    for (int i = 0; i < actualPath1.length - 1; i ++) {
      targetPath1 += actualPath1[i];
    }
    if (path1.charAt(0) == '/') {
      targetPath1 = "/" + actualPath1;
    }
    
    //The targetPath2 is path2 excluding the last Directory
    for (int i = 0; i < actualPath2.length - 1; i ++) {
      targetPath2 += actualPath2[i];
    }
    if (path2.charAt(0) == '/') {
      targetPath2 = "/" + actualPath2;
    }
    
    //Get the FileSystemNodes that we need to add a Directory in
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
    
  }
  
}

package data;

import java.util.*; 

public class Directory {

  //Contains files in a linked list
  
  LinkedList<File> files  = new LinkedList<File>();
  
  
  //method to add files to the directory
  
  public void addFile(File file) {
    
    //adding the file to the linked list of files
    files.add(file);
    
    return;
  }
  
  
}

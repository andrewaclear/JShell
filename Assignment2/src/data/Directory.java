package data;

import java.util.*; 

public class Directory {

  //Has a name and contains files in a linked list
  
  String directoryName = "";
  
  LinkedList<File> files  = new LinkedList<File>();
  
  
  public Directory(String name) {
    this.directoryName = name;
  }
  
  //method to change the name of Directory
  public void setDirectoryName(String name) {
    this.directoryName = name;
  }
  
  //method to get the name of the Directory
  public String getDirectoryName() {
    return this.directoryName;
  }
  
  //method to add files to the directory

  public void addFile(File file) {
    
    //adding the file to the linked list of files
    files.add(file);
    
    return;
  }
  
  //Return the directory's name when printing
  public String toString() {
    return this.directoryName;
  }
  
}

package data;

import java.util.*; 

public class Directory {

  //Has a name and contains files in a linked list
  
  // TODO set name in contructor and make way to access LinkedList of files
  String directoryName = "";
  LinkedList<File> files  = new LinkedList<File>();
  
  //Constructor
  public Directory(String name) {
    this.directoryName = name;
  }
  
  //setDirectoryName changes the directoryName
  public void setDirectoryName(String name) {
    this.directoryName = name;
  }
  
  //getDirectoryName returns the directoryName
  public String getDirectoryName() {
    return this.directoryName;
  }
  
  //addFile adds a file to the directory
  public void addFile(File file) {
    //adds the file to the files
    files.add(file);
  }

}

// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package data;

import java.util.*; 

public class Directory {

  /*
   * A Directory has a String directoryName and an ArrayList of File: files
   */
  private String directoryName = "";
  private List<File> files  = new ArrayList<File>();
  
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
  
  //addFile adds a file to the Directory
  public void addFile(File file) {
    //adds the file to the files
    files.add(file);
  }
  
  //getFiles returns files stored in the Directory
  public List<File> getFiles() {
    return this.files;
  }
  
  public File getFile(String name) {
    for (File currentFile: this.files) {
      if (currentFile.getFileName().equals(name)) {
        return currentFile;
      }
    }
    
    return null;
  }
  
}

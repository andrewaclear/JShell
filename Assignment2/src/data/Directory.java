package data;

import java.util.*; 

public class Directory {

  //Has a name and contains files in a linked list
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
  
  public boolean compareTo(Directory otherDirectory) {
    return this.directoryName.equals(otherDirectory.getDirectoryName());
  }

}

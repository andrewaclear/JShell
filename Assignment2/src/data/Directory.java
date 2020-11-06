package data;

import java.util.*; 

public class Directory {

  //A Directory has a name: directoryName and Files in a linked list: files
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

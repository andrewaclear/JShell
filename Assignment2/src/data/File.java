package data;

public class File {
  
  //Contains line of strings
  
  String fileName = "";
  String content = "";
  
  //method to change the content of a file
  public void setContent(String content) {
    this.content = content;
  } 
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void getFileName(String fileName) {
    this.fileName = fileName;
  }
  
  //Return the file's name when printing
  public String toString() {
    return this.fileName;
  }
}

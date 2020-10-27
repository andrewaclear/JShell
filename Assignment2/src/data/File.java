package data;

public class File {
  
  //Contains line of strings
  
  String fileName = "";
  String content = "";
  
  //setContent changes the content of a file
  public void setContent(String content) {
    this.content = content;
  } 
  
  //getFileName returns the name of this file: fileName
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  //getFileName returns the name of this file: fileName
  public void getFileName(String fileName) {
    this.fileName = fileName;
  }
  
}

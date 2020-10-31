package data;

public class File {
  
  //File contains a string: content and has a name: fileName
  String fileName = "";
  String content = "";
  
  public File(String name) {
    fileName = name;
  }
  
  //setContent changes the content of a file
  public void setContent(String content) {
    this.content = content;
  } 
  
  //getFileName returns the name of this file: fileName
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  //getFileName returns the name of this file: fileName
  public String getFileName() {
    return this.fileName;
  }
  
  //getContent returns the content of this file: content
  public String getContent() {
    return this.content;
  }
  
}

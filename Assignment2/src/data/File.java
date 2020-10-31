package data;

public class File {
  
  //File contains a string: content and has a name: fileName
  String fileName = "";
  String content = "";
  
  //Constructor
  public File(String name) {
    fileName = name;
  }
  
  //setContent changes the content of a File
  public void setContent(String content) {
    this.content = content;
  } 
  
  //getFileName returns the name of this File: fileName
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  //getFileName returns the name of this File: fileName
  public String getFileName() {
    return this.fileName;
  }
  
  //getContent returns the content of this File: content
  public String getContent() {
    return this.content;
  }
  
}

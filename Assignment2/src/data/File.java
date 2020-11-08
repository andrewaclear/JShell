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

public class File {
  
  //A File has a string: content and a name: fileName
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

package data;

public class File {
  
  //Contains line of strings
  
  String name = "";
  String content = "";
  
  //method to change the content of a file
  public void setContent(String content) {
    this.content = content;
  } 
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void getName(String name) {
    this.name = name;
  }
  
  //Return the file's name when printing
  public String toString() {
    return this.name;
  }
}

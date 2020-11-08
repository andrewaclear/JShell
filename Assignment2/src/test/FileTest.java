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
package test;

import data.File;

public class FileTest {

  public static void main(String[] args) {
    
    //Create a new file with fileName "GoodFile"
    File myFile = new File("GoodFile");
    
    //Print "The name of this file is GoodFile"
    System.out.println("The name of this file is " + myFile.getFileName());
    
    //Set the content of myFile to "This is some real content right inside here"
    myFile.setContent("This is some real content right inside here");
    
    //Print "The content of this file is This is some real content right inside here"
    System.out.println("The content of this file is " +  myFile.getContent());
    
    //Set the content of myFile to "The content is so so"
    myFile.setContent("This content is so so");
    
    //Print "The content of this file is This content is so so"
    System.out.println("The content of this file is " +  myFile.getContent());
    
  //Set the content of myFile to ""
    myFile.setContent("");
    
    //Print "The content of this file is "
    System.out.println("The content of this file is " +  myFile.getContent());
    
  }

}

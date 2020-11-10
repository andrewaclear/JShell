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
    
    //The fileName of myFile should be GoodFile
    if (!(myFile.getFileName().equals("GoodFile"))) {
      System.out.println("ERROR: The name of this file is " 
        + myFile.getFileName());
    }
    
    myFile.setContent("This is some real content right inside here");
    
    //The content of myFileName should be "This is some real content right 
    //inside here"
    if (!(myFile.getContent().equals("This is some real content right "
        + "inside here"))) {
      System.out.println("ERROR: The content of this file is " 
        + myFile.getContent());
    }

    myFile.setContent("This content is so so");
    
    //Print "The content of this file is This content is so so"
    if (!(myFile.getContent().equals("This content is so so"))) {
      System.out.println("ERROR: The content of this file is " 
        +  myFile.getContent());
    }
    
    myFile.setContent("");
    
    //The content of myFile should be ""
    if (!(myFile.getContent().equals(""))) {
      System.out.println("ERROR: The content of this file is " 
       +  myFile.getContent());
    }
    
    System.out.println("DONE");
    
  }

}

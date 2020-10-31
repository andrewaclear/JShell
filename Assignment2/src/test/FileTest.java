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

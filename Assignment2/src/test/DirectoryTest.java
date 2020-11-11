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

import java.util.List;
import data.Directory;
import data.File;

public class DirectoryTest {

  public static void main(String[] args) {

    // Create a new variable of type Directory directory that has directoryName
    // "GoodDirectory"
    Directory directory = new Directory("GoodDirectory");

    System.out.println(
        "The name of this directory is " + directory.getDirectoryName());

    File file = new File("Rando");
    File file2 = new File("Mambo");

    file.setContent("Quality stuff in here");
    file2.setContent("Even more quality stuff in here");


    directory.addFile(file);
    directory.addFile(file2);


    List<File> files = directory.getFiles();

    System.out.println(
        "The directory: " + directory.getDirectoryName() + " has files: ");
    for (File tempFile : files) {
      System.out.println("File: " + tempFile.getFileName()
          + " --- has content: " + tempFile.getContent());
    }

  }

}

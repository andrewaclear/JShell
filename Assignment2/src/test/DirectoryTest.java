package test;

import java.util.List;
import data.Directory;
import data.File;

public class DirectoryTest {

  public static void main(String[] args) {

    //Create a new variable of type Directory directory that has directoryName "GoodDirectory"
    Directory directory = new Directory("GoodDirectory");
    
    System.out.println("The name of this directory is " + directory.getDirectoryName());
    
    File file = new File("Rando");
    File file2 = new File("Mambo");
    
    file.setContent("Quality stuff in here");
    file2.setContent("Even more quality stuff in here");
    
    
    directory.addFile(file);
    directory.addFile(file2);
    
    
    List<File> files = directory.getFiles();
    
    System.out.println("The directory: " + directory.getDirectoryName() + " has files: ");
    for (File tempFile : files) { 
      System.out.println("File: "+ tempFile.getFileName() + " --- has content: "+ tempFile.getContent());
    }
    
  }

}

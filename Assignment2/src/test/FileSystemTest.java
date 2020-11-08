package test;

import data.FileSystem;

public class FileSystemTest {
  
  public static void main(String[] args) {
  
    FileSystem myFileSystem = new FileSystem();
    
    System.out.println(myFileSystem.getRoot().getPath());
    
    System.out.println(myFileSystem.inappropriatePath("a"));
    
  }
}

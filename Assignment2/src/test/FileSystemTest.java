package test;

import data.FileSystem;

public class FileSystemTest {
  
  public static void main(String[] args) {
  
    FileSystem my_file_system = new FileSystem();
    
    System.out.println(my_file_system.getRoot().getPath());
    
  }
}

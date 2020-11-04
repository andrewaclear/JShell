package test;

import commands.ChangeDirectory;
import commands.MakeDirectory;
import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectoryTest {
  public static void main(String[] args) {
  
    FileSystem fSystem = new FileSystem();
    
    MakeDirectory mkdir = new MakeDirectory();
    
    boolean flag = true;
    
    String[] tokens = {"Yo", "Ha"};
    
    flag = mkdir.run(tokens, fSystem);
    flag = mkdir.run(tokens, fSystem);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    ChangeDirectory cd = new ChangeDirectory();
    
    String[] tokensForCD = {"Yo"};
    
    cd.run(tokensForCD, fSystem);
    
    String[] tokens2 = {"/Yo/Hola", "/Yo/Nah"};
    
    flag = mkdir.run(tokens2, fSystem);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    System.out.println(fSystem.getCurrentDirectory().getPath());
    
  }
}

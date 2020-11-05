package test;

import commands.ChangeDirectory;
import commands.MakeDirectory;
import commands.PrintWorkingDirectory;
import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectoryTest {
  public static void main(String[] args) {
    
    //String[] huah = " haa ha".split(" ");
    
    //System.out.println(huah[1]);
    
    
    FileSystem fSystem = new FileSystem();
    
    MakeDirectory mkdir = new MakeDirectory();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    
    boolean flag = true;
    
    String[] tokens = {"mkdir","Yo", "Ha"};
    
    flag = mkdir.run(tokens, fSystem);
    flag = mkdir.run(tokens, fSystem);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    pwd.run(tokens, fSystem);
    
    ChangeDirectory cd = new ChangeDirectory();
    
    String[] tokensForCD = {"cd","Yo"};
    
    cd.run(tokensForCD, fSystem);
    
    String[] tokens2 = {"mkdir","/Yo/Hola", "/Yo/Nah"};
    
    flag = mkdir.run(tokens2, fSystem);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    pwd.run(tokens, fSystem);
    
    String[] tokensForCD2 = {"cd","Yo"};
    
    cd.run(tokensForCD2, fSystem);
    
    String[] tokensForCD3 = {"cd","Hola"};
    
    cd.run(tokensForCD3, fSystem);
    
  }
}

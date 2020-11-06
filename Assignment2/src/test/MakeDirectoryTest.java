package test;

import commands.ChangeDirectory;
import commands.MakeDirectory;
import commands.PrintWorkingDirectory;
import data.Cache;
import data.FileSystem;
import data.FileSystemNode;

public class MakeDirectoryTest {
  public static void main(String[] args) {
    
    //String[] huah = " haa ha".split(" ");
    
    //System.out.println(huah[1]);
    
    
    FileSystem fSystem = new FileSystem();
    Cache cache = new Cache();
    
    MakeDirectory mkdir = new MakeDirectory();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    
    boolean flag = true;
    
    String[] tokens = {"mkdir","Yo", "Ha"};
    
    flag = mkdir.run(tokens, fSystem, cache);
    flag = mkdir.run(tokens, fSystem, cache);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    pwd.run(tokens, fSystem, cache);
    
    ChangeDirectory cd = new ChangeDirectory();
    
    String[] tokensForCD = {"cd","Yo"};
    
    cd.run(tokensForCD, fSystem, cache);
    
    String[] tokens2 = {"mkdir","/Yo/Hola", "/Yo/Nah"};
    
    flag = mkdir.run(tokens2, fSystem, cache);
    
    for (FileSystemNode child : fSystem.getCurrentDirectory().getChildren()) {
      System.out.println("A child of this directory is " + child.getDirectory().getDirectoryName());
    }
    
    pwd.run(tokens, fSystem, cache);
    
    String[] tokensForCD2 = {"cd","Yo"};
    
    cd.run(tokensForCD2, fSystem, cache);
    
    String[] tokensForCD3 = {"cd","Hola"};
    
    cd.run(tokensForCD3, fSystem, cache);
    
  }
}

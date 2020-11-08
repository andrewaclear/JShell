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

package test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import org.junit.Test;
import commands.Command;
import commands.MakeDirectory;
import commands.Redirection;
import commands.Tree;
import data.FileSystem;
import driver.JShell;

public class TreeTest {
  
  private JShell shell;
  
  public void setUp() throws Exception
  {
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem()); 
  }
  
  public void tearDown() throws Exception
  {
    Field field = (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field.setAccessible(true);
    field.set(null, null);
  }
  
  @Test
  public void runTest1() throws Exception {
    setUp();
    
    MakeDirectory mkdir = new MakeDirectory();
    String[] mkdirTokens = {"mkdir", "a", "b", "c"};
    mkdir.run(mkdirTokens, shell);
    
    Tree tree = new Tree();
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  a\n  b\n  c",actualOutput);
    assertEquals(null, actualErrors);
    
    tearDown();
  }
  
  
  @Test
  public void runTest2() throws Exception {
    setUp();
    
    MakeDirectory mkdir = new MakeDirectory();
    String[] mkdirTokens = {"mkdir", "a", "b", "c", "b/banana", "/b/apple"};
    mkdir.run(mkdirTokens, shell);
    
    Tree tree = new Tree();
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  a\n  b\n    banana\n    apple\n  c", actualOutput);
    assertEquals(null, actualErrors);
    
    tearDown();
  }
  
  
  public void runTest3() throws Exception {
    setUp();
    
    MakeDirectory mkdir = new MakeDirectory();
    String[] mkdirTokens = {"mkdir", "a", "b", "c", "b/banana", "/c/apple"};
    mkdir.run(mkdirTokens, shell);
    
    Redirection redirectionCommand = new Redirection();
    String[] redirectionTokens = {"redirect", "\"banana\"", ">", "ooga"};
    redirectionCommand.run(redirectionTokens, shell);
    
    Tree tree = new Tree();
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  ooga\n  a\n  b\n    banana\n  c\n    apple\n", 
        actualOutput);
    assertEquals(null, actualErrors);
    
    tearDown();
  }
  
  public void runTest4() throws Exception {
    setUp();
    
    MakeDirectory mkdir = new MakeDirectory();
    String[] mkdirTokens = {"mkdir", "a", "b", "c", "b/banana", "/c/apple"};
    mkdir.run(mkdirTokens, shell);
    
    Redirection redirectionCommand = new Redirection();
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirectionCommand.run(redirectionTokens1, shell);

    String[] redirectionTokens2 = {"redirect", "\"apple\"", ">", "b/hue"};
    redirectionCommand.run(redirectionTokens2, shell);
    
    Tree tree = new Tree();
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  ooga\n  a\n  b\n    hue\n    banana\n    apple\n  c", 
        actualOutput);
    assertEquals(null, actualErrors);
    
    tearDown();
  }
}

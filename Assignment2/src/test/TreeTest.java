package test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Command;
import commands.MakeDirectory;
import commands.Redirection;
import commands.Tree;
import data.FileSystem;
import driver.JShell;

public class TreeTest {
  
  private JShell shell;
  private MakeDirectory mkdir = new MakeDirectory();
  private Tree tree = new Tree();
  private Redirection redirectionCommand = new Redirection();
  
  @Before
  public void setUp() throws Exception
  {
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem()); 
  }
  
  
  @After
  public void tearDown() throws Exception
  {
    Field field = (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field.setAccessible(true);
    field.set(null, null);
  }
  
  @Test
  public void runTest1() throws Exception {
    String[] mkdirTokens = {"mkdir", "a", "b", "c"};
    mkdir.run(mkdirTokens, shell);

    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  a\n  b\n  c",actualOutput);
    assertEquals(null, actualErrors);
  }
  
  
  @Test
  public void runTest2() throws Exception {
    
    String[] mkdirTokens = {"mkdir", "kha", "bruh", "c", "bruh/banana", 
        "/bruh/apple"};
    mkdir.run(mkdirTokens, shell);
    
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  kha\n  bruh\n    banana\n    apple\n  c", actualOutput);
    assertEquals(null, actualErrors);
  }
  
  
  @Test
  public void runTest3() throws Exception {

    String[] mkdirTokens = {"mkdir", "a", "b", "c", "b/banana", "/c/apple"};
    mkdir.run(mkdirTokens, shell);
    
    Redirection redirectionCommand = new Redirection();
    String[] redirectionTokens = {"redirect", "\"banana\"", ">", "ooga"};
    redirectionCommand.run(redirectionTokens, shell);

    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  ooga\n  a\n  b\n    banana\n  c\n    apple", 
        actualOutput);
    assertEquals(null, actualErrors);
  }
  
  @Test
  public void runTest4() throws Exception {

    String[] mkdirTokens = {"mkdir", "a", "b", "c", "b/banana", "/c/apple"};
    mkdir.run(mkdirTokens, shell);
    
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirectionCommand.run(redirectionTokens1, shell);

    String[] redirectionTokens2 = {"redirect", "\"apple\"", ">", "b/hue"};
    redirectionCommand.run(redirectionTokens2, shell);
    
    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/\n  ooga\n  a\n  b\n    hue\n    banana\n  c\n    apple", 
        actualOutput);
    assertEquals(null, actualErrors);
  }
  
  
  @Test
  public void runTest5() throws Exception {

    String[] treeTokens = {"tree"};
    Command theResultingCommand = tree.run(treeTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/",actualOutput);
    assertEquals(null, actualErrors);
  }
  
}

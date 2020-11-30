package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.ChangeDirectory;
import commands.Command;
import commands.Concatenate;
import commands.Copy;
import commands.MakeDirectory;
import commands.Redirection;
import commands.Tree;
import data.FileSystem;
import driver.JShell;

public class CopyTest {

  private JShell shell;
  private MakeDirectory mkdir = new MakeDirectory();
  private Tree tree = new Tree();
  private ChangeDirectory cd = new ChangeDirectory();
  private Redirection redirection = new Redirection();
  private Copy cp = new Copy();
  private Concatenate cat = new Concatenate();
  
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
  public void runTest1() {
    String[] cpTokens = {"cp", "/", "/"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals("cp: / is inside /", actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/",actualOutput);
  }
  
  @Test
  public void runTest2() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyheyhey\"", ">", "ka"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cpTokens = {"cp", "dir1", "dir2"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals(null, actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n  dir2\n    dir1\n  dir3\n  ooga\n  ka", 
        actualOutput);
  }
  
  
  @Test
  public void runTest3() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/aaa"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cpTokens = {"cp", "dir1", "dir2"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals(null, actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    aaa\n  dir2\n    orange\n"
        + "    dir1\n      banana\n      aaa\n  dir3\n  ooga", actualOutput);
  }
  
  
  @Test
  public void runTest4() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/ooga", "/dir1"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals(null, actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    String[] catTokens = {"cat", "/dir1/ooga"};
    Command theCheckFileCommand = cat.run(catTokens, shell);
    String theFileContent = theCheckFileCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n  dir2\n    "
        + "orange\n  dir3\n  ooga", actualOutput);
    assertEquals("banana", theFileContent);
  }
  
  
  @Test
  public void runTest5() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/ooga", "/dir1/risa"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals(null, actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    String[] catTokens = {"cat", "/dir1/risa"};
    Command theCheckFileCommand = cat.run(catTokens, shell);
    String theFileContent = theCheckFileCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n    risa\n  "
        + "dir2\n    orange\n  dir3\n  ooga", actualOutput);
    assertEquals("banana", theFileContent);
  }
  
  
  @Test
  public void runTest6() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/wtf/is/this", "/dir1"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals("cp: \"/wtf/is/this\": No such file or directory", 
        actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n  "
        + "dir2\n    orange\n  dir3\n  ooga", actualOutput);
  }
  
  @Test
  public void runTest7() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/dir1", "/wtf/is/this"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals("cp: \"/wtf/is/this\": No such file or directory", 
        actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n  "
        + "dir2\n    orange\n  dir3\n  ooga", actualOutput);
  }
  
  @Test
  public void runTest8() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/wtf/is/this1", "/wtf/is/this2"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals("cp: \"/wtf/is/this1\": No such file or directory", 
        actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n  "
        + "dir2\n    orange\n  dir3\n  ooga", actualOutput);
  }
  
  
  @Test
  public void runTest9() {
    String[] mkdirTokens = {"mkdir", "dir1", "dir2", "dir3", "dir1/banana", 
       "dir2/orange"};
    mkdir.run(mkdirTokens, shell);
    
    String[] redirectionTokens1 = {"redirect", "\"banana\"", ">", "ooga"};
    redirection.run(redirectionTokens1, shell);
    
    String[] redirectionTokens2 = {"redirect", "\"heyoah\"", ">", "dir1/ooga"};
    redirection.run(redirectionTokens2, shell);
    
    String[] cdTokens = {"cd", "dir3"};
    cd.run(cdTokens, shell);
    
    String[] cpTokens = {"cp", "/dir1", "/ooga"};
    Command theResultingCommand = cp.run(cpTokens, shell);
    String actualErrors = theResultingCommand.getErrors();
    assertEquals("cp: cannot move directory at /dir1 because /ooga "
        + "refers to a file", actualErrors);

    String[] treeTokens = {"tree"};
    Command theCheckCommand = tree.run(treeTokens, shell);
    String actualOutput = theCheckCommand.getOutput();
    
    assertEquals("/\n  dir1\n    banana\n    ooga\n  "
        + "dir2\n    orange\n  dir3\n  ooga", actualOutput);
  }
}

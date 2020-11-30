package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.ChangeDirectory;
import commands.MakeDirectory;
import data.Directory;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;

public class FileSystemNodeTest {

  private JShell shell;
  private MakeDirectory mkdir = new MakeDirectory();
  private ChangeDirectory cd = new ChangeDirectory();
  FileSystem fSystem;
  
  @Before
  public void setUp() throws Exception
  {
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem()); 
    fSystem = shell.getfSystem();
  }
  
  @After
  public void tearDown() throws Exception
  {
    Field field = (shell.getfSystem().getClass()).getDeclaredField(
        "fileSystem");
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  public void getPathTest1() {
    
    String actualOutput = fSystem.getRoot().getPath();
    
    assertEquals("/", actualOutput);
    
  }
  
  
  @Test
  public void getPathTest2() {
    String[] mkdirTokens = {"mkdir", "bokay", "nani", "hai"};
    mkdir.run(mkdirTokens, shell);
    
    String[] cdTokens1 = {"cd", "nani"};
    cd.run(cdTokens1, shell);
    
    String actualOutput = fSystem.getCurrentDirectory().getPath();

    assertEquals("/nani", actualOutput);
    
  }
  
  
  @Test
  public void getPathTest3() {
    String[] mkdirTokens = {"mkdir", "braaaaaaah", "nani", "kakashi", 
        "nani/tenia", "nani/banana", "nani/banana/supreme", "kakashi/weeb",
        "/nani/banana/surprise"};
    mkdir.run(mkdirTokens, shell);
    
    String[] cdTokens1 = {"cd", "nani/banana/supreme"};
    cd.run(cdTokens1, shell);
    
    String actualOutput = fSystem.getCurrentDirectory().getPath();
    
    assertEquals("/nani/banana/supreme", actualOutput);
  }
  
  
  @Test
  public void cloneFileSystemNodeTest1() {
    
    FileSystemNode actualOutput = new FileSystemNode(new Directory("Dummy"));
    actualOutput = fSystem.getRoot().cloneFileSystemNodeInto(actualOutput);
    
    assertEquals("/", actualOutput.getDirectory().getDirectoryName());
    assertEquals(null, actualOutput.getParent());
  }
  
  
  @Test
  public void cloneFileSystemNodeTest2() {
    String[] mkdirTokens = {"mkdir", "braaaaaaah", "nani", "kakashi", 
        "nani/tenia", "nani/banana", "nani/tenia/tango"};
    mkdir.run(mkdirTokens, shell);
    
    FileSystemNode actualOutput = new FileSystemNode(new Directory("Dummy"));
    actualOutput = fSystem.getRoot().getChildByDirectoryName("nani").
        cloneFileSystemNodeInto(actualOutput);
    
    assertEquals("nani", actualOutput.getDirectory().getDirectoryName());
    assertEquals(true, actualOutput.isChildInside("tenia"));
    assertEquals(true, actualOutput.isChildInside("banana"));
    assertEquals(true, actualOutput.getChildByDirectoryName("tenia").
        isChildInside("tango"));
  }
}

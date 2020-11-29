package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.ChangeDirectory;
import commands.Command;
import commands.MakeDirectory;
import commands.PrintWorkingDirectory;
import data.FileSystem;
import driver.JShell;

public class PrintWorkingDirectoryTest {

  private JShell shell;
  private MakeDirectory mkdir = new MakeDirectory();
  private ChangeDirectory cd = new ChangeDirectory();
  private PrintWorkingDirectory pwd = new PrintWorkingDirectory();
  
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
    String[] pwdTokens = {"pwd"};
    Command theResultingCommand = pwd.run(pwdTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/", actualOutput);
    assertEquals(null, actualErrors);
  }
  
  @Test
  public void runTest2() {
    
    String[] mkdirTokens = {"mkdir", "bruh", "kha", "hai"};
    mkdir.run(mkdirTokens, shell);
    
    String[] cdTokens = {"cd", "bruh"};
    cd.run(cdTokens, shell);
    
    String[] pwdTokens = {"pwd"};
    Command theResultingCommand = pwd.run(pwdTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/bruh", actualOutput);
    assertEquals(null, actualErrors);
  }
  
  
  @Test
  public void runTest3() {
    
    String[] mkdirTokens = {"mkdir", "okay", "no", "yes", "yes/maybe", "yes/ok", 
        "idk", "idk/wtf", "yes/maybe/ha"};
    mkdir.run(mkdirTokens, shell);
    
    String[] cdTokens = {"cd", "yes/maybe/ha"};
    cd.run(cdTokens, shell);
    
    String[] pwdTokens = {"pwd"};
    Command theResultingCommand = pwd.run(pwdTokens, shell);
    String actualOutput = theResultingCommand.getOutput();
    String actualErrors = theResultingCommand.getErrors();
    
    assertEquals("/yes/maybe/ha", actualOutput);
    assertEquals(null, actualErrors);
  }
  
}

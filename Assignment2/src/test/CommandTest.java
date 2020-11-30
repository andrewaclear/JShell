/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.Command;
import data.FileSystem;
import driver.JShell;

/**
 * @author a
 *
 */
public class CommandTest {

  private JShell shell;
  private Command command;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    command = new Command();
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem()); 
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    Field field = (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field.setAccessible(true);
    field.set(null, null);
  }


  /**
   * Test method for {@link commands.Command#containsArrow(java.lang.String[])}.
   */
  @Test
  public final void testContainsArrow() {
    String[] tokens = {};
    assertFalse(command.containsArrow(tokens));

    String[] tokens0 = {""};
    assertFalse(command.containsArrow(tokens0));    

    String[] tokens1 = {"hi", "this", "was", "/path/like", "fun"};
    assertFalse(command.containsArrow(tokens1));

    String[] tokens2 = {">"};
    assertTrue(command.containsArrow(tokens2));

    String[] tokens3 = {">>"};
    assertTrue(command.containsArrow(tokens3));

    String[] tokens4 = {"like", ">", "so"};
    assertTrue(command.containsArrow(tokens4));
  }

  /**
   * one file
   * 
   * Test method for {@link commands.Command#checkRun(java.lang.String[], driver.JShell)}.
   */
  @Test
  public final void testCheckRun0() {
    String[] tokens = {};
    Command ret;

    ret = command.checkRun(tokens, shell);
    assertEquals("expected", ret.getErrors());



  }

}

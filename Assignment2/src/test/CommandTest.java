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
    // shell = new JShell();
    // shell.setfSystem(FileSystem.createFileSystem()); 
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    // Field field = (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    // field.setAccessible(true);
    // field.set(null, null);
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
    JShell shell = new JShell();
    String[] tokens = {};
    Command ret;

    ret = command.checkRun(tokens, shell);
    assertEquals("expected", ret.getErrors());


  }

}

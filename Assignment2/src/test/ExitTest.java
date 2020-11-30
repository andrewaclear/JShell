/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.Command;
import commands.Exit;
import driver.JShell;

/**
 * @author a
 *
 */
public class ExitTest {

  private JShell shell;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    shell = new JShell();
  }

  /**
   * Test method for {@link commands.Exit#run(java.lang.String[], driver.JShell)}.
   */
  @Test
  public final void testRun() {
    Exit ex = new Exit();
    String[] tokens = {"exit"};
    Command terminate = ex.run(tokens, shell);
    assertEquals("exit", terminate.getIdentifier());
    assertEquals(null, ex.getErrors());
  }

}

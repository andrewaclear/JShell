package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Echo;
import data.FileSystem;
import driver.JShell;
import runtime.ErrorHandler;

public class ErrorHandlerTest {

  private JShell shell;

  @Before
  public void setUp() throws Exception {
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem());

  }

  @After
  public void tearDown() throws Exception {
    Field field =
        (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  public void invalidComboOfParamsTest() {
    Echo echo = new Echo();
    String[] actual = {"echo", "John", "Marry", "Thomas"};
    assertEquals("Wrong error returned",
        "echo: John Marry Thomas : Invalid combination of arguments",
        ErrorHandler.invalidComboOfParams(echo, actual));
  }

  @Test
  public void missingStringTest() {
    Echo echo = new Echo();
    String[] actual = {"echo", "John"};
    assertEquals("Wrong error returned",
        "echo: John: No string found, format string as \"string\"",
        ErrorHandler.missingString(echo, actual));
  }
}

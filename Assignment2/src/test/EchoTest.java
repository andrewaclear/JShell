package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Concatenate;
import commands.Echo;
import data.FileSystem;
import driver.JShell;

public class EchoTest {

  private Echo echo;
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
  //Note: No need to handle invalid strings, since Parser takes care of them
  // Correctly prints the given string and handles bad input
  @Test
  public void runTest1() throws Exception {
    setUp();
    echo = new Echo();

    String[] actual = {"echo", "\"  Hello the world, test the world.  \""};
    assertEquals("echo did not correctly print string",
        "  Hello the world, test the world.  ",
        echo.checkRun(actual, shell).getOutput());
    String[] actual2 =
        {"echo", "dssaasdsd", "\"  Hello the world, test the world.  \""};
    assertEquals("echo did not correctly print string",
        "echo: dssaasdsd: No string found, format string as \"string\"",
        echo.checkRun(actual2, shell).getErrors());
    String[] actual3 =
      {"echo", "testering"};
  assertEquals("echo did not correctly print string",
      "echo: testering: No string found, format string as \"string\"",
      echo.checkRun(actual3, shell).getErrors());


    tearDown();
  }

}

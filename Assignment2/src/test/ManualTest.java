package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.ChangeDirectory;
import commands.ClientUrl;
import commands.Concatenate;
import commands.Copy;
import commands.Echo;
import commands.Exit;
import commands.History;
import commands.ListContents;
import commands.LoadJShell;
import commands.MakeDirectory;
import commands.Manual;
import commands.Move;
import commands.PopDirectory;
import commands.PrintWorkingDirectory;
import commands.PushDirectory;
import commands.Redirection;
import commands.Remove;
import commands.SaveJShell;
import commands.Search;
import commands.Tree;
import data.FileSystem;
import driver.JShell;

public class ManualTest {

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

  // Test if all commands are in Manual and that error is given when
  // unrecognized command is given
  @Test
  public void runTest() {
    Manual man = new Manual();
    HashMap<String, String> expected = new HashMap<String, String>();

    expected.put("man", man.getDescription());
    expected.put("cd", new ChangeDirectory().getDescription());
    expected.put("cat", new Concatenate().getDescription());
    expected.put("echo", new Echo().getDescription());
    expected.put("exit", new Exit().getDescription());
    expected.put("history", new History().getDescription());
    expected.put("ls", new ListContents().getDescription());
    expected.put("mkdir", new MakeDirectory().getDescription());
    expected.put("popd", new PopDirectory().getDescription());
    expected.put("pwd", new PrintWorkingDirectory().getDescription());
    expected.put("pushd", new PushDirectory().getDescription());
    expected.put("search", new Search().getDescription());
    expected.put("loadJShell", new LoadJShell().getDescription());
    expected.put("saveJShell", new SaveJShell().getDescription());
    expected.put("tree", new Tree().getDescription());
    expected.put("mv", new Move().getDescription());
    expected.put("cp", new Copy().getDescription());
    expected.put("rm", new Remove().getDescription());
    expected.put("curl", new ClientUrl().getDescription());
    expected.put("redirect", new Redirection().getDescription());

    String[] actual = {"man", "cd", "cat", "echo", "exit", "history", "ls",
        "mkdir", "popd", "pwd", "pushd", "rm", "tree", "saveJShell",
        "loadJShell", "curl", "mv", "search", "cp", "redirect"};


    String[] manRun = {"man", ""};
    for (int i = 0; i < actual.length; i++) {
      manRun[1] = actual[i];
      assertNotNull("Missing Manual description",
          man.run(manRun, shell).getOutput());
      assertEquals(
          "Manual has wrong description ", "Documentation for: " + actual[i]
              + "\n" + expected.get(actual[i]) + "\n",
          man.run(manRun, shell).getOutput());
    }
  }

  // Test invalid inputs
  @Test
  public void runTest2() {
    Manual man = new Manual();

    String[] actual = {"man", "cat", "dog"};

    assertEquals("Manual did not return correct error",
        "man: cat dog : Invalid combination of arguments",
        man.checkRun(actual, shell).getErrors());
    String[] actual2 = {"man", "dog"};
    assertEquals("Manual did not return correct error",
        "man: No manual entry for: dog", man.run(actual2, shell).getErrors());
  }

}

package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.MakeDirectory;
import commands.Redirection;
import data.*;
import driver.JShell;

public class RedirectionTest {
  private Redirection redirect;
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

  // Test if redirect commands with output using >
  @Test
  public void runTest1() {
    redirect = new Redirection();
    String[] testRedirect = {"redirect", "Hello the world", ">", "file"};

    redirect.checkRun(testRedirect, shell);
    assertNotNull("Redirection did correctly make file", shell.getfSystem()
        .getCurrentDirectory().getDirectory().getFileByFileName("file"));
    assertEquals("Redirection has correct file contents", "Hello the world",
        shell.getfSystem().getCurrentDirectory().getDirectory()
            .getFileByFileName("file").getContent());

    // Correct overrides file with same name
    String[] testRedirect2 =
        {"redirect", "Apples, peaches and oranges", ">", "file"};

    redirect.checkRun(testRedirect2, shell);
    assertNotNull("Redirection did correctly make file", shell.getfSystem()
        .getCurrentDirectory().getDirectory().getFileByFileName("file"));
    assertEquals("Redirection has correct file contents",
        "Apples, peaches and oranges", shell.getfSystem().getCurrentDirectory()
            .getDirectory().getFileByFileName("file").getContent());

  }

  // Test if redirect commands with output using >>
  @Test
  public void runTest2() {
    redirect = new Redirection();
    String[] testRedirect = {"redirect", "Hello the world", ">>", "file"};

    redirect.checkRun(testRedirect, shell);
    assertNotNull("Redirection did correctly make file", shell.getfSystem()
        .getCurrentDirectory().getDirectory().getFileByFileName("file"));
    assertEquals("Redirection has correct file contents", "Hello the world",
        shell.getfSystem().getCurrentDirectory().getDirectory()
            .getFileByFileName("file").getContent());

    // Correct appends file contents with files that have same name
    String[] testRedirect2 =
        {"redirect", "Apples, peaches and oranges", ">>", "file"};

    redirect.checkRun(testRedirect2, shell);
    assertNotNull("Redirection did correctly make file", shell.getfSystem()
        .getCurrentDirectory().getDirectory().getFileByFileName("file"));
    assertEquals("Redirection has correct file contents",
        "Hello the worldApples, peaches and oranges",
        shell.getfSystem().getCurrentDirectory().getDirectory()
            .getFileByFileName("file").getContent());
  }

  // Test if redirect commands with an invalid file name
  @Test
  public void runTest3() {
    redirect = new Redirection();
    String[] testRedirect = {"redirect", "Hello the world", ">", "g@mmar*y"};

    redirect = (Redirection) redirect.checkRun(testRedirect, shell);
    assertEquals("Redirection does not give the correct error",
        "redirect: \"g@mmar*y\": Invalid file and/or directory name",
        redirect.getOutput() != null ? redirect.getOutput()
            : "" + redirect.getErrors() != null ? redirect.getErrors() : "");
  }

  // Test if redirect commands with a valid or invalid file path
  @Test
  public void runTest4() {
    redirect = new Redirection();
    MakeDirectory mk = new MakeDirectory();
    String[] mkTest = {"mkdir", "dir1", "dir1/a", "dir1/a/b", "dir2"};
    mk.checkRun(mkTest, shell);
    String[] testRedirect =
        {"redirect", "Hello the world", ">", "dir1/a/b/file"};

    redirect = (Redirection) redirect.checkRun(testRedirect, shell);
    assertNotNull("Redirection did create file at proper directory",
        shell.getfSystem().getSemiFileSystemNode("dir1/a/b/file"));

    String[] testRedirect2 =
        {"redirect", "Hello the world", ">", "dir1/c/b/file"};

    redirect = (Redirection) redirect.checkRun(testRedirect2, shell);
    assertEquals("Redirection did create file at proper directory",
        "redirect: \"dir1/c/b/file\": No such file or directory",
        redirect.getOutput() != null ? redirect.getOutput()
            : "" + redirect.getErrors() != null ? redirect.getErrors() : "");
  }

  // Test redirect with > or >> missing
  @Test
  public void runTest5() {
    redirect = new Redirection();
    String[] testRedirect = {"redirect", "Hello the world"};

    redirect = (Redirection) redirect.checkRun(testRedirect, shell);
    assertEquals("Redirection does not give the correct error",
        "redirect: Hello the world : Invalid combination of arguments",
        redirect.getOutput() != null ? redirect.getOutput()
            : "" + redirect.getErrors() != null ? redirect.getErrors() : "");
  }

}

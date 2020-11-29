package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.ClientUrl;
import commands.Concatenate;
import commands.MakeDirectory;
import commands.Redirection;
import data.FileSystem;
import driver.JShell;

public class ConcatenateTest {

  private Concatenate cat;
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

  // Check with file, with multiple files and with invalid files
  @Test
  public void runTest1() throws Exception {
    setUp();
    cat = new Concatenate();
    Redirection redirect = new Redirection();
    String[] createFile1 = {"redirect", "apples and oranges", ">", "file1"};
    String[] createFile2 = {"redirect", "rocks and sticks", ">", "file2"};
    String[] createFile3 = {"redirect", "plane and train", ">", "file3"};

    redirect.run(createFile1, shell);
    redirect.run(createFile2, shell);
    redirect.run(createFile3, shell);
    String[] actual = {"cat", "file1"};
    assertEquals("cat did not correctly print contents of the file",
        "apples and oranges", cat.checkRun(actual, shell).getOutput());
    String[] actual2 = {"cat", "file1", "file3", "file2"};
    assertEquals("cat did not correctly print contents of the files",
        "apples and oranges\n\n\nplane and train\n\n\nrocks and sticks",
        cat.checkRun(actual2, shell).getOutput());
    String[] actual3 = {"cat", "file1", "sdas", "file2"};
    cat = (Concatenate) cat.checkRun(actual3, shell);
    assertEquals("cat did not correctly print contents of the files and error",
        "apples and oranges\ncat: \"sdas\": No such file or directory",
        cat.getOutput() + "\n" + cat.getErrors());

    tearDown();
  }

  // Check with paths and invalid paths
  @Test
  public void runTest2() throws Exception {
    setUp();
    cat = new Concatenate();
    MakeDirectory make = new MakeDirectory();
    String[] createDir1 = {"mkdir", "dir1", "dir1/a", "dir1/b"};
    String[] createDir2 = {"mkdir", "dir2", "dir2/c", "dir2/d"};
    make.checkRun(createDir1, shell);
    make.checkRun(createDir2, shell);
    Redirection redirect = new Redirection();
    String[] createFile1 =
        {"redirect", "apples and oranges", ">", "dir1/a/file1"};
    String[] createFile2 = {"redirect", "rocks and sticks", ">", "dir2/file2"};
    String[] createFile3 = {"redirect", "plane and train", ">", "dir2/d/file3"};
    String[] createFile4 = {"redirect", "room", ">", "dir2/file4"};

    redirect.run(createFile1, shell);
    redirect.run(createFile2, shell);
    redirect.run(createFile3, shell);
    redirect.run(createFile4, shell);

    String[] actual = {"cat", "dir1/a/file1"};
    assertEquals("cat did not correctly find and print file at specified path",
        "apples and oranges", cat.checkRun(actual, shell).getOutput());
    String[] actual2 = {"cat", "dir2/file2"};
    assertEquals("cat did not correctly find and print file at specified path",
        "rocks and sticks", cat.checkRun(actual2, shell).getOutput());
    String[] actual3 =
        {"cat", "dir2/file2", "dir2/john/jack/file2", "dir1/a/file1"};
    cat = (Concatenate) cat.checkRun(actual3, shell);
    assertEquals(
        "cat did not correctly find and print file at specified path and stop"
            + " and then return the correct errors",
        "rocks and sticks\ncat: \"dir2/john/jack/file2\": No such file or"
            + " directory",
        cat.getOutput() + "\n" + cat.getErrors());
    tearDown();
  }

}

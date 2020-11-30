package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Echo;
import commands.History;
import commands.LoadJShell;
import commands.MakeDirectory;
import commands.SaveJShell;
import commands.Tree;
import data.Cache;
import data.FileSystem;
import driver.JShell;

public class SaveJShellTest {

  private SaveJShell saveShell;
  private JShell shell;
  private JShell testShell;

  @Before
  public void setUp() throws Exception {
    shell = new JShell();
    shell.setfSystem(FileSystem.createFileSystem());
    testShell = new JShell();
    testShell.setfSystem(FileSystem.createFileSystem());
    testShell.setCache(new Cache());
  }

  @After
  public void tearDown() throws Exception {
    Field field =
        (shell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field.setAccessible(true);
    field.set(null, null);

    Field field2 =
        (testShell.getfSystem().getClass()).getDeclaredField("fileSystem");
    field2.setAccessible(true);
    field2.set(null, null);
  }

  // Checks that history and fileSystem are correctly loaded
  // Note: Loads from file called testload saved in svn
  @Test
  public void testRun() throws Exception {
    MakeDirectory make = new MakeDirectory();
    Echo echo = new Echo();
    String[] createDir1 =
        {"mkdir", "dir1", "dir1/a", "dir1/b", "dir2", "dir2/c", "dir2/c/g"};
    String[] echoOut = {"echo", "\"fdss\"", ">", "dir1/a/file1"};
    make.checkRun(createDir1, shell);
    echo.checkRun(echoOut, shell);
    shell.getCache()
        .addHistoryLine("mkdir dir1 dir1/a dir1/b dir2 dir2/c dir2/c/g");
    shell.getCache().addHistoryLine("echo \"fdss\" > dir1/a/file1");
    shell.getCache().addHistoryLine("ls");
    shell.getCache().addHistoryLine("cd dir1/a");
    shell.getCache().addHistoryLine("ls");
    shell.getCache().addHistoryLine("cat file1");
    shell.getCache().addHistoryLine("history");
    shell.getCache().addHistoryLine("history");
    shell.getCache().addHistoryLine("history 1");
    shell.getCache().addHistoryLine("history 100");

    SaveJShell save = new SaveJShell();
    String[] tokens = {"saveJShell", "testerSave"};
    SaveJShell saveTest = (SaveJShell) save.run(tokens, shell);

    LoadJShell loader =  new LoadJShell();
    String[] actual = {"loadJShell", "testerSave"};
    testShell.setCache(new Cache());
    loader.run(actual, testShell);

    History his = new History();
    History his2 = new History();
    String[] setHis = {"history"};

    assertEquals("saveJShell did not correctly save history",
        his.run(setHis, shell).getOutput(),
        his2.run(setHis, testShell).getOutput());
    assertEquals("saveJShell did not correctly save current directory",
        shell.getfSystem().getCurrentDirectory().getDirectory()
            .getDirectoryName(),
        testShell.getfSystem().getCurrentDirectory().getDirectory()
            .getDirectoryName());
    Tree tree = new Tree();
    String[] setTree = {"tree"};
    assertEquals("saveJShell did not correctly save filesystem",
        tree.checkRun(setTree, shell).getOutput(),
        tree.checkRun(setTree, testShell).getOutput());
  }
  
  //Test if correctly overrites file with same name
  @Test
  public void testRun2() throws Exception {
    MakeDirectory make = new MakeDirectory();
    Echo echo = new Echo();
    String[] createDir1 =
        {"mkdir", "dir1", "dir1/a", "dir1/b"};
    String[] echoOut = {"echo", "\"apples\"", ">", "dir1/a/file1"};
    make.checkRun(createDir1, shell);
    echo.checkRun(echoOut, shell);
    shell.getCache()
        .addHistoryLine("mkdir dir1 dir1/a dir1/b");
    shell.getCache().addHistoryLine("echo \"apples\" > dir1/a/file1");
    shell.getCache().addHistoryLine("ls");
    
    SaveJShell save = new SaveJShell();
    String[] tokens = {"saveJShell", "testerSave"};
    SaveJShell saveTest = (SaveJShell) save.run(tokens, shell);
    
    LoadJShell loader =  new LoadJShell();
    String[] actual = {"loadJShell", "testerSave"};
    testShell.setCache(new Cache());
    loader.run(actual, testShell);

    History his = new History();
    History his2 = new History();
    String[] setHis = {"history"};
    
    assertEquals("saveJShell did not correctly save history",
        his.run(setHis, shell).getOutput(),
        his2.run(setHis, testShell).getOutput());
    assertEquals("saveJShell did not correctly save current directory",
        shell.getfSystem().getCurrentDirectory().getDirectory()
            .getDirectoryName(),
        testShell.getfSystem().getCurrentDirectory().getDirectory()
            .getDirectoryName());
    Tree tree = new Tree();
    String[] setTree = {"tree"};
    assertEquals("saveJShell did not correctly save filesystem",
        tree.checkRun(setTree, shell).getOutput(),
        tree.checkRun(setTree, testShell).getOutput());
  }

}

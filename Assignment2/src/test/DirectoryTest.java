package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import data.Directory;
import data.File;

public class DirectoryTest {
  
  private Directory directory;

  @Before
  public void setUp() throws Exception
  {
    directory = new Directory("Nani");

  }
  
  @Test
  public void getFileTest1() {
    
    File file1 = new File("yes");
    file1.setContent("AGHA");
    File file2 = new File("no");
    file2.setContent("IAGAGAGA ASGAG@%%");
    File file3 = new File("okay");
    file3.setContent("iahgfhasf 23562536 @#%@#%");
    directory.addFile(file1);
    directory.addFile(file2);
    directory.addFile(file3);

    
    File actualOutput = directory.getFile("no");
    
    assertEquals(file2, actualOutput);
  }
  
  @Test
  public void getFileTest2() {
    
    File actualOutput = directory.getFile("iDontExist");
    
    assertEquals(null, actualOutput);
  }
  
  
  @Test
  public void removeFileTest1() {
    
    File file1 = new File("insane");
    file1.setContent("Atestsss");
    File file2 = new File("lit");
    file2.setContent("PogChamp");
    File file3 = new File("cannotSee");
    file3.setContent("dokay");
    
    directory.addFile(file1);
    directory.addFile(file2);
    directory.addFile(file3);
    
    directory.removeFile(file3.getFileName());
    
    File actuaOutput = directory.getFile("okay");
    
    assertEquals(null, actuaOutput);
  }
  
  
  @Test
  public void removeFileTest2() {
    
    File file1 = new File("Bonjour");
    file1.setContent("Petit");
    File file2 = new File("Bono");
    file2.setContent("CHICHIHUA");
    
    directory.addFile(file1);
    directory.addFile(file2);
    
    directory.removeFile("nonExistantFileName");
    
    List<File> expectedFiles = new ArrayList<File>();
    expectedFiles.add(file1);
    expectedFiles.add(file2);
    
    List<File> actualFiles = directory.getFiles();
    
    assertEquals(expectedFiles, actualFiles);
  }
  
  
  @Test
  public void removeFileTest3() {
    
    List<File> expectedFiles = directory.getFiles();
    
    directory.removeFile("nonExistantFileName");
    
    List<File> actualFiles = directory.getFiles();
    
    assertEquals(expectedFiles, actualFiles);
  }
  
  
  @Test
  public void isFileInsideByFileNameTest1() {
    
    File file1 = new File("Haimont");
    file1.setContent("PBRO");
    File file2 = new File("CHINGong");
    file2.setContent("asghagh");
    
    directory.addFile(file1);
    directory.addFile(file2);
    
    boolean actualOutput = directory.isFileInsideByFileName(
        file2.getFileName());
    
    assertEquals(true, actualOutput);
  }
  
  
  @Test
  public void isFileInsideByFileNameTest2() {
    
    File file1 = new File("agfagag");
    file1.setContent("oka");
    File file2 = new File("asgt");
    file2.setContent("Cggg");
    
    directory.addFile(file1);
    directory.addFile(file2);
    
    boolean actualOutput = directory.isFileInsideByFileName(
        "nonExistantFileName");
    
    assertEquals(false, actualOutput);
  }
  
  
  @Test
  public void isFileInsideByFileNameTest3() {
    
    boolean actualOutput = directory.isFileInsideByFileName(
        "nonExistantFileName");
    
    assertEquals(false, actualOutput);
  }
  
}

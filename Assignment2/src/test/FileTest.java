package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import data.File;

public class FileTest {

  private File file;

  @Before
  public void setUp() throws Exception
  {
    file = new File("Hai");

  }
  
  @Test
  public void getFileNameTest() {
    String actualOutput = file.getFileName();
    
    assertEquals("Hai", actualOutput);
  }
  
  
  @Test
  public void setFileNameTest() {
    
    file.setFileName("YOLO");
    
    String checkOutput = file.getFileName();
    
    assertEquals("YOLO", checkOutput);
  }
  
  @Test
  public void getContentTest() {
    String actualOutput = file.getContent();
    
    assertEquals("", actualOutput);
  }
  
  
  @Test
  public void setContentTest() {
    
    file.setContent("Good Stuff");
    
    String checkOutput = file.getContent();
    
    assertEquals("Good Stuff", checkOutput);
  }
  
}

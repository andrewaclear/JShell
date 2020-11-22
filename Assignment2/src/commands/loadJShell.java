package commands;

import data.Cache;
import data.FileSystem;
import driver.JShell;
import io.StandardOutput;
import runtime.ErrorHandler;
import java.io.*;

public class loadJShell extends Command {

  /**
   * Constructor for loadJShell class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public loadJShell() {
    this.setIdentifier("saveJShell");
    this.setDescription(
        "The file FileName is some file that is stored on the actual filesystem"
        + " of your computer. The purpose of this\r\n"
        + "command is to save the session of the JShell before the user"
        + " closes it down. ");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("Too many arguments, please enter fileName");
    this.setMissingOperand("What file name, do wish to call the save?");
  }
  
  @Override
  public boolean run(String[] tokens, JShell shell) { 
    FileSystem fSystem = shell.getfSystem();
    Cache cache = shell.getCache();
    try {
      FileInputStream file = new FileInputStream(tokens[1]);
      ObjectInputStream inStream = new ObjectInputStream(file);
      JShell.setfSystem((FileSystem) inStream.readObject());
      JShell.setCache((Cache) inStream.readObject());
     
      inStream.close();
      file.close();
      
    } catch (IOException e) {
      StandardOutput.println("Invalid filepath given");
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return true;
  }
}

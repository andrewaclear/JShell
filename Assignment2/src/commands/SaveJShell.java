package commands;

import data.Cache;
import data.FileSystem;
import driver.JShell;
import runtime.ErrorHandler;
import java.io.*;

public class SaveJShell extends Command {

  /**
   * Constructor for saveJShell class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public SaveJShell() {
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
      FileOutputStream file = new FileOutputStream(tokens[1]);
      ObjectOutputStream outStream = new ObjectOutputStream(file);
      outStream.writeObject(fSystem);
      outStream.writeObject(cache);
      outStream.close();
      file.close();
      
    } catch (IOException e) {
      ErrorHandler.badInput(this, "Invalid filepath given");
    }

    return true;
  }
}

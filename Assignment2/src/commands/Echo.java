// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package commands;

import data.*;
import driver.JShell;
import io.StandardOutput;
import runtime.ErrorHandler;


/**
 * Takes in an array of tokens from execution and executes the Echo command.
 * echo "STRING arrow OUTFILE does the following: If OUTFILE is not provided,
 * print STRING on the shell. Otherwise, put STRING into file OUTFILE. STRING is
 * a string of characters surrounded by double quotation marks. This creates a
 * new file if OUTFILE does not exists and erases the old contents if OUTFILE
 * already exists. In either case, the only thing in OUTFILE should be STRING.
 * echo "STRING" double_arrow OUTFILE: Like the previous command, but appends
 * instead of overwrites.
 */
public class Echo extends Command {

  /**
   * Constructor for Echo class. It initializes identifier, maxNumOfArguments,
   * minNumOfArguments errorTooManyArguments, missingOperand, and description
   * from its super class Commands.
   */
  public Echo() {
    this.setDescription("If OUTFILE is not provided, print STRING"
        + " on the shell. Otherwise, put \nSTRING into file OUTFILE."
        + " STRING is a string of"
        + " characters surrounded \nby double  quotation marks. This"
        + " creates a new file if OUTFILE does \nnot exists and erases"
        + " the old contents if OUTFILE already exists. \nIn either case,"
        + " the only thing in OUTFILE should be STRING.");
    this.setIdentifier("echo");
    this.setMaxNumOfArguments(4);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("too many arguments");
    this.setMissingOperand("please specify a string to print");
  }

  /**
   * Print STRING to terminal, writes STRING to a new file, overrides contents
   * of a file if file already exists and/or appends STRING to a file.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write to the
   *        file structure.
   * @param cache, stores the history and directory stack of the running
   *        terminal
   * @return returns a boolean true signal the shell to continue running
   */
  @Override
  public boolean run(String[] tokens, JShell shell) {
    // If called with just echo "STRING", prints STRING to terminal
    if (tokens.length == 2) {
      if (tokens[1].charAt(0) == '"') {
        StandardOutput.println(tokens[1].replace("\"", ""));
      } else {
        ErrorHandler.missingString(this, tokens);
      }
      // If called with echo "STRING" > file, then prints STRING to file
    } else if (tokens.length == 4 && tokens[2].equals(">")
        || tokens[2].equals(">>")) {
      if (tokens[1].charAt(0) == '"') {
        EchoToFile echoFile = new EchoToFile();
        echoFile.run(tokens, shell);
      } else {
        ErrorHandler.missingString(this, tokens);
      }
      // Else invalid combination of arguments
    } else {
      ErrorHandler.invalidComboOfParams(this, tokens);
    }

    return true;
  }
}


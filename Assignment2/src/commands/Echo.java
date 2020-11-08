package commands;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;


/**
 * Takes in an array of tokens from execution and executes the Echo command.
 * echo "STRING > OUTFILE does the following: 
 * If OUTFILE is not provided, print STRING on the shell. Otherwise, put 
 * STRING into file OUTFILE. STRING is a string of characters surrounded 
 * by double  quotation marks. This creates a new file if OUTFILE does not
 * exists and erases the old contents if OUTFILE already exists. In either
 * case, the only thing in OUTFILE should be STRING. 
 * echo "STRING" >> OUTFILE:
 * Like the previous command, but appends instead of overwrites. 
 */
public class Echo extends Command {
  
  /**
   * Constructor for Echo class. It initializes Description, Identifier,
   * MaxNumOfArguments, ErrorTooManyArguments, MissingOperand from its
   * super class Commands.
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
   * @param fSystem, an instance of FileSystem class to read and write
   * to the file structure.
   * @param cache, store the current directory stack
   * @return returns a boolean true to mark successful execution
   * @Override overrides run method from super class Command
   */
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    //If called with just echo "STRING", prints STRING to terminal
    if (tokens.length == 2) {
      if (tokens[1].charAt(0) == '"') {
        StandardOutput.println(tokens[1].replaceAll("\"", ""));
      } else {
        ErrorHandler.missingString(this, tokens);
      }
    //If called with echo "STRING" > file, then prints STRING to file
    } else if (tokens.length == 4 && tokens[2].equals(">") 
        || tokens[2].equals(">>")) {
       EchoToFile echoFile = new EchoToFile();
       echoFile.run(tokens, fSystem, cache);
    //Else invalid combination of arguments
    } else {
      ErrorHandler.invalidComboOfParams(this, tokens);
    }
    
    return true;
  }
}


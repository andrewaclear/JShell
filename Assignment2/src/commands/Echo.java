package commands;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Echo extends Command {
  
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
  
  @Override
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


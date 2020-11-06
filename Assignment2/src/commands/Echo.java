package commands;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Echo extends Command {
  
  public Echo() {
    this.setDescription("If OUTFILE is not provided, print STRING"
      + " on the shell. Otherwise, put \nSTRING into :ile OUTFILE."
      + " STRING is a string of"
      + " characters surrounded \nby double  quotation marks. This"
      + " creates a new :ile if OUTFILE does \nnot exists and erases"
      + " the old contents if OUTFILE already exists. \nIn either case,"
      + " the only thing in OUTFILE should be STRING.");
    
    this.setIdentifier("echo");
    this.setMaxNumOfArguments(4);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("Too many arguments");
    this.setMissingOperand("Please specify a string to print");
  }
  
  
  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    //If called with just echo "STRING", prints STRING to terminal
    if (tokens.length == 2) {
      if (tokens[1].charAt(0) == '"') {
        StandardOutput.println(tokens[1].replaceAll("\"", ""));
      } else {
        ErrorHandler.missingString(tokens);
      }
    //If called with echo "STRING" > file, then prints STRING to file
    } else if (tokens.length == 4 && tokens[2].equals(">")) {
      //If file exists, then override its contents
      if (fSystem.getCurrentDirectory().getDirectory()
          .getFile(tokens[3]) != null) {
        fSystem.getCurrentDirectory().getDirectory()
          .getFile(tokens[3]).setContent(tokens[1]);
      //If file doesn't exist then create new file with specified contents
      } else {
        File newFile = new File(tokens[3]);
        newFile.setContent(tokens[1].replaceAll("\"", ""));
     
        fSystem.getCurrentDirectory().getDirectory().addFile(newFile);     
      }
    //Else invalid combination of arguments
    } else {
      ErrorHandler.invalidComboOfParams(this, tokens);
    }
    
    return true;
  }
}


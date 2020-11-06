package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import data.File;
import data.FileSystem;
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
        + " the only thing in OUTFILE should be STRING. ");
    
    this.setIdentifier("echo");
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Please specify a string to print");
  }
  
  
  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    //for (String token: tokens) {
     // StandardOutput.println(token);
   // }
    if (tokens.length == 2) {
      if (tokens[1].charAt(0) == '"') {
        StandardOutput.println(tokens[1].replaceAll("\"", ""));
      } else {
        ErrorHandler.missingString(tokens);
      }
      
    } else if (tokens.length == 3) {
      if (fSystem.getCurrentDirectory().isChildInside(tokens[2])) {
        fSystem.getCurrentDirectory().getDirectory()
          .getFile(tokens[2]).setContent(tokens[1]);
      } else {
        File newFile = new File(tokens[2]);
        newFile.setContent(tokens[1].replaceAll("\"", ""));
     
        fSystem.getCurrentDirectory().getDirectory().addFile(newFile);     
      }
    }
    
    return true;
  }
}

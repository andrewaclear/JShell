package commands;

import data.Cache;
import data.FileSystem;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Concatenate extends Command {

  public Concatenate() {
    this.setIdentifier("cat");
    this.setDescription("Display the contents of FILE1 and other files"
      + " (i.e. File2 ....) concatenated in the shell.");
    
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Which files do you wish to display?");
  }
  
  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    int i = 1;
    while (i < tokens.length) {
     
      if (fSystem.getCurrentDirectory().getDirectory()
          .getFile(tokens[i]) != null) {
       
        StandardOutput.println(fSystem.getCurrentDirectory()
            .getDirectory().getFile(tokens[i]).getContent());
      
        if (i + 1 < tokens.length) {
          StandardOutput.println("\n\n\n");
        }
      } else {
        ErrorHandler.fileNotFound(tokens[i]);
      }
      i++;
    }
    return true;
  }
}

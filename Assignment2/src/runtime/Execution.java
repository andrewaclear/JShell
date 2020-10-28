package runtime;

import data.FileSystem;
import commands.*;
import runtime.*;

public class Execution {
  
  /*Takes in an array of tokens and attempts to execute a command. 
    Returns true if the command is sucessfully executed;
    else returns false. (in future returns an error msg)
  */
  public boolean execute_command(String[] tokens, FileSystem fSystem) {
    boolean run = true;
    
    switch(tokens[0]) {
      case "cd":
        //Execute the cd command here and set msg to an error if cd 
        //command runs into an error 
        break;
      case "cat":
        break;
      case "echo":
        break;
      case "exit":
        if (tokens.length == 1) run = Exit.close();
        else ErrorHandler.Exit(tokens);
        break;
      case "history":
        break;
      case "ls":
        break;
      case "mkdir":
        break;
      case "man":
        break;
      case "popd":
        break;
      case "pushd":
        break;
      case "pwd":
        break;
      default:
        //Return command not found error
    }
    
    return run;
  }
}

package runtime;

import data.FileSystem;
import commands.*;

public class Execution {
  
  /*Takes in an array of tokens and attempts to execute a command. 
    Returns true if the command is successfully executed;
    else returns false. (in future returns an error msg)
  */
  public boolean execute_command(String[] tokens, FileSystem fSystem) {
    boolean run = true;
    Command command = new Command();
    
    switch(tokens[0]) {
      case "cd":
        //Execute the cd command here and set msg to an error if cd 
        //command runs into an error 
        break;
      case "cat":
        command = new Concatenate();
        break;
      case "echo":
        command = new Echo();
        break;
      case "exit":
        command = new Exit();
        break;
      case "history":
        command = new History();
        break;
      case "ls":
        command = new List();
        break;
      case "mkdir":
        command = new MakeDirectory();
        break;
      case "man":
        command = new Manual();
        break;
      case "popd":
        command = new PopDirectory();
        break;
      case "pushd":
        command = new PushDirectory();
        break;
      case "pwd":
        command = new PrintWorkingDirectory();
        break;
      default:
        ErrorHandler.commandNotFound(tokens);
        return run;
        //Return command not found error
    }

    if (tokens.length == command.getNumOfArguments()) {
      run = command.run(tokens, fSystem);
    } else if (tokens.length > command.getNumOfArguments()) {
      ErrorHandler.tooManyArguments(command);
    } else if (tokens.length < command.getNumOfArguments()) {
      ErrorHandler.missingOperand(command);
    }
    
    return run;
  }
}

package commands;

import driver.JShell;

public class Search extends Command {
    
  public Search() {
    this.setDescription("The syntax of the search command is as follows: 
    search path ... -type [f|d] -name expression. So here are some examples of how
    the search command may be used:
    • search /users/Desktop -type f -name \"xyz\". This command will search the directory Desktop
    and search all files (indicated by type f) that have the name exactly xyz.
    • search /users/Desktop -type d -name \"abc\". This command will search the directory Desktop
    and search all directories (indicated by type d) that have the name exactly abc.
    • search /users/Desktop. This command will result in an error because it has missing arguments.
    Note: For any missing arguments or incorrect argument parameters, your program must not crash and
    result in error.");
    this.setIdentifier("search");
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(6);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Missing required arguments");
  }
  
  @Override
  public boolean run(String[] tokens, JShell shell) {
    return true;
  }
  
}

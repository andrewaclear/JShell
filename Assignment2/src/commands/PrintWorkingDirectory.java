package commands;

import data.FileSystem;

public class PrintWorkingDirectory extends Command {

  public PrintWorkingDirectory() {
    this.setIdentifier("pwd");
    
    //PrintWorkingDirectory must not have any arguments
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
    this.setDescription("Print the current working directory"
        + " (including the whole path).  ");
    this.setErrorTooManyArguments("no parameters are accepted");
    this.setMissingOperand("identifier pwd is missing");
    
  }
  
  //When run, PrintWorkingDirectory have to display the current Directory in the FileSystem
  public boolean run(String[] tokens, FileSystem system) {
    
    //Print the path of the current directory
    System.out.println(system.getCurrentDirectory().getPath());
    
    return true;
    
  }
}

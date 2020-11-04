package commands;

import data.FileSystem;

public class PrintWorkingDirectory extends Command {

  public PrintWorkingDirectory() {
    this.setIdentifier("pwd");
    
    //PrintWorkingDirectory must not have any arguments
    this.setMaxNumOfArguments(0);
    this.setMinNumOfArguments(0);
    this.setDescription("Print the current working directory"
        + " (including the whole path).  ");
  }
  
  //When run, PrintWorkingDirectory have to display the current Directory in the FileSystem
  public boolean run(String[] tokens, FileSystem system) {
    
    //Print the path of the current directory
    System.out.println(system.getCurrentDirectory().getPath());
    
    return true;
    
  }
}

package commands;

import data.FileSystem;

public class PrintWorkingDirectory extends Command {

  public PrintWorkingDirectory() {
    this.setIdentifier("pwd");
    
    //PrintWorkingDirectory must not have any arguments
    this.setMaxNumOfArguments(0);
    this.setMinNumOfArguments(0);
  }
  
  //When run, PrintWorkingDirectory have to display the current Directory in the FileSystem
  public void run(FileSystem system) {
    
    //Print the path of the current directory
    System.out.println(system.getCurrentDirectory().getPath());
    
  }
}

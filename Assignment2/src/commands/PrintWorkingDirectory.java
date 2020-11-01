package commands;

import data.FileSystem;

public class PrintWorkingDirectory extends Command {

  public PrintWorkingDirectory() {
    this.setIdentifier("pwd");
    this.setMaxNumOfArguments(0);
    this.setMinNumOfArguments(0);
  }
  
  public void run(FileSystem system) {
    
    //print the path of the current directory
    System.out.println(system.getCurrentDirectory().getPath());
    
  }
}

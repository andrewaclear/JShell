package commands;

import data.FileSystem;

public class Exit extends Command {

  public Exit() {
    // String tooManyArguments = "exit: doesn't take any arguments";

    this.setIdentifier("exit");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("doesn't take any arguments");
    this.setMissingOperand("");
    this.setDescription("Quit the program ");
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    return false;
  }

}

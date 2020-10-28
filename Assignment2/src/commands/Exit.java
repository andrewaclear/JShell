package commands;

import data.FileSystem;

public class Exit extends Command {

  public Exit() {
    // String desc = "Quit the program ";
    // String iden = "exit";
    // String tooManyArguments = "exit: doesn't take any arguments";

    this.setDescription("Quit the program");
    this.setIdentifier("exit");
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("doesn't take any arguments");
    this.setMissingOperand("");
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    return false;
  }

}

package commands;

public class Exit extends Command {

  public Exit() {
    // String desc = "Quit the program ";
    // String iden = "exit";
    // String tooManyArguments = "exit: doesn't take any arguments";

    this.setDescription("Quit the program");
    this.setIdentifier("exit");
    this.setNumOfArguments(1);
    this.setErrorTooManyArguments("doesn't take any arguments");
  }

  public boolean run(String[] tokens) {
    return false;
  }

}

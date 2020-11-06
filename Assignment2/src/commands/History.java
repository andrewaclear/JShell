package commands;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;

public class History extends Command {

  public History() {
    this.setIdentifier("history");
    this.setDescription("This command will print out recent commands,"
                      + " one command per line. ");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("too many arguments");
    this.setMissingOperand("");
  }

  private int tryNonNegInteger(String token) {
    try {
      int truncate = Integer.parseInt(token);
      if (truncate>=0) return truncate;
      else return -1;
    } catch(Exception e) {
      return -1;
    }
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    int start;
    int n=cache.getHistorySize();

    if (tokens.length == 1) start = 1;
    else {
      int truncate = tryNonNegInteger(tokens[1]);
      if (truncate >= n) start = 0;
      else if (truncate >= 0) start = n-truncate;
      else {
        ErrorHandler.badInput(this, "operand must be a non-negative integer");
        return true;
      }
    }

    for (int i = start; i<n; i++) {
      StandardOutput.println(String.valueOf(i)+". "+cache.getHistory(i));
    }    

    return true;
  }
}
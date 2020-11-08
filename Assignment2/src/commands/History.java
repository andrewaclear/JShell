// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
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
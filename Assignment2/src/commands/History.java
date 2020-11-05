package commands;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import data.*;
import io.StandardOutput;


public class History extends Command {

  private ArrayList<String> data = new ArrayList<String>();

  public History() {
    this.setIdentifier("history");
    this.setDescription("This command will print out recent commands,"
                      + " one command per line. ");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("Too many arguments.");
    this.setMissingOperand("");
  }

  public void addLine(String line) {
    data.add(line);
    // StandardOutput.println(data.get(0));
  }

  public void printHistory(int last) {
    int n = data.size();
    for (int i = 1; (n-i >= 0 && i<last); i++) {
      StandardOutput.println(String.valueOf(i)+". "+data.get(n-i));
    }
    // StandardOutput.println(data.get(last));
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    int last;
    data.trimToSize();

    if (tokens.length == 1) last = data.size();
    else last = Integer.valueOf(tokens[1]);

    printHistory(last);
    // System.out.println(String.valueOf(last));
    
    return true;
  }
}
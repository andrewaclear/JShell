package commands;

import java.util.HashMap;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Manual extends Command {
  //HashMap that stores entries <Command Identifier, Command Description>
  private HashMap<String, String> descriptions = new HashMap<String, String>();
  
  public Manual() {
    this.setDescription("Print documentation for CMD (s)");
    
    //Adds entries to hashmap mapping cmd identifier -> cmd description
    descriptions.put("man", this.getDescription());
    descriptions.put("cd", new ChangeDirectory().getDescription());
    descriptions.put("cat", new Concatenate().getDescription());
    descriptions.put("echo", new Echo().getDescription());
    descriptions.put("exit", new Exit().getDescription());
    descriptions.put("history", new History().getDescription());
    descriptions.put("ls", new ListContents().getDescription());
    descriptions.put("mkdir", new MakeDirectory().getDescription());
    descriptions.put("popd", new PopDirectory().getDescription());
    descriptions.put("pwd", new PrintWorkingDirectory().getDescription());
    descriptions.put("pushd", new PushDirectory().getDescription());
 
    this.setIdentifier("man");
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("Too many arguments");
    this.setMissingOperand("What manual page do you want?");
    
  }
  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    int i = 1;
    while (i < tokens.length) {
      //If command is recognized, then print manual for it
      if (descriptions.containsKey(tokens[i])) {
        StandardOutput.println("Documentation for: " + tokens[i] + "\n");
        StandardOutput.println(descriptions.get(tokens[i]));
        if (i + 1 < tokens.length) {
          StandardOutput.print("\n\n\n");
        }
      //Else, then give command not found error
      } else {
        ErrorHandler.commandNotFoundManual(tokens[i]);
      }
      i++;
    }
    return true;
  }
}

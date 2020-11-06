package runtime;

import data.*;
import io.StandardOutput;
import commands.*;
import java.util.HashMap;

public class Execution {
  private HashMap<String, String> 
    commandHashMap = new HashMap<String, String>();
  
  /*Takes in an array of tokens and attempts to execute a command. 
    Returns true if the command is successfully executed;
    else returns false. (in future returns an error msg)
  */
  public Execution () {
    initHashMap(commandHashMap);
  }
  
  public boolean executeCommand(String[] tokens, FileSystem fSystem, 
                                Cache cache) {
    boolean run = true;
    
    try {
      if (commandHashMap.containsKey(tokens[0])) {
        String commandName = commandHashMap.get(tokens[0]);
        Command command = (Command) Class.forName(commandName).
                          getDeclaredConstructor().newInstance();
      
        if ((command.getMaxNumOfArguments() < 0 ||
             tokens.length <= command.getMaxNumOfArguments()) &&
            tokens.length >= command.getMinNumOfArguments()) {
          run = command.run(tokens, fSystem, cache);
        } else if (tokens.length > command.getMaxNumOfArguments()) {
          ErrorHandler.tooManyArguments(command);
        } else if (tokens.length < command.getMinNumOfArguments()) {
          ErrorHandler.missingOperand(command);
        }
      } else if (tokens[1].equals("Failed Parsing")) {
        ErrorHandler.commandNotFound(tokens);
      } else {
        ErrorHandler.commandNotFound(tokens);
      }
    } catch (Exception e) {
      //StandardOutput.println("Invalid command and parameters");
    }
    
    return run;
  }
  
  private static void initHashMap(HashMap<String, String> commandHashMap)  {
    commandHashMap.put("man", "commands.Manual");
    commandHashMap.put("cd", "commands.ChangeDirectory");
    commandHashMap.put("cat", "commands.Concatenate");
    commandHashMap.put("echo", "commands.Echo");
    commandHashMap.put("exit", "commands.Exit");
    commandHashMap.put("history", "commands.History");
    commandHashMap.put("ls", "commands.ListContents");
    commandHashMap.put("mkdir", "commands.MakeDirectory");
    commandHashMap.put("popd", "commands.PopDirectory");
    commandHashMap.put("pwd", "commands.PrintWorkingDirectory");
    commandHashMap.put("pushd", "commands.PushDirectory");
  }
}



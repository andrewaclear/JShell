package runtime;

import data.FileSystem;
import io.StandardOutput;
import java.util.HashMap;
import commands.*;

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
  
  public boolean executeCommand(String[] tokens, FileSystem fSystem) {
    boolean run = true;
    
    if (commandHashMap.containsKey(tokens[0])) {
      String commandName = commandHashMap.get(tokens[0]);
      
      try {
      Command command = (Command) Class.forName(commandName).
          getDeclaredConstructor().newInstance();
      
      if (tokens.length <= command.getMaxNumOfArguments() && 
          tokens.length >= command.getMinNumOfArguments()) {
        run = command.run(tokens, fSystem);
      } else if (tokens.length > command.getMaxNumOfArguments()) {
        ErrorHandler.tooManyArguments(command);
      } else if (tokens.length < command.getMinNumOfArguments()) {
        ErrorHandler.missingOperand(command);
      }
      
      } catch (Exception e) {
        
      }

    } else {
      ErrorHandler.commandNotFound(tokens);
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



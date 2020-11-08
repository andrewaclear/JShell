package runtime;

import data.*;
import io.StandardOutput;
import commands.*;
import java.util.HashMap;

/**
 * Takes in an array of tokens from Parser and executes the
 * the specified command from tokens. 
 */
public class Execution {
  /**
   * HashMap that stores entries <String commandId, String commandClass>
   */
  private HashMap<String, String> 
    commandHashMap = new HashMap<String, String>();
  
  /**
   * Constructor for Execution class. It initializes the commandHashMap
   * HashMap.
   */
  public Execution () {
    initHashMap(commandHashMap);
  }
  
  /**
   * Takes in an array of tokens and attempts to execute a command. 
   * Returns true when exit command is executed. Verifies that  the number of 
   * command arguments are within designated max and min lengths. 
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write
   * to the file structure.
   * @param cache, store the current directory stack
   * @return returns a boolean true when exit is entered into terminal
   */
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
     // StandardOutput.println(e.getMessage());
    }
    
    return run;
  }
  
  /**
   * Initializes the commandHashMap with command ids and command classes
   * 
   * @param commandHashMap, a hashmap mapping commandId to command class
   * @return returns void
   */
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



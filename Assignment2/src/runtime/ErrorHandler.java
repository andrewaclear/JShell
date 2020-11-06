package runtime;

import io.StandardOutput;
import commands.Command;

// import java.lang.reflect.Array;
// import java.util.Arrays;

public class ErrorHandler {

    public static void tooManyArguments(Command command) {
        StandardOutput.println(command.getIdentifier()+": "
        +command.getErrorTooManyArguments());
    }

    public static void commandNotFound(String[] tokens) {
        StandardOutput.println(tokens[0]+": command not found");
    }
    
    public static void commandNotFoundManual(String commandNotFound) {
      StandardOutput.println(commandNotFound + ": command not found");
    }

    public static void missingOperand(Command command) {
        StandardOutput.println(command.getIdentifier()+": "
          +command.getErrorMissingOperand());
    }
    
    public static void missingString(Command command, String[] tokens) {
      StandardOutput.println(command.getIdentifier()+": "+tokens[1] 
        +": no string found, format string as \"string\"");
    }
    
    public static void illegalString() {
      StandardOutput.println("Illegal character in string");
    }

    public static void badInput(Command command, String message) {
      StandardOutput.println(command.getIdentifier()+": "+message);
    }
    
    public static void fileNotFound(String file) {
      StandardOutput.println(file + ": file not found");
    }
    
    public static void invalidComboOfParams(Command command, String[] tokens) {
      StandardOutput.print(command.getIdentifier() + ": ");
      for (int i = 1; i < tokens.length; i++) {
        StandardOutput.print(tokens[i] + " ");
      }
      StandardOutput.print(": invalid combination of arguments\n");
    }
}

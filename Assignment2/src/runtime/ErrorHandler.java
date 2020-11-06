package runtime;

import io.StandardOutput;
import commands.Command;

// import java.lang.reflect.Array;
// import java.util.Arrays;

public class ErrorHandler {

    public static void tooManyArguments(Command command) {
        // StandardOutput.println(Exit.badInput+Arrays.toString(Arrays.copyOfRange(tokens, 1, tokens.length)));
        StandardOutput.println(command.getIdentifier()+": "+command.getErrorTooManyArguments());
    }

    public static void commandNotFound(String[] tokens) {
        StandardOutput.println(tokens[0]+": command not found");
    }
    
    public static void commandNotFoundManual(String commandNotFound) {
      StandardOutput.println(commandNotFound + ": command not found");
    }

    public static void missingOperand(Command command) {
        StandardOutput.println(command.getIdentifier()+": "+command.getErrorMissingOperand());
    }
    
    public static void missingString(String[] tokens) {
      StandardOutput.println(tokens[1] + ": no string found, format  string as \"string\"");
    }
    
    public static void illegalString() {
      StandardOutput.println("Illegal character in string");
    }
    
    public static void fileNotFound(String file) {
      StandardOutput.println(file + ": file not found");
    }
}

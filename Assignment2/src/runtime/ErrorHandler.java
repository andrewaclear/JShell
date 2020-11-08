package runtime;

import io.StandardOutput;
import commands.Command;
import data.FileSystem;
import data.FileSystemNode;

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
    
    public static void invalidPath(Command command) {
      StandardOutput.println(command.getIdentifier() +
          ": invalid path");
    }
    
    public static void invalidName(Command command, String[] tokens) {
      StandardOutput.println(command.getIdentifier() +
          ": " + tokens[3] + ": invalid file and directory name");
    }
    
    public static void childAlreadyExistant(String directoryName, 
        FileSystemNode node) {
      StandardOutput.println("The directory " + directoryName 
          + " already exists at " + node.getPath());
    }
    
    public static void inappropriatePath(String givenPath) {
      StandardOutput.println("The given path : " + givenPath 
          + " constains illicit characters");
    }
    
    public static void invalidPath(String givenPath) {
      StandardOutput.println("The given : " + givenPath 
          + " is not a valid path");
    }
    
}

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
package runtime;

import io.StandardOutput;
import commands.Command;
import commands.Manual;
import data.FileSystemNode;


/**
 * Defines error messages for the various commands and prints the error messages
 * to the terminal.
 */
public class ErrorHandler {

  /**
   * Prints tooManyArguments error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static void tooManyArguments(Command command) {
    StandardOutput.println(
        command.getIdentifier() + ": " + command.getErrorTooManyArguments());
  }

  /**
   * Prints commandNotFound error message
   * 
   * @param tokens, array of string tokens holding command arguments
   */
  public static void commandNotFound(String[] tokens) {
    StandardOutput.println(tokens[0] + ": Command not found");
  }

  /**
   * Prints commandNotFoundManual error message
   * 
   * @param commandNotFound, name of command for which error occured
   */
  public static void commandNotFoundManual(Manual man, String commandNotFound) {
    StandardOutput.println(man.getIdentifier() + ": No manual entry for: " 
      + commandNotFound);
  }

  /**
   * Prints missingOperand error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static void missingOperand(Command command) {
    StandardOutput.println(
        command.getIdentifier() + ": " + command.getErrorMissingOperand());
  }

  /**
   * Prints missingString error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static void missingString(Command command, String[] tokens) {
    StandardOutput.println(command.getIdentifier() + ": " + tokens[1]
        + ": No string found, format string as \"string\"");
  }

  /**
   * Prints illegalString error message
   */
  public static void illegalString() {
    StandardOutput.println("Illegal character in string");
  }

  /**
   * Prints badInput error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param message, error message to be printed
   */
  public static void badInput(Command command, String message) {
    StandardOutput.println(command.getIdentifier() + ": " + message);
  }
  
  /**
   * Prints fileAlreadyExist error message
   * 
   * @param file, name of file not found
   */
  public static void fileAlreadyExist(Command command, String file) {
    StandardOutput.println(command.getIdentifier() +": " + file 
        + ": File with given name already exists");
  }

  /**
   * Prints invalidComboOfParams error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static void invalidComboOfParams(Command command, String[] tokens) {
    StandardOutput.print(command.getIdentifier() + ": ");
    for (int i = 1; i < tokens.length; i++) {
      StandardOutput.print(tokens[i] + " ");
    }
    StandardOutput.print(": Invalid combination of arguments\n");
  }

  /**
   * Prints invalidPath error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static void invalidPath(Command command, String path) {
    StandardOutput.println(command.getIdentifier() +": \"" + path 
        + "\": No such file or directory");
  }

  /**
   * Prints invalidName error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static void invalidName(Command command, String[] tokens) {
    StandardOutput.println(command.getIdentifier() + ": \"" + tokens[3]
        + "\": Invalid file and/or directory name");
  }

  /**
   * Prints childAlreadyExistant error message
   * 
   * @param directoryName, name of directory
   * @param node, an instance of FileSystemNode that holds the position of child
   *        node in FileSystem
   */
  public static void childAlreadyExistant(String directoryName,
      FileSystemNode node) {
    StandardOutput.println("The directory " + directoryName
        + " already exists at " + node.getPath());
  }

  /**
   * Prints inappropriatePath error message
   * 
   * @param givenPath, the invalid path
   */
  public static void inappropriatePath(String givenPath) {
    StandardOutput.println(
        "The given path : " + givenPath + " contains illicit characters");
  }
  
  public static void removeDirectoryError(String givenPath) {
    StandardOutput.println("The given path: " + givenPath 
        + " cannot be removed because it is a "
        + "subpath to the current directory");
  }

  
  public static void moveDirectoryError(String givenPath) {
    StandardOutput.println("The given path: " + givenPath 
        + " cannot be used to move because it is inside "
        + "the target path");
  }
  
  
  /**
   * Prints invalidUrl error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static void invalidUrl(Command command, String url) {
    StandardOutput.println(command.getIdentifier() +": \"" + url 
        + "\": Invalid valid URL or file found");
  }

}

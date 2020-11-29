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
  public static String tooManyArguments(Command command) {
    return command.getIdentifier() + ": " + command.getErrorTooManyArguments();
  }

  /**
   * Prints commandNotFound error message
   * 
   * @param tokens, array of string tokens holding command arguments
   */
  public static String commandNotFound(String[] tokens) {
    return tokens[0] + ": Command not found";
  }

  /**
   * Prints commandNotFoundManual error message
   * 
   * @param commandNotFound, name of command for which error occured
   */
  public static String commandNotFoundManual(Manual man,
      String commandNotFound) {
    return man.getIdentifier() + ": No manual entry for: " + commandNotFound;
  }

  /**
   * Prints missingOperand error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static String missingOperand(Command command) {
    return command.getIdentifier() + ": " + command.getErrorMissingOperand();
  }

  /**
   * Prints missingString error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static String missingString(Command command, String[] tokens) {
    return command.getIdentifier() + ": " + tokens[1]
        + ": No string found, format string as \"string\"";
  }

  /**
   * Prints illegalString error message
   */
  public static String illegalString() {
    return "parser: Illegal character in string";
  }

  /**
   * Prints badInput error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param message, error message to be printed
   */
  public static String badInput(Command command, String message) {
    return command.getIdentifier() + ": " + message;
  }

  /**
   * Prints fileAlreadyExist error message
   * 
   * @param file, name of file not found
   */
  public static String fileAlreadyExist(Command command, String file) {
    return command.getIdentifier() + ": " + file
        + ": File with given name already exists";
  }

  /**
   * Prints invalidComboOfParams error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static String invalidComboOfParams(Command command, String[] tokens) {
    String output = "";
    output += command.getIdentifier() + ": ";
    for (int i = 1; i < tokens.length; i++) {
      output += tokens[i] + " ";
    }
    output += ": Invalid combination of arguments";
    return output;
  }

  /**
   * Prints invalidPath error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static String invalidPath(Command command, String path) {
    return command.getIdentifier() + ": \"" + path
        + "\": No such file or directory";
  }

  /**
   * Prints invalidName error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   */
  public static String invalidName(Command command, String token) {
    return command.getIdentifier() + ": \"" + token
        + "\": Invalid file and/or directory name";
  }

  /**
   * Prints childAlreadyExistant error message
   * 
   * @param directoryName, name of directory
   * @param node, an instance of FileSystemNode that holds the position of child
   *        node in FileSystem
   */
  public static String childAlreadyExistant(String directoryName,
      FileSystemNode node) {
    return "The directory " + directoryName + " already exists at "
        + node.getPath();
  }

  /**
   * Prints inappropriatePath error message
   * 
   * @param givenPath, the invalid path
   */
  public static String inappropriatePath(Command command, String givenPath) {
    return command.getIdentifier() + ": " + givenPath 
        + " contains illicit characters";
  }

  public static String removeDirectoryError(String givenPath) {
    return "The given path: " + givenPath
        + " cannot be removed because it is a "
        + "subpath to the current directory";
  }

  /**
   *subFileSystemNodeError returns the error of targetPath being inside the
   *givenPath
   * 
   * @param command, an instance of Command class or its subclasses
   * @param givenPath, a FileSystemNode path
   * @param targetPath, a FileSystemNode path
   */
  public static String subFileSystemNodeError(Command command, String givenPath, 
      String targetPath) {
    return  command.getIdentifier() + ": " + targetPath+ " is inside " 
      + givenPath;
  }

  /**
   * Prints invalidUrl error message
   * 
   * @param command, an instance of Command class or its subclasses
   */
  public static String invalidUrl(Command command, String url) {
    return command.getIdentifier() + ": \"" + url
        + "\": Invalid valid URL or file found";
  }
  
}

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
import commands.ChangeDirectory;
import commands.Command;
import commands.Copy;
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
   * @return returns the error
   */
  public static String tooManyArguments(Command command) {
    return command.getIdentifier() + ": " + command.getErrorTooManyArguments();
  }

  /**
   * Prints commandNotFound error message
   * 
   * @param tokens, array of string tokens holding command arguments
   * @return returns the error
   */
  public static String commandNotFound(String[] tokens) {
    return tokens[0] + ": Command not found";
  }

  /**
   * Prints commandNotFoundManual error message
   * 
   * @param commandNotFound, name of command for which error occurred
   * @return returns the error
   */
  public static String commandNotFoundManual(Manual man,
      String commandNotFound) {
    return man.getIdentifier() + ": No manual entry for: " + commandNotFound;
  }

  /**
   * Prints missingOperand error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @return returns the error
   */
  public static String missingOperand(Command command) {
    return command.getIdentifier() + ": " + command.getErrorMissingOperand();
  }

  /**
   * Prints missingString error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param tokens, array of string tokens holding command arguments
   * @return returns the error
   */
  public static String missingString(Command command, String[] tokens) {
    return command.getIdentifier() + ": " + tokens[1]
        + ": No string found, format string as \"string\"";
  }

  /**
   * Prints illegalString error message
   * 
   * @return returns the error
   */
  public static String illegalString() {
    return "parser: Illegal character in string";
  }

  /**
   * Prints badInput error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @param message, error message to be printed
   * @return returns the error
   */
  public static String badInput(Command command, String message) {
    return command.getIdentifier() + ": " + message;
  }

  /**
   * Prints fileAlreadyExist error message
   * 
   * @param file, name of file not found
   * @return returns the error
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
   * @return returns the error
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
   * @return returns the error
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
   * @return returns the error
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
   * @return returns the error
   */
  public static String childAlreadyExistant(String directoryName,
      FileSystemNode node) {
    return "The file/directory " + directoryName + " already exists at "
        + node.getPath();
  }

  /**
   * Prints inappropriatePath error message
   * 
   * @param givenPath, the invalid path
   * @return returns the error
   */
  public static String inappropriatePath(Command command, String givenPath) {
    return command.getIdentifier() + ": " + givenPath
        + " contains illicit characters";
  }


  /**
   * RemoveDirectoryError gives the String error of trying to remove a Directory
   * that is a subpath to the current directory
   * 
   * @param givenPath, the invalid path
   * @return String error of trying to remove a Directory that is a subpath to
   *         the current directory
   */
  public static String removeDirectoryError(String givenPath) {
    return "The given path: " + givenPath
        + " cannot be removed because it is a "
        + "subpath to the current directory";
  }

  /**
   * subFileSystemNodeError returns the error of targetPath being inside the
   * givenPath
   * 
   * @param command, an instance of Command class or its subclasses
   * @param givenPath, a FileSystemNode path
   * @param targetPath, a FileSystemNode path
   * @return returns the error
   */
  public static String subFileSystemNodeError(Command command, String givenPath,
      String targetPath) {
    return command.getIdentifier() + ": " + targetPath + " is inside "
        + givenPath;
  }

  /**
   * Prints invalidUrl error message
   * 
   * @param command, an instance of Command class or its subclasses
   * @return returns the error
   */
  public static String invalidUrl(Command command, String url) {
    return command.getIdentifier() + ": \"" + url
        + "\": Invalid valid URL or file found";
  }

  /**
   * moveDirectoryIntoFileError returns the error of givenPath being a path to a
   * FileSystemNode and targetPath being a File path so you cannot move the
   * directory
   * 
   * @param command, an instance of Command class or its subclasses
   * @param givenPath, a FileSystemNode path
   * @param targetPath, a File path
   * @return returns the error
   */
  public static String moveDirectoryIntoFileError(Command command,
      String givenPath, String targetPath) {
    return command.getIdentifier() + ": cannot move directory at " + givenPath
        + " because " + targetPath + " refers to a file";
  }


  /**
   * changeDirectoryIntoFileError returns the error of targetPath refering to a
   * File so you cannot change directory
   * 
   * @param command, an instance of Command class or its subclasses
   * @param targetPath, a File path
   * @return returns the error
   */
  public static String changeDirectoryIntoFileError(Command command,
      String targetPath) {
    return command.getIdentifier() + ": cannot change directory to file "
        + targetPath;
  }

  /**
   * copyDirectoryIntoFileError returns an error for invalid copy direcoty
   * 
   * @param command instance of command
   * @param givenPath the file path
   * @param targetPath the target path
   * @return returns the error
   */
  public static String copyDirectoryIntoFileError(Command command,
      String givenPath, String targetPath) {
    return command.getIdentifier() + ": cannot copy directory " + givenPath
        + " to a file " + targetPath;
  }

}

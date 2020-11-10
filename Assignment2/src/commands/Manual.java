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
package commands;

import java.util.HashMap;

import data.*;
import io.StandardOutput;
import runtime.ErrorHandler;


/**
 * Takes in an array of tokens from execution and executes the Manual command.
 * Manual prints documentation for CMD (s)
 */
public class Manual extends Command {
  /**
   * HashMap that stores entries <Command Identifier, Command Description>
   */
  private HashMap<String, String> descriptions = new HashMap<String, String>();
  
  /**
   * Constructor for Manual class. It initializes the description HashMap and
   * Description, Identifier, MaxNumOfArguments, ErrorTooManyArguments,
   * MissingOperand from its super class Commands.
   */
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
    this.setErrorTooManyArguments("too many arguments");
    this.setMissingOperand("What manual page do you want?");
    
  }
  
  /**
   * Prints out documentation for a specified command from tokens
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write
   * to the file structure.
   * @param cache, store the current directory stack
   * @return returns a boolean true to mark successful execution
   * @Override overrides run method for super class Command
   */
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

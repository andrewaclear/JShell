package commands;

import data.Cache;
import data.File;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Tree extends Command {

  /**
   * Constructor for Tree class. It initializes identifier, maxNumOfArguments,
   * minNumOfArguments, errorTooManyArguments, missingOperand, and description
   * from its super class Commands.
   */
  public Tree() {
    this.setIdentifier("tree");

    // Tree must only have one token
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(1);

    // Error Handling
    this.setErrorTooManyArguments("at most two parameters are accepted");
    this.setMissingOperand("identifier tree is missing");

    // Description
    this.setDescription(
        "Displays the whole fileSystem in a tree structure" + " drawing");
  }

  /**
   * The run method of Tree displays the fSystem as a tree structure of
   * subdirectories and files
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param JShell contains the FileSystem and cache
   * @return returns a boolean true signal the shell to continue running
   */
  @Override
  public Command run(String[] tokens, JShell shell) {
    FileSystem fSystem = shell.getfSystem();
    int level = 0;
    String output = recursiveTreeDisplay("", fSystem.getRoot(), level);

    this.setOutput(output.substring(0, output.length() - 1));

    return this;

  }


  private String recursiveTreeDisplay(String output,
      FileSystemNode fileSystemNode, int level) {

    String indentationUnit = "  ";

    output += indentationUnit.repeat(level)
        + fileSystemNode.getDirectory().getDirectoryName() + "\n";

    int nextLevel = level + 1;

    for (File file : fileSystemNode.getDirectory().getFiles()) {
      output += indentationUnit.repeat(nextLevel) + file.getFileName() + "\n";
    }

    for (FileSystemNode child : fileSystemNode.getChildren()) {
      output = recursiveTreeDisplay(output, child, nextLevel);
    }

    return output;
  }
}

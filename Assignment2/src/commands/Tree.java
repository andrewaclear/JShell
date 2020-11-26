package commands;

import data.Cache;
import data.File;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import io.StandardOutput;

public class Tree extends Command {
  
  /**
   * Constructor for Tree class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments, errorTooManyArguments, 
   * missingOperand, and description from its super class Commands.
   */
  public Tree() {
    this.setIdentifier("tree");

    // Tree must only have one token
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(1);

    // Error Handling
    this.setErrorTooManyArguments("No parameter is accepted");
    this.setMissingOperand("No parameter is accepted");

    // Description
    this.setDescription("Displays the whole fileSystem in a tree structure"
        + " drawing");
  }
  
  /**
   * The run method of Tree displays the fSystem as a tree structure of 
   * subdirectories and files
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param JShell contains the FileSystem and cache
   * @return returns a boolean true signal the shell to continue running
   */
  public boolean run(String[] tokens, JShell shell) {
    FileSystem fSystem = shell.getfSystem();
    int level = 0;
    String output = recursiveTreeDisplay("", fSystem.getRoot(), level);
    
    StandardOutput.println(tokens, output.substring(0,output.length()-1), 
    shell, this);
    
    return true;
    
  }


  private String recursiveTreeDisplay(String output,
    FileSystemNode fileSystemNode, int level) {
    
    String indentationUnit = "  ";
    
    output += indentationUnit.repeat(level) + 
              fileSystemNode.getDirectory().getDirectoryName() + "\n";
    
    int nextLevel = level + 1;
    
    for (File file : fileSystemNode.getDirectory().getFiles()) {
      output += indentationUnit.repeat(nextLevel) + file.getFileName() + "\n";
    }
    
    for (FileSystemNode child : fileSystemNode.getChildren()) {
      
      if (child.getChildren().size() != 0) {
        output += recursiveTreeDisplay("", child, nextLevel);
      } else {
        output += indentationUnit.repeat(nextLevel) + 
                  child.getDirectory().getDirectoryName() + "\n";
      }
    }

    return output;
  }
}

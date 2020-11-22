package commands;

import data.Cache;
import data.File;
import data.FileSystem;
import data.FileSystemNode;
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
    this.setMaxNumOfArguments(1);
    this.setMinNumOfArguments(1);

    // Error Handling
    this.setErrorTooManyArguments("no parameter is accepted");
    this.setMissingOperand("no parameter is accepted");

    // Description
    this.setDescription("Displays the whole fileSystem in a tree structure"
        + " drawing");
  }
  
  /**
   * The run method of Tree displays the fSystem as a tree structure of 
   * subdirectories and files
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write to the
   *        file structure.
   * @param cache, stores the history and directory stack of the running
   *        terminal
   * @return returns a boolean true signal the shell to continue running
   */
  public boolean run(String[] tokens, JShell shell) {
    
    int level = 0;
    
    recursiveTreeDisplay(fSystem.getRoot(), level);
    
    return true;
    
  }


  private void recursiveTreeDisplay(FileSystemNode fileSystemNode, int level) {
    
    String indentationUnit = "  ";
    
    StandardOutput.println(indentationUnit.repeat(level) + 
        fileSystemNode.getDirectory().getDirectoryName());
    
    int nextLevel = level + 1;
    
    for (File file : fileSystemNode.getDirectory().getFiles()) {
      StandardOutput.println(indentationUnit.repeat(nextLevel)  
          + file.getFileName());
    }
    
    for (FileSystemNode child : fileSystemNode.getChildren()) {
      
      if (child.getChildren().size() != 0) {
        recursiveTreeDisplay(child, nextLevel);
      } else {
        StandardOutput.println(indentationUnit.repeat(nextLevel) 
            + child.getDirectory().getDirectoryName());
      }
    }
  }
}

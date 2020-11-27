package commands;

import data.Cache;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import runtime.ErrorHandler;

public class Remove extends Command {
  
  /**
  * Constructor for Remove class. It initializes identifier,
  * maxNumOfArguments, minNumOfArguments, errorTooManyArguments, 
  * missingOperand, and description from its super class Commands.
  */
  public Remove() {
    this.setIdentifier("rm");
  
    // Remove must only have two tokens
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
    
    // Error Handling
    this.setErrorTooManyArguments("Only one parameter is accepted");
    this.setMissingOperand("Only one parameter is accepted");
    
    // Description
    this.setDescription("Removes the directory from the fileSystem "
        + "including the children of the directory as well as the subpaths "
        + "in the stack");
  }
  
  
  /**
   * The run method of Remove removes the directory that the tokens[1] refers
   * to and deletes all the paths in the stack that depended on tokens[1] 
   * if tokens[1] is a valid/proper path, otherwise, it displays an error 
   * message and return true.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param JShell contains the FileSystem and cache
   * @return returns a boolean true signal the shell to continue running
   */
  public Command run(String[] tokens, JShell shell) {
    FileSystem fSystem = shell.getfSystem();
    Cache cache = shell.getCache();
    FileSystemNode beforeNode = fSystem.getSemiFileSystemNode(tokens[1]);
    
    if (beforeNode != null) {

        if (!fSystem.getCurrentDirectory().getPath().startsWith(tokens[1])) {
          if (beforeNode.isChildInside(fSystem.getPathLastEntry(tokens[1]))) {
            cache.removeDirectory(fSystem.getFileSystemNode(
                tokens[1]).getPath());
            
            beforeNode.removeChild(fSystem.getPathLastEntry(tokens[1]));
          } else if (beforeNode.getDirectory().isFileInsideByFileName(tokens[1])) {
            beforeNode.getDirectory().removeFile(tokens[1]);
          } else {
            //ADD ERROR
          }
          
        } else 
          this.setErrors(ErrorHandler.removeDirectoryError(tokens[1]));
        
      
    }
    return this;
  }
    
}

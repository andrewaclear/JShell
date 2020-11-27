package commands;

import data.File;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import runtime.ErrorHandler;

public class Move extends Command {
  
  /**
   * Constructor for Move class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public Move() {
    this.setIdentifier("mv");

    // MakeDirectory must have three tokens
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(3);

    // Error Handling
    this.setErrorTooManyArguments("Only two parameters are accepted");
    this.setMissingOperand("Only two parameters are accepted");

    // Description
    this.setDescription("This command moves a directory or file into the"
        + " desired directory etiher new or already existing one");
  }
  
  /**
   * The run method of MakeDirectory makes two directories in the given path
   * tokens[1] and tokens[2] if both paths are valid/appropriate in fileSystem,
   * or makes a directory in the path tokens[1] if tokens[1] is a
   * valid/appropriate path in fileSystem but tokens[2] is not, or makes no
   * directories at all if tokens[1] is not a valid/appropriate path in fSystem.
   * In any case, returns true after being done.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param JShell contains the FileSystem and cache
   * @return returns a boolean true to mark successful execution
   */
  @Override
  public Command run(String[] tokens, JShell shell) {
    FileSystem fSystem = shell.getfSystem();
    
    FileSystemNode givenNode = fSystem.getSemiFileSystemNode(tokens[1]);
    
    if (tokens[2].startsWith(tokens[1])) 
      this.setErrors(ErrorHandler.moveDirectoryError(tokens[2]));
    else if (givenNode != null) {
        if (givenNode.isChildInside(fSystem.getPathLastEntry(tokens[1]))) {
          moveDirectoryInFileSystem(tokens[1], tokens[2], shell);
        } else if (givenNode.isFileInsideByFileName(fSystem.getPathLastEntry(
            tokens[1]))){
          moveFileInFileSystem(tokens[1], tokens[2], shell);
            }
          } else 
            this.setErrors(ErrorHandler.invalidPath(this, tokens[1]));

    return this;
  }
  
  public void moveDirectoryInFileSystem(String givenPath, 
      String targetPath, JShell shell) {
    
    FileSystem fSystem = shell.getfSystem();
    
    FileSystemNode targetNode = fSystem.forcedGetFileSystemNode(targetPath);
    
    FileSystemNode givenNode = fSystem.getFileSystemNode(givenPath);
    
    if (targetNode != null) {
      if (!targetNode.isChildInside(
          givenNode.getDirectory().getDirectoryName())) {
        targetNode.addChild(givenNode);
        givenNode.setParent(targetNode);
        Remove remove = new Remove();
        String[] removeTokens = {"rm", givenPath};
        remove.run(removeTokens, shell);
      } else 
        this.setErrors(ErrorHandler.invalidPath(this, targetPath));
      
    } else 
      this.setErrors(ErrorHandler.invalidPath(this, targetPath));
    
  }
  
  
  public void moveFileInFileSystem(String givenPath, 
      String targetPath, JShell shell) {
    
    FileSystem fSystem = shell.getfSystem();
    FileSystemNode targetNode = fSystem.getSemiFileSystemNode(targetPath);
    
    if (targetNode != null) {
      if (targetNode.isChildInside(fSystem.getPathLastEntry(targetPath))) {
        Echo echoCommand = new Echo();
        File file = fSystem.getSemiFileSystemNode(givenPath).getFile(
            fSystem.getPathLastEntry(givenPath));
        String[] echoTokens = {"echo", "\"" + file.getContent() + "\"", ">", 
            targetPath + fSystem.getPathLastEntry(givenPath)};
        echoCommand.run(echoTokens, shell);
      } else {
        Echo echoCommand = new Echo();
        File file = fSystem.getSemiFileSystemNode(givenPath).getFile(
            fSystem.getPathLastEntry(givenPath));
        String[] echoTokens = {"echo", "\"" + file.getContent() + "\"", ">", 
            targetPath};
        echoCommand.run(echoTokens, shell);
      }
      Remove remove = new Remove();
      String[] removeTokens = {"rm", givenPath};
      remove.run(removeTokens, shell);
    } else 
      this.setErrors(ErrorHandler.invalidPath(this, targetPath));
    
  }
}

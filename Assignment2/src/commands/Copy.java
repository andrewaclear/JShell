package commands;

import data.Directory;
import data.File;
import data.FileSystem;
import data.FileSystemNode;
import driver.JShell;
import runtime.ErrorHandler;

public class Copy extends Command {
  
  /**
   * Constructor for Move class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public Copy() {
    this.setIdentifier("cp");

    // MakeDirectory must have three tokens
    this.setMaxNumOfArguments(3);
    this.setMinNumOfArguments(3);

    // Error Handling
    this.setErrorTooManyArguments("only two parameters are accepted");
    this.setMissingOperand("only two parameters are accepted");

    // Description
    this.setDescription("This command copies a directory or file into the"
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
      ErrorHandler.moveDirectoryError(tokens[2]);
    else if (givenNode != null) {
      if (givenNode.isChildInside(fSystem.getPathLastEntry(tokens[1]))) {
        copyFileSystemNodeInFileSystem(tokens[1], tokens[2], shell);
      } else if (givenNode.getDirectory().isFileInsideByFileName(
          fSystem.getPathLastEntry(tokens[1]))) {
        copyFileInFileSystem(tokens[1], tokens[2], shell);
      } else
        ErrorHandler.invalidPath(this, tokens[1]);
    } else {
      ErrorHandler.invalidPath(this, tokens[1]);
    }
    return this;
  }
  
  
  private void copyFileSystemNodeInFileSystem(String givenPath, 
      String targetPath, JShell shell) {
    
    FileSystem fSystem = shell.getfSystem();
    
    FileSystemNode clonedFileSystemNode = new FileSystemNode(
        new Directory("Dummy"));
        
    clonedFileSystemNode = fSystem.getFileSystemNode(
        givenPath).cloneFileSystemNode(clonedFileSystemNode);
    
    FileSystemNode targetNode = fSystem.getFileSystemNode(targetPath);

    if (targetNode != null) {
      clonedFileSystemNode.setParent(targetNode);
      targetNode.addChild(clonedFileSystemNode);
    } else 
      ErrorHandler.invalidPath(this, targetPath);

  }
  
  
  private void copyFileInFileSystem(String givenPath, 
      String targetPath, JShell shell) {
    
    FileSystem fSystem = shell.getfSystem();
    FileSystemNode targetNode = fSystem.getSemiFileSystemNode(targetPath);
    FileSystemNode possibleNode = fSystem.getFileSystemNode(targetPath);
    
    if (targetNode != null) {
      if (possibleNode != null) {
        Redirection redirectionCommand = new Redirection();
        File file = fSystem.getSemiFileSystemNode(givenPath).getFile(
            fSystem.getPathLastEntry(givenPath));
        String[] redirectionTokens = {"redirect", "\"" + file.getContent() + 
            "\"", ">", targetPath + "/" + fSystem.getPathLastEntry(givenPath)};
        redirectionCommand.run(redirectionTokens, shell);
      } else {
        Redirection redirectionCommand = new Redirection();
        File file = fSystem.getSemiFileSystemNode(givenPath).getFile(
            fSystem.getPathLastEntry(givenPath));
        String[] redirectionTokens = {"redirect", "\"" + file.getContent() + "\"", 
            ">", targetPath};
        redirectionCommand.run(redirectionTokens, shell);
      }

    } else 
      ErrorHandler.invalidPath(this, targetPath);
  }
  

  
}

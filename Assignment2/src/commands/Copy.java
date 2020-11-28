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
   * The run method of Copy copies a FileSystemNode into another FileSystemNode 
   * or copies a File into another FileSystemNode or overwrites a file into 
   * another File path that already exists or copies the content of a file 
   * into another File path
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
        copyFileSystemNode(tokens[1], tokens[2], shell);
      } else if (givenNode.getDirectory().isFileInsideByFileName(
          fSystem.getPathLastEntry(tokens[1]))) {
        copyFile(tokens[1], tokens[2], shell);
      } else
        ErrorHandler.invalidPath(this, tokens[1]);
    } else {
      ErrorHandler.invalidPath(this, tokens[1]);
    }
    return this;
  }
  
  /**
   * copyFileSystemNode copies a FileSystemNode that the givenPath refers to
   * to another FileSystemNode that targetPath refers 
   * 
   * @param givenPath, a path to a FileSystemNode
   * @param targetPath, a path to a FileSystemNode
   */
  private void copyFileSystemNode(String givenPath, String targetPath, 
      JShell shell) {
    
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
  
  /**
   * copyFile copies a File that the givenPath refers to
   * to another FileSystemNode or File that targetPath refers, if the File
   * already exists, overwrite it's content instead.
   * 
   * @param givenPath, a path to a FileSystemNode
   * @param targetPath, a path to a FileSystemNode
   */
  private void copyFile(String givenPath, String targetPath, JShell shell) {
    
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

package commands;

import data.Cache;
import data.FileSystem;
import data.FileSystemNode;
import io.StandardOutput;
import runtime.ErrorHandler;

/**
 * Takes in an array of tokens from execution and executes the
 * Concatenate command. The Concatenate command; displays the contents of
 * FILE1 and other files (i.e. File2 ....) concatenated in the shell.
 */
public class Concatenate extends Command {
  /**
   * Constructor for Concatenate class. It initializes Description, Identifier,
   * MaxNumOfArguments, ErrorTooManyArguments, MissingOperand from its
   * super class Commands.
   */
  public Concatenate() {
    this.setIdentifier("cat");
    this.setDescription("Display the contents of FILE1 and other files"
      + " (i.e. File2 ....) concatenated in the shell.");
    
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Which files do you wish to display?");
  }
  
  /**
   * Tries to get a FileSystemNode from path, return a FileSystemNode if 
   * successful, else return null.
   * 
   * @param path, a path to a file in the file system
   * @param fSystem, an instance of FileSystem class to read and write
   * to the file structure.
   * @return returns a FileSystemNode at the specified path
   */
  private FileSystemNode tryGetFileSystemNode(String path, FileSystem fSystem) {
    try {
      FileSystemNode node = fSystem.getFileSystemNode(path);
      return node;
    } catch (Exception e) {
      return null;
    }
  }
  
  /**
   * Prints the contents of a specified file to the terminal.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write
   * to the file structure.
   * @param cache, store the current directory stack
   * @return returns a boolean true to mark successful execution
   * @Override overrides run method from super class Command
   */
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    int i = 1;
    String path, name;
    FileSystemNode node;
    try {
      while (i < tokens.length) {
        path = tokens[i];
        
          //Only file name and no path (Located in currentDirectory)
          if(!path.contains("/")) {
            node = fSystem.getCurrentDirectory();
            name = path;
          } else { // Must follow path to find location of file
            //remove fileName from path
            name = path.substring(path.lastIndexOf('/')+1);
            path = path.substring(0, path.lastIndexOf('/') + 1);
            //True then path is relative, else path is absolute 
            if (!(path.charAt(0) == '/')) {
              path = fSystem.getCurrentDirectory().getPath() + path;
            }  
               
            node = tryGetFileSystemNode(path, fSystem);   
          }
          
          if (node.getDirectory().getFile(name) != null) {
            
            StandardOutput.println(node.getDirectory()
                .getFile(name).getContent());
          
            if (i + 1 < tokens.length) {
              StandardOutput.println("\n\n\n");
            }
          } else {
            ErrorHandler.fileNotFound(tokens[i]);
            break;
          }
          i++;
      }
    } catch (Exception e) {
        ErrorHandler.invalidPath(this);
    }

    return true;
  }
}

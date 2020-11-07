package commands;

import data.Cache;
import data.FileSystem;
import data.FileSystemNode;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Concatenate extends Command {

  public Concatenate() {
    this.setIdentifier("cat");
    this.setDescription("Display the contents of FILE1 and other files"
      + " (i.e. File2 ....) concatenated in the shell.");
    
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Which files do you wish to display?");
  }
  
  private FileSystemNode tryGetFileSystemNode(String path, FileSystem fSystem) {
    try {
      FileSystemNode node = fSystem.getFileSystemNode(path);
      return node;
    } catch (Exception e) {
      return null;
    }
  }
  
  @Override
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
          }
          i++;
      }
    } catch (Exception e) {
        ErrorHandler.invalidPath(this);
    }

    return true;
  }
}

/*
 * String path = tokens[3];
    String name;
    FileSystemNode node;
    */
    

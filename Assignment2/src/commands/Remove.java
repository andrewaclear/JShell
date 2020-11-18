package commands;

import data.Cache;
import data.FileSystem;
import data.FileSystemNode;
import runtime.ErrorHandler;

public class Remove extends Command {
  
    public Remove() {
      this.setIdentifier("rm");
  
      // Remove must only have two tokens
      this.setMaxNumOfArguments(2);
      this.setMinNumOfArguments(2);
  
      // Error Handling
      this.setErrorTooManyArguments("only one parameter is accepted");
      this.setMissingOperand("only one parameter is accepted");
  
      // Description
      this.setDescription("Removes the directory from the fileSystem "
          + "including the children of the directory as well as the subpaths "
          + "in the stack");
  }
    
    public boolean run(String[] tokens, FileSystem fileSystem, Cache cache) {
      
      FileSystemNode beforeNode = null;
      
      if (fileSystem.getFileSystemNode(tokens[1]) != null) {
        // Set targetNode to the FileSystemNode that the path leads to
        beforeNode = fileSystem.getSemiFileSystemNode(tokens[1]);
        
        if (beforeNode != null) {
          if (!fileSystem.getCurrentDirectory().getPath().startsWith(
              fileSystem.getFileSystemNode(tokens[1]).getPath())) {
            
            beforeNode.removeChild(fileSystem.getPathLastEntry(tokens[1]));
            
            cache.removeDirectory(fileSystem.getFileSystemNode(
                tokens[1]).getPath());
            
          } else {
            
            //ERROR: Cannot remove directories because it is a subdirectory 
            ErrorHandler.removeDirectoryError(tokens[1]);
          }
        }
        
      }
      return true;
    }
    
}

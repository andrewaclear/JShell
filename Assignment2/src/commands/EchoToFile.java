package commands;

import data.Cache;
import data.File;
import data.FileSystem;
import data.FileSystemNode;
import runtime.ErrorHandler;

public class EchoToFile extends Command{

  public EchoToFile() {
    this.setDescription("If OUTFILE is not provided, print STRING"
      + " on the shell. Otherwise, put \nSTRING into file OUTFILE."
      + " STRING is a string of"
      + " characters surrounded \nby double  quotation marks. This"
      + " creates a new file if OUTFILE does \nnot exists and erases"
      + " the old contents if OUTFILE already exists. \nIn either case,"
      + " the only thing in OUTFILE should be STRING.");
    
    this.setIdentifier("echo");
    this.setMaxNumOfArguments(4);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("too many arguments");
    this.setMissingOperand("please specify a string to print");
  }
  
  private FileSystemNode tryGetFileSystemNode(String path, FileSystem fSystem) {
    try {
      FileSystemNode node = fSystem.getFileSystemNode(path);
      return node;
    } catch (Exception e) {
      return null;
    }
  }
  
  private void toFile(String[] tokens, FileSystemNode node, String name) {
    File curFile = node.getDirectory().getFile(name);
    String desc = tokens[1].replaceAll("\"", "");
    //Check if file exists
    if (curFile != null) {
      if (tokens[2].equals(">>")) { //With >> then appends to file
        curFile.setContent(curFile.getContent() + desc);
      } else { //With > then override the file
        curFile.setContent(desc);
      }
    //If file doesn't exist then create new file with specified contents
    } else {
      File newFile = new File(name);
      newFile.setContent(desc);
      node.getDirectory().addFile(newFile);
    }
  }
 
  @Override
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    String path = tokens[3];
    String name;
    FileSystemNode node;
    
    try {
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
      
      if (name.matches("(.+)?[ /.!@#$%^&*(){}~|<>?](.+)?")) {
        ErrorHandler.invalidName(this, tokens);
        return true;
      }
     
      toFile(tokens, node, name);
      } catch (Exception e) {
        ErrorHandler.invalidPath(this);
      }
    return true;
  }
}
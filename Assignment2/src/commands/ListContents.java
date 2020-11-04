package commands;

import java.util.List;
import data.*;
import io.StandardOutput;

public class ListContents extends Command {

  public ListContents() {
  
    // String iden = "ls";
    // this.setIdentifier(iden);

    // there is no max number of arguments (set when call)
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("");
    this.setDescription("If no paths are given, print the contents"
        + " (file or directory) of the \ncurrent directory, with a new line"
        + " following each of the content \n(file or directory). Otherwise,"
        + " for each path p, the order listed:  \n If p specifies a"
        + " file, print p \n If p specifies a directory, print p, a"
        + " colon, then the contents of that directory, then an extra"
        + " new line. \n If p does not exist, print a suitable message.");
  }

  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    
    // print out current directory case
    if (tokens.length == 1) {
      listSubFolders("", fSystem);
    } else {
      for (int i = 1; i < tokens.length; i++) {
        listSubFolders(tokens[i], fSystem);
      }
    }

    return true;
  }

  private void listSubFolders(String token, FileSystem fSystem) {

    // set path
    String path;
    if (token.equals("")) {
      path = fSystem.getCurrentDirectory().getPath();
    } else if (token.charAt(0) == '/') {
      path = token;
    } else {
      path = fSystem.getCurrentDirectory().getPath()+token;
    }

    FileSystemNode[] subFolders = fSystem.getFileSystemNode(path).getChildren().toArray(new FileSystemNode[0]);
    StandardOutput.println(String.valueOf(subFolders.length));
    // print out sub-folders
    for (FileSystemNode folder : subFolders)   {
      StandardOutput.println(folder.getDirectory().getDirectoryName());
    }

    File[] subFiles = fSystem.getFileSystemNode(path).getDirectory().getFiles().toArray(new File[0]);

    for (File file : subFiles) {
      StandardOutput.println(file.getFileName());
    }
  }

}

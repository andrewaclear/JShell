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
    if (tokens.length == 1) {
      List<FileSystemNode> subFolders = fSystem.getCurrentDirectory().getChildren();
      // subFolders.sort(arg0);
      for (FileSystemNode folder : subFolders) {
        StandardOutput.println(folder.getDirectory().getDirectoryName());
      }

      // INPROGRESS
      // for (fSystem.getCurrentDirectory().)

    }
    return true;
  }

}

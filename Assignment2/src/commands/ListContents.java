package commands;

import java.util.List;
import data.*;
import io.StandardOutput;

public class ListContents extends Command {

  public ListContents(int max) {
  
    // String iden = "ls";
    // this.setIdentifier(iden);

    // there is no max number of arguments (set when call)
    this.setMaxNumOfArguments(max);
    this.setMinNumOfArguments(1);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("");
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

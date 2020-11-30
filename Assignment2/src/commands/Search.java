package commands;

import data.FileSystemNode;
import driver.JShell;
import runtime.ErrorHandler;

public class Search extends Command {

  public Search() {
    this.setDescription("The syntax of the search command is as follows: "
        + "search path ... -type [f|d] -name expression. So here are some"
        + " examples of how " + "the search command may be used: "
        + "search /users/Desktop -type f -name \"xyz\". This command will"
        + " search the directory Desktop "
        + "and search all files (indicated by type f) that have the name"
        + " exactly xyz.search /users/Desktop -type d -name \"abc\". This"
        + " command will search the directory Desktop and search all"
        + " directories (indicated by type d) that have the name exactly abc."
        + " search /users/Desktop. This command will result in an error because"
        + " it has missing arguments.");
    this.setIdentifier("search");
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(6);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("Missing required arguments");
  }

  private String searchForFiles (FileSystemNode node, String item, String path) {
    if (node.getDirectory().getFiles() == null) return "";

    int numFiles = node.getDirectory().getFiles().size();
    String partOutput = "";

    for (int i = 0; i < numFiles; i++) {
      if (node.getDirectory().getFiles().get(i).getFileName().equals(item)) {
        partOutput += path + item + "\n";
        // partOutput += path + ": contains file: " + searchStr + "\n";
        break;
      }
    }

    return partOutput;
  }

  private String searchNode(FileSystemNode node, String item, String path, boolean fParam) {
    if (node.getChildren() == null) return "";

    String partOutput = "";
    String subOutput = "";
    String dirName = "";
    int numFolders = node.getChildren().size();

    if (path.charAt(path.length()-1) != '/') path += "/";

    for (int i = 0; i < numFolders; i++) {
      dirName = node.getChildren().get(i).getDirectory().getDirectoryName();
      if (!fParam && dirName.equals(item)) {
        partOutput += path + item + "\n";
        // partOutput += path + ": contains directory: " + searchStr + "\n";
      }
      // if (path.charAt(path.length()-1) != '/') path += "/";
      subOutput += searchNode(node.getChildren().get(i), item, path+dirName, fParam);
    }

    if (fParam) partOutput += searchForFiles(node, item, path);

    return partOutput + subOutput;
  }

  private boolean validInput(String[] tokens) {
    
  }
  
  @Override
  public Command run(String[] tokens, JShell shell) {
    int len = tokens.length;
    String output = "", temp = "", searchStr = tokens[len - 1];
    FileSystemNode node;

    boolean typeParam = tokens[len - 4].equals("-type");
    boolean fParam = tokens[len - 3].equals("f");
    boolean dParam = tokens[len - 3].equals("d");
    boolean nameParam = tokens[len - 2].equals("-name");
    boolean stringParam = (searchStr.charAt(0) == '\"' && searchStr.charAt(searchStr.length()-1) == '\"');
    String fd = fParam? "file" : "directory";

    if (typeParam && (fParam || dParam) && nameParam && stringParam) {
      String item = searchStr.replace("\"", "");
      for (int iPath = 1; iPath < len - 4; iPath++) {
        node = shell.getfSystem().getFileSystemNode(tokens[iPath]);

        if (node != null) {
          temp = searchNode(node, item, tokens[iPath], fParam);
          if (temp.equals("")) 
            output += tokens[iPath] + ": does not contain "+ fd +": " + searchStr + "\n";
          else output += temp;
        } else {
          this.setErrors(ErrorHandler.invalidPath(this, tokens[iPath]));
          break;
        }
      }
    } else this.setErrors(ErrorHandler.invalidComboOfParams(this, tokens));

    if (output.length() > 1)
      this.setOutput(output.substring(0, output.length() - 1));

    return this;
  }
}

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

  private String searchForFiles (FileSystemNode node, String searchStr, String item, String path) {
    if (node.getDirectory().getFiles() == null) return "";

    int numFiles = node.getDirectory().getFiles().size();
    String partOutput = "";
    // boolean found = false;

    for (int i = 0; i < numFiles; i++) {
      if (node.getDirectory().getFiles().get(i).getFileName().equals(item)) {
        partOutput += path + ": contains file: " + searchStr + "\n";
        // found = true;
        break;
      }
    }
    // if (!found) partOutput += path + ": does not contain file: " + searchStr + "\n";

    return partOutput;
  }

  private String searchNode(FileSystemNode node, String searchStr, String path, boolean fParam) {
    if (node.getChildren() == null) return "";

    String partOutput = "";
    String subOutput = "";
    String dirName = "";
    String item = searchStr.replace("\"", "");
    // boolean found = false;
    int numFolders = node.getChildren().size();

    for (int i = 0; i < numFolders; i++) {
      dirName = node.getChildren().get(i).getDirectory().getDirectoryName();
      if (!fParam && dirName.equals(item)) {
        partOutput += path + ": contains directory: " + searchStr + "\n";
        // found = true;
      }
      if (path.charAt(path.length()-1) != '/') path += "/";
      subOutput += searchNode(node.getChildren().get(i), searchStr, path+dirName, fParam);
    }
    // if (!fParam && !found) {
    //   partOutput += path + ": does not contain directory: " + searchStr + "\n";
    // }

    if (fParam) partOutput += searchForFiles(node, searchStr, item, path);

    return partOutput + subOutput;
  }
  
  @Override
  public Command run(String[] tokens, JShell shell) {
    String output = "";
    FileSystemNode node;
    int len = tokens.length;
    String searchStr = tokens[len - 1];
    String temp = "";

    boolean typeParam = tokens[len - 4].equals("-type");
    boolean fParam = tokens[len - 3].equals("f");
    boolean dParam = tokens[len - 3].equals("d");
    boolean nameParam = tokens[len - 2].equals("-name");
    boolean stringParam = searchStr.charAt(0) == '\"' && searchStr.charAt(searchStr.length()-1) == '\"';

    if (typeParam && (fParam || dParam) && nameParam && stringParam) {
      for (int iPath = 1; iPath < len - 4; iPath++) {
        node = shell.getfSystem().getFileSystemNode(tokens[iPath]);

        if (node != null) {
          temp = searchNode(node, searchStr, tokens[iPath], fParam);
          if (temp.equals("")) {
            if (fParam) {
              output += tokens[iPath] + ": does not contain file: " + searchStr + "\n";
            } else {
              output += tokens[iPath] + ": does not contain directory: " + searchStr + "\n";
            }
          } else output += temp + "\n";
        } else {
          this.setErrors(ErrorHandler.invalidPath(this, tokens[iPath]));
          break;
        }
      }
    } else {
      this.setErrors(ErrorHandler.invalidComboOfParams(this, tokens));
    }

    if (output.length() > 1)
      this.setOutput(output.substring(0, output.length() - 1));

    return this;
  }

}

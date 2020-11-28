package commands;

import data.FileSystemNode;
import driver.JShell;
import runtime.ErrorHandler;

public class Search extends Command {
    
  public Search() {
    this.setDescription("The syntax of the search command is as follows: "
    + "search path ... -type [f|d] -name expression. So here are some"
    + " examples of how "
    + "the search command may be used: "
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
  
  @Override
  public Command run(String[] tokens, JShell shell) {
    String output = "";
    FileSystemNode node;
    boolean found;
    int len = tokens.length;
    int n = 0;
    String item = tokens[len-1].replace("\"","");
    String name = tokens[len-1];

    boolean typeParam = tokens[len-4].equals("-type");
    boolean fParam = tokens[len-3].equals("f");
    boolean dParam = tokens[len-3].equals("d");
    boolean nameParam = tokens[len-2].equals("-name");
    boolean stringParam = tokens[len-1].charAt(0) == '\"';

    if (typeParam && (fParam || dParam) && nameParam && stringParam) {
      for (int iPath = 1; iPath < len-4; iPath++) {
        found = false;
        node = shell.getfSystem().getFileSystemNode(tokens[iPath]);

        if (node != null) {
          if (fParam) {
            n = node.getDirectory().getFiles().size();
            for (int i = 0; i < n; i++) {
              found = node.getDirectory().getFiles().get(i).getFileName().equals(item);
              if (found) {
                output += tokens[iPath] + ": contains file: " + name + "\n";
                break;
              }
            }
            if (!found) output += tokens[iPath] + ": does not contain file: " + name + "\n";
          } else {
            n = node.getChildren().size();
            for (int i = 0; i < n; i++) {
              found = node.getChildren().get(i).getDirectory().getDirectoryName().equals(item);
              if (found) {
                output += tokens[iPath] + ": contains directory: " + name + "\n";
                break;
              }
            }
            if (!found) output += tokens[iPath] + ": does not contain directory: " + name + "\n";
          }
        } else {
          this.setErrors(ErrorHandler.invalidPath(this, tokens[iPath]));
          break;
        }
      }
    } else {
      this.setErrors(ErrorHandler.invalidComboOfParams(this, tokens));
    }
    
    if (output.length() > 1) this.setOutput(output.substring(0, output.length()-1));

    return this;
  }
  
}

package commands;

import java.util.HashMap;
import data.FileSystem;
import io.StandardOutput;
import runtime.ErrorHandler;

public class Manual extends Command {
  //HashMap that stores entries <Command Identifier, Command Description>
  private HashMap<String, String> descriptions = new HashMap<String, String>();
  //All the command descriptions
  /*private String manDesc =  "Print documentation for CMD (s)";*/
  
  /*private String cdDesc =  "Change directory to DIR, which may be relative to"
      + " the current directory\nor may be a full path. As with Unix, .."
      + " means a parent directory and a . \nmeans the current directory."
      + " The directory must be /, the forward slash. \nThe foot of the"
      + " file system is a single slash: /. ";*/
 
 /* private String catDesc = "Display the contents of FILE1 and other files"
      + " (i.e. File2 ....) concatenated in the shell.";*/
 
  /*private String echoDesc = "If OUTFILE is not provided, print STRING"
      + " on the shell. Otherwise, put \nSTRING into :ile OUTFILE."
      + " STRING is a string of"
      + " characters surrounded \nby double  quotation marks. This"
      + " creates a new :ile if OUTFILE does \nnot exists and erases"
      + " the old contents if OUTFILE already exists. \nIn either case,"
      + " the only thing in OUTFILE should be STRING. ";*/
 
  //private String exitDesc = "Quit the program ";
  
 /* private String historyDesc = "This command will print out recent commands,"
      + " one command per line. ";*/
  
  /*private String lsDesc = "If no paths are given, print the contents"
           + " (file or directory) of the \ncurrent directory, with a new line"
           + " following each of the content \n(file or directory). Otherwise,"
           + " for each path p, the order listed:  \n If p specifies a"
           + " file, print p \n If p specifies a directory, print p, a"
           + " colon, then the contents of that directory, then an extra"
           + " new line. \n If p does not exist, print a suitable message.";*/
  
  /*private String mkdirDesc = "This command takes in two arguments only."
      + " Create directories, \neach of which may be relative"
      + " to the current directory or may \nbe a full path."
      + " If creating DIR1 results in any kind of error,"
      + " \ndo not proceed with creating DIR 2. However, if DIR1"
      + " was created \nsuccessfully, and DIR2 creation results in an error,"
      + " then give \nback an error specific to DIR2. ";*/
  
  /*private String popdDesc = "Remove the top entry from the directory stack,"
      + " and cd into it. \nThe removal must be consistent as per the LIFO"
      + " behavior of  a \nstack. The popd command removes the top"
      + " most directory from \nthe directory stack and makes it the"
      + " current working directory. \nIf there is no directory onto"
      + " the stack, then give appropriate \nerror message. ";*/
  
  /*private String pwdDesc = "Print the current working directory"
      + " (including the whole path).  ";*/
  
  /*private String pushdDesc = "Saves the current working directory by pushing"
      + " onto directory stack \nand then changes the new current working"
      + " directory to DIR. The push \nmust be consistent as per the"
      + " LIFO behavior of a stack. The pushd \ncommand saves the old"
      + " current working directory in directory stack \nso that it can"
      + " be returned to at any time (via the popd command). \nThe size"
      + " of the directory stack is dynamic and dependent on the \npushd"
      + " and the popd commands.";*/
  
  public Manual() {
    this.setDescription("Print documentation for CMD (s)");
    
    //Adds entries to hashmap mapping cmd identifier -> cmd description
    descriptions.put("man", this.getDescription());
    descriptions.put("cd", new ChangeDirectory().getDescription());
    descriptions.put("cat", new Concatenate().getDescription());
    descriptions.put("echo", new Echo().getDescription());
    descriptions.put("exit", new Exit().getDescription());
    descriptions.put("history", new History().getDescription());
    descriptions.put("ls", new ListContents().getDescription());
    descriptions.put("mkdir", new MakeDirectory().getDescription());
    descriptions.put("popd", new PopDirectory().getDescription());
    descriptions.put("pwd", new PrintWorkingDirectory().getDescription());
    descriptions.put("pushd", new PushDirectory().getDescription());
 
    this.setIdentifier("man");
    this.setMaxNumOfArguments(-1);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("");
    this.setMissingOperand("What manual page do you want?");
    
  }
  @Override
  public boolean run(String[] tokens, FileSystem fSystem) {
    int i = 1;
    while (i < tokens.length) {
      if (descriptions.containsKey(tokens[i])) {
        StandardOutput.println("Documentation for: " + tokens[i] + "\n");
        StandardOutput.println(descriptions.get(tokens[i]) + "\n");
      } else {
        ErrorHandler.commandNotFoundManual(tokens[i]);
      }
      i++;
    }
    return true;
  }
}

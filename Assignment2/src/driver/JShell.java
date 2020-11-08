// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3:
// UTORID user_name:
// UT Student #:
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import io.*;
import runtime.*;
import data.*;

public class JShell {

  public static void main(String[] args) {
    
    boolean run = true;
    Parser parse = new Parser();
    Execution execute = new Execution();
    StandardInput input = new StandardInput();
    FileSystem fSystem = new FileSystem();
    Cache cache = new Cache();
    
    //Main program loop
    while (run) {
      StandardOutput.print(fSystem.getCurrentDirectory()
          .getDirectory().getDirectoryName()+" #: ");
      // StandardOutput.println("/#: "); //Shows beginning of a line
      input.nextLine();
      // add line to history
      cache.addHistoryLine(input.current_line);
      //Parses input into tokens and then executes the command
      run = execute.executeCommand(parse.parse(input.current_line),
          fSystem, cache);
    }
    input.close();
  }
}

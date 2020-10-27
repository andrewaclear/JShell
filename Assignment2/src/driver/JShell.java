// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student3:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student4:
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

import java.util.Scanner;
import io.*;
import runtime.*;

public class JShell {

  public static void main(String[] args) {
    
    String execution_log = "";
    Parser parse = new Parser();
    Execution execute = new Execution();
    StandardInput input = new StandardInput();
    //Note: Consider adding error checking for parser
    //Also Note we need an extensive error class with all types of different
    //errors.
    
    //Main program loop
    while (!execution_log.equals("exit")) {
      StandardOutput.println("/#: "); //Shows beginning of a line
      input.nextLine();
      //Parses input into tokens and then executes the command
      execution_log = execute.execute_command(parse.parse(input.current_line));
      if (execution_log == "exit") break;
      else if (execution_log != "successful") {
        StandardOutput.println("Error"); //TODO: Add error class
      } 
    }
    input.close();
  }

}

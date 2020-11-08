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
/**
* The JShell program is a Java implementation of a bash shell. 
* The following commands are included: exit, mkdir, cd, ls, pwd, pushd, popd,
* history, cat, echo, man. 
* The goal of this project is to make smart design choices about when to use: 
* inheritance, composition, polymorphism, reflection and/or interfaces.
* Further, it was to practice developing in an Agile environment; writing
* dailyScrum meeting logs and meeting with our team to write sprint backlogs 
* and a product backlog. Finally, we also created an auto tester using
* JUnit and assert and then generated documentation using Javadocs.
* Logistics: Collaborated and commited code using SVN
* 
* @author  Christopher Suh
* @author  Christian Chen Liu
* @author  Andrew D'Amario
* @version 173
* @since   2020/11/10
*/
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

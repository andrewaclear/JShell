// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name:
// UT Student #:
// Author:
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

public class JShell {

  public static void main(String[] args) {
    
    boolean exit_condition = false;
    Parser parse = new Parser();
    Execution execute = new Execution();
    Scanner scan = new Scanner(System.in); // Counts as CommandLineReader
    String current_line = "";
    boolean executed;
    
    //Main program loop
    while (!exit_condition) {
      System.out.print("/#: "); //Shows beginning of a line
      current_line = scan.nextLine(); //Awaits input 
      //Parses input into tokens and then executes the command
      executed = execute.execute_command(parse.parse(current_line)); 
      if (!executed) {
        System.out.println("Error"); //TODO: Add error class
      }
    }
  }

}

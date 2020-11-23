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
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package io;

import commands.Echo;
import driver.JShell;

/**
 * Defines how the system prints out to the shell.
 */
public class StandardOutput {


  /**
   * Prints message out to the shell on it's own line.
   * 
   * @param message, to be printed out to the shell
   */
  public static void println(String message) {
    System.out.println(message);
  }

  public static boolean containsArrow(String[] tokens) {
    int indexArrow = tokens.length - 2 >= 0 ? tokens.length - 2: 0;

    return tokens[indexArrow].equals(">") ||
          tokens[indexArrow].equals(">>");

  }

  public static void println(String[] tokens, String output, JShell shell) {
    int indexArrow = tokens.length - 2 >= 0 ? tokens.length - 2: 0;
   
    boolean containsArrow = containsArrow(tokens);

    if (!containsArrow) {
      System.out.println(output);
    } else {
      String[] tokens2 = new String[4];
      
      tokens2[0] = "echo";
      tokens2[1] = "\"" + output + "\"";
      tokens2[2] = tokens[indexArrow];
      tokens2[3] = tokens[indexArrow + 1];

      Echo echo = new Echo();
      echo.run(tokens2, shell);
    }
  }


  /**
   * Prints message out to the shell without adding a line return.
   * 
   * @param message, to be printed out to the shell
   */
  public static void print(String message) {
    System.out.print(message);
  }
}

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
package commands;

import data.*;

public class Command {
  private String identifier;
  private int maxNumOfArguments;
  private int minNumOfArguments;
  private String errorTooManyArguments;
  private String errorMissingOperand;
  private String description;
  
  public void setIdentifier(String iden) {
    this.identifier = iden;
  }

  public void setMaxNumOfArguments(int num) {
    this.maxNumOfArguments = num;
  }

  public void setMinNumOfArguments(int num) {
    this.minNumOfArguments = num;
  }

  public void setErrorTooManyArguments(String err) {
    this.errorTooManyArguments = err;
  }

  public void setMissingOperand(String err) {
    this.errorMissingOperand = err;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public int getMaxNumOfArguments() {
    return this.maxNumOfArguments;
  }

  public int getMinNumOfArguments() {
    return this.minNumOfArguments;
  }

  public String getErrorTooManyArguments() {
    return this.errorTooManyArguments;
  }

  public String getErrorMissingOperand() {
    return this.errorMissingOperand;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // run command, overridden by subclasses
  public boolean run(String[] tokens, FileSystem fSystem, Cache cache) {
    return true;
  }


}

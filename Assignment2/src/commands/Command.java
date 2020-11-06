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

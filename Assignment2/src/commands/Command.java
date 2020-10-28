package commands;

public class Command {
  private String description;
  private String identifier;
  private int numOfArguments;
  private String errorTooManyArguments;
  
  public void setDescription(String desc) {
    this.description = desc;
  }
  
  public void setIdentifier(String iden) {
    this.identifier = iden;
  }

  public void setNumOfArguments(int num) {
    this.numOfArguments = num;
  }

  public void setErrorTooManyArguments(String err) {
    this.errorTooManyArguments = err;
  }

  public String getDescription() {
    return this.description;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public int getNumOfArguments() {
    return this.numOfArguments;
  }

  public String getErrorTooManyArguments() {
    return this.errorTooManyArguments;
  }


  public boolean run(String[] tokens) {
    return true;
  }

}

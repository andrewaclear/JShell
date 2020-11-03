package commands;

public class Echo extends Command {
  
  public Echo() {
    this.setIdentifier("echo");
    this.setDescription("If OUTFILE is not provided, print STRING"
        + " on the shell. Otherwise, put \nSTRING into :ile OUTFILE."
        + " STRING is a string of"
        + " characters surrounded \nby double  quotation marks. This"
        + " creates a new :ile if OUTFILE does \nnot exists and erases"
        + " the old contents if OUTFILE already exists. \nIn either case,"
        + " the only thing in OUTFILE should be STRING. ");
  }
}

package commands;

public class Echo extends Command {
  
  public Echo() {
    String desc = "If OUTFILE is not provided, print STRING on the shell."
        + " Otherwise, put STRING into :ile OUTFILE. STRING is a string of"
        + " characters surrounded by double  quotation marks. This"
        + " creates a new :ile if OUTFILE does not exists and erases"
        + " the old contents if OUTFILE already exists. In either case,"
        + " the only thing in OUTFILE should be STRING. ";
    
    String iden = "echo";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

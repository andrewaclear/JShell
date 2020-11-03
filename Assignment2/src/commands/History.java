package commands;

public class History extends Command {
  
  public History() {
    this.setIdentifier("history");
    this.setDescription("This command will print out recent commands,"
        + " one command per line. ");
  }
}

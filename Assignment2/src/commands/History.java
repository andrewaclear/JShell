package Commands;

public class History extends Command {
  
  public History() {
    String desc = "This command will print out recent commands,"
        + " one command per line. ";
    
    String iden = "history";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

package commands;

public class Manual extends Command {
  
  public Manual() {
    String desc = "Print documentation for CMD (s)";
    
    String iden = "man";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

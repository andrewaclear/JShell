package Commands;

public class MakeDirectory extends Command {

  public MakeDirectory() {
    String desc = "This command takes in two arguments only."
        + " Create directories, each of which may be relative"
        + " to the current directory or may be a full path."
        + " If creating DIR1 results in any kind of error,"
        + " do not proceed with creating DIR 2. However, if DIR1"
        + " was created successfully, and DIR2 creation results in an error,"
        + " then give back an error specific to DIR2. ";
    
    String iden = "mkdir";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

package Commands;

public class ChangeDirectory extends Command {
  
  public ChangeDirectory() {
    String desc = "Change directory to DIR, which may be relative to"
        + " the current directory or may be a full path. As with Unix, .."
        + " means a parent directory and a . means the current directory."
        + " The directory must be /, the forward slash. The foot of the"
        + " file system is a single slash: /. ";
    
    String iden = "cd";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

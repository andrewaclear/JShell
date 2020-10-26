package Commands;

public class List extends Command {

  public List() {
    String desc = "If no paths are given, print the contents"
        + " (file or directory) of the current directory, with a new line"
        + " following each of the content (file or directory).  Otherwise,"
        + " for each path p, the order listed:  \n •If p specifies a file,"
        + " print p \n •If p specifies a directory, print p, a colon, then the"
        + " contents of that directory, then an extra new line. \n"
        + " •If p does not exist, print a suitable message.  ";
    
    String iden = "ls";
    this.setDescription(desc);
    this.setIdentifier(iden);
  }
}

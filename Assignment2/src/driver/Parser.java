package driver;

public class Parser {
  //Takes in a line as a String and returns an array of tokens of type String
  public String[] parse(String line) {
    String temp;
    
    temp = line.replaceAll(" +", " ");
    temp = temp.trim();
    
    return temp.split(" ", 0);
  }
}

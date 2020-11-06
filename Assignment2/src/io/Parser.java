package io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import runtime.ErrorHandler;

public class Parser {
  //Takes in a line as a String and returns an array of tokens of type String
  public String[] parse(String line) {
    String temp;
    String tempString = "";
    int i = 0, j = 0;
    ArrayList<String> tokens = new ArrayList<String>();
    
    temp = line.replaceAll(" +", " ");
    temp = temp.trim();
    
    mainLoop:
    while (i < temp.length()) {
      if (temp.charAt(i) == ' ') {
        tokens.add(tempString);
        tempString = "";
        i++;
      } else if (temp.charAt(i) == '\"') {
          j = i + 1;
          while (temp.charAt(j) != '\"') {
            tempString += temp.charAt(j);
            j++;
            if (j >= temp.length()) {
              ErrorHandler.illegalString();
              tokens.clear();
              tokens.add("Failed Parsing");
              break mainLoop;
            }
          }
          tokens.add("\"" + tempString + "\"");
          i = j + 2;
          tempString = "";
      } else {
        tempString += temp.charAt(i);
        if (i + 1 == temp.length()) tokens.add(tempString);
        i++;
      }
    }
    
    String[] tokensArray = new String[tokens.size()];
 
    return tokens.toArray(tokensArray);
  }
}

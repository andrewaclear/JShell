package io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import runtime.ErrorHandler;

public class Parser {
  //Takes in a line as a String and returns an array of tokens of type String
  public String[] parse(String line) {
    String temp; // input string parsed to remove extra spaces
    String tempString = ""; //temp string used to build each argument
    int i = 0, j = 0; // counters
    //Stores tokens dynamically, later converted to an array
    ArrayList<String> tokens = new ArrayList<String>(); 
    
    temp = line.replaceAll(" +", " ");
    temp = temp.trim();
    
    mainLoop:
    while (i < temp.length()) {
      if (temp.charAt(i) == ' ') { //' ' marks the end of an argument
        tokens.add(tempString);
        tempString = "";
        i++;
      } else if (temp.charAt(i) == '\"') { //Start of a string (ignore spaces)
          j = i + 1;
          if (j < temp.length()) { // In case input is just "
            while (temp.charAt(j) != '\"') { //get whole string as single arg 
              tempString += temp.charAt(j);
              j++;
              if (j >= temp.length()) { // No closing " for the string
                tokens = failedParsing(tokens);
                break mainLoop;
              }
            }
            tokens.add("\"" + tempString + "\""); // Add string to tokens
            i = j + 2; //Update counter to new position in input string; temp
            tempString = "";
          } else {
            tokens = failedParsing(tokens);
            break mainLoop;
          }
      //Continue adding characters to build string argument
      } else {
        tempString += temp.charAt(i);
      //Last character before end of input
        if (i + 1 == temp.length()) tokens.add(tempString); 
        i++;
      }
    }
    
    String[] tokensArray = new String[tokens.size()];
 
    return tokens.toArray(tokensArray);
  }
  // Used to clear tokens list when parser has bad input
  private ArrayList<String> failedParsing(ArrayList<String> tokens) {
    ErrorHandler.illegalString();
    String command = tokens.get(0);
    tokens.clear();
    tokens.add(command);
    tokens.add("Failed Parsing");
    
    return tokens;
  }
}

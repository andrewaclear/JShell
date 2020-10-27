package io;

import java.util.Scanner;

public class StandardInput {

    // Counts as CommandLineReader
    private Scanner scan = new Scanner(System.in); 
    public String current_line = "";
    
    public void nextLine() {
        current_line = scan.nextLine(); //Awaits input 
    }

    public void close() {
        scan.close();
    }
}

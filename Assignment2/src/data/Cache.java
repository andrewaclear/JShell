// **********************************************************
// Assignment2:
// Student1: Christian Chen Liu
// UTORID user_name: Chenl147
// UT Student #: 1006171009
// Author: Christian Chen Liu
//
// Student2: Christopher Suh
// UTORID user_name: suhchris
// UT Student #: 1006003664
// Author: Christopher Suh
//
// Student3: Andrew D'Amario
// UTORID user_name: damario4
// UT Student #: 1006618947
// Author: Andrew D'Amario
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package data;

import java.util.LinkedList;
import java.util.List;

public class Cache {
    
    private List<String> history = new LinkedList<String>();
    private LinkedList<String> dirStack = new LinkedList<String>();

    public void addHistoryLine(String line) {
        history.add(line);
    }

    public String getHistory(int i) {
        return history.get(i);
    }

    public int getHistorySize() {
        return history.size();
    }

    public String popDirectoryStack() {
        return dirStack.pop();
    }

    public void pushDirectoryStack(String path) {
        dirStack.push(path);
    }
}

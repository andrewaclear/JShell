package data;

import java.util.LinkedList;
import java.util.List;

public class Cache {
    
    private List<String> history = new LinkedList<String>();
    private List<String> dirStack = new LinkedList<String>();

    public void addHistoryLine(String line) {
        history.add(line);
    }

    public String getHistory(int i) {
        return history.get(i);
    }

    public int getHistorySize() {
        return history.size();
    }
}

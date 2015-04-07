package eda095.lab2.sd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class SD {
    private LinkedList<String> urls;

    private Set<String> visited;

    public SD() {
        urls = new LinkedList<String>();
        visited = new HashSet<String>();
    }

    public synchronized String getUrl() {
        while (urls.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return urls.poll();
    }

    public synchronized void putUrl(String url) {
        urls.add(url);
        notifyAll();
    }

    public synchronized boolean hasVisited(String url) {
        return visited.contains(url);
    }

    public synchronized void visit(String url) {
        visited.add(url);
    }

    public synchronized int numVisited() {
        return visited.size();
    }
}

package eda095.lab4.thread;

import java.util.ArrayList;

import eda095.lab1.Downloader;
import eda095.lab1.Parser;
import eda095.lab2.sd.SD;

public class Runner implements Runnable {

    private SD sharedData;

    public Runner(SD sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        String url = null;
        while (sharedData.numVisited() < 1000) {
            url = sharedData.getUrl();
            System.out.println("Visiting " + url);
            System.out.println("Has visited " + (sharedData.numVisited() + 1));
            if (sharedData.hasVisited(url)) {
                System.out.println("Skipped " + url + " since it's already visited.");
                continue;
            }
            sharedData.visit(url);
            ArrayList<String> urls = Parser.getHrefs(url);
            for (String string : urls) {
                sharedData.putUrl(string);
            }
        }
    }
}

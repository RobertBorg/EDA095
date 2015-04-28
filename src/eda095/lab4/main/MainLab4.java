package eda095.lab4.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eda095.lab2.sd.SD;
import eda095.lab4.thread.Runner;

public class MainLab4
{

    public static void main(String[] args) {
        SD sharedData = new SD();
        int numWorkers = 10;
        ExecutorService e = Executors.newFixedThreadPool(10);
        for (int i = 0; i < numWorkers; i++) {
            e.submit(new Runner(sharedData));
        }
        sharedData.putUrl("http://cs.lth.se/pierre_nugues/");
    }

}

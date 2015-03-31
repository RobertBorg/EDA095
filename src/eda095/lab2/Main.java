package eda095.lab2;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eda095.lab1.Parser;
import eda095.lab2.sd.SD;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> hrefs = Parser.getHrefs(args[0]);
		SD sd = new SD();
		ArrayList<String> pdfs = new ArrayList<String>();
		for(String s: hrefs) {
			if(s.endsWith(".pdf")) {
				pdfs.add(s);
			}
		}
		ExecutorService e = Executors.newFixedThreadPool(10);
		
		for(String s: pdfs) {
			sd.putUrl(s);
			e.submit(new eda095.lab2.constr.Runner(s));
		}
		
		Thread t = new eda095.lab2.thread.Runner(sd);
		t.start();
		
		

	}

}

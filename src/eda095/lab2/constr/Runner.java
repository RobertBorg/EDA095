package eda095.lab2.constr;

import eda095.lab1.Downloader;

public class Runner implements Runnable {
	private String url;
	public Runner(String url) {
		this.url = url;
	}
	public void run() {
		Downloader dl = new Downloader(url, true);
		if(dl.download()) {
			System.out.println(String.format("Completed download of %s using constructor metod", url));
		}
	}
}

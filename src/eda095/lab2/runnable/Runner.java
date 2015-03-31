package eda095.lab2.runnable;

import eda095.lab1.Downloader;
import eda095.lab2.sd.SD;

public class Runner implements Runnable {
	private SD sd;
	public Runner(SD sd) {
		this.sd = sd;
	}
	public void run() {
		String url;
		while( (url = sd.getUrl()) != null) {
			Downloader dl = new Downloader(url, true);
			if(dl.download()) {
				System.out.println(String.format("Download of %s completed using runnable mehod; id: %s", url, Thread.currentThread().getName()));
			}
		}
	}
}

package eda095.lab2.sd;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import eda095.lab1.Downloader;

public class Runner {
	private SD sd;
	public Runner(SD sd) {
		this.sd = sd;
	}
	public void run() {
		String url;
		while((url = sd.getUrl()) != null) {
			Downloader dl = new Downloader(url, true);
			if(dl.download())
				System.out.println(String.format("Completed download of %s", url)); 
		}
	}
}

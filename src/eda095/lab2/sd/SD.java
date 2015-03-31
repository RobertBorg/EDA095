package eda095.lab2.sd;

import java.util.LinkedList;

public class SD {
	private LinkedList<String> urls;
	public SD() {
		urls = new LinkedList<String>();
	}
	public synchronized String getUrl() {
		return urls.poll();
	}
	
	public synchronized void putUrl(String url) {
		urls.add(url);
	}
}

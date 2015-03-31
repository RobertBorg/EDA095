package eda095.lab2.sd;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Runner implements Runnable {
	private SD sd;
	public Runner(SD sd) {
		this.sd = sd;
	}
	public void run() {
		String url;
		while((url = sd.getUrl()) != null) {
			URL u = null;
			try {
				u = new URL(url);
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			InputStream is = null;
			try {
				is = (InputStream) u.getContent();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int b;
			try {
				while((b = is.read())!= -1) {
					//throw away ???
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(String.format("completed download of %s", url)); 
		}
	}
}

package eda095.lab1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
	private boolean discard;
	private String url;
	private ByteArrayOutputStream bb;
	int bytecount = 0;
	public Downloader(String url, boolean discard) {
		this.url = url;
		this.discard = discard;
		bb = new ByteArrayOutputStream();
	}
	public boolean download() {
		URL url = null;
		try {
			url = new URL(this.url);
		} catch (MalformedURLException e) {
			return false;
		}
		InputStream is;
		try {
			is = (InputStream) url.getContent();
		} catch (IOException e) {
			return false;
		}
		
		byte[] buff = new byte[1024*4];
		int read = 0;
		try {
			while((read = is.read(buff)) != -1) {
				bytecount += read;
				if(!discard)
					bb.write(buff,0,read);
			}
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	public byte[] data() {
		return bb.toByteArray();
	}
	public int bytesDownloaded() {
		return bytecount;
	}
}

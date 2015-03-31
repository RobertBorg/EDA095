package eda095.lab1;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> hrefs = Parser.getHrefs(args[0]);
		
		for(String s: hrefs) {
			if(s.endsWith(".pdf")) {
				Downloader dl = new Downloader(s, true);
				if(dl.download())
					System.out.println(String.format("Downloaded %s, a total of %d bytes", s, dl.bytesDownloaded()));
			}
				
		}
	}

}

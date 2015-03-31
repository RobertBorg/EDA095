package eda095.lab1;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL(args[0]);
		ArrayList<String> hrefs = new ArrayList<String>(); 
		char[] troll = "<a href=\"".toCharArray();
		InputStream hip = (InputStream)url.getContent();
		int state = 0;
		int c = 0;
		for(;c != -1;) {
			c = hip.read();
			if( c == -1)
				break;
			if(troll[state] == c){
				state = ++state % troll.length;
				if(state == 0) {
					StringBuilder sb = new StringBuilder();					
					c = hip.read();
					while( c != -1 && c != '"') {
						sb.append((char)c);
						c = hip.read();
					}
					hrefs.add(sb.toString());
					state = 0;
				}
			} else if(Character.isWhitespace(c)){
					continue;
			} else {
				if( state>3) state = 3;
				else if( state>0) state = 0;
			}
			
		}
		for(String s: hrefs) {
			if(s.endsWith(".pdf"))
				System.out.println(s);
		}
	}

}

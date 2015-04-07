package eda095.lab3.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServerThread extends Thread {
	private SharedServerData ssd;
	private Socket s;
	public ServerThread(SharedServerData ssd, Socket s) {
		this.ssd = ssd;
		this.s = s;
	}
	public void run() {
		InputStream is = null;
		try {
			is = s.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		byte[] buffer = new byte[1024*16];
		while(true) {
			int read = 0;
			try {
				read = is.read(buffer);
				ssd.broadcast(buffer, read);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			
		}
	}
}

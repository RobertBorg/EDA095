package eda095.lab3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private ServerSocket ss;
	public Server(int port) throws IOException {
		ss = new ServerSocket(port);
	}
	public void run() {
		Socket s;
		try {
			while((s = ss.accept()) != null) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

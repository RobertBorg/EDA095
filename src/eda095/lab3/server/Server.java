package eda095.lab3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private ServerSocket ss;
	private SharedServerData sd;
	public Server(int port, SharedServerData sd) throws IOException {
		ss = new ServerSocket(port);
		this.sd = sd;
	}
	public void run() {
		Socket s;
		try {
			while((s = ss.accept()) != null) {
				sd.addConnection(s);
				ServerThread st = new ServerThread(sd, s);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

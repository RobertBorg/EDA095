package eda095.lab3.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedServerData {
	private ArrayList<Socket> connections;
	public SharedServerData() {
		connections = new ArrayList<Socket>();
	}

	public synchronized boolean addConnection(Socket s ) {
		if(connections.contains(s))
			return false;
		connections.add(s);
		return true;
	}
	public synchronized void broadcast(byte[] buff, int len) {
		for(Socket s: connections) {
			try {
				OutputStream os = s.getOutputStream();
				os.write(buff, 0, len);
				os.flush();
			} catch (IOException e) {
				connections.remove(s);
				e.printStackTrace();
			}
		}
	}
}

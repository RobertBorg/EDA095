package eda095.lab3.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;

public class SingleThreadedServer extends Thread {
	private SharedServerData ssd;
	private ServerSocket ss;
	public SingleThreadedServer(SharedServerData ssd) throws IOException {
		this.ssd = ssd;
		ss = new ServerSocket(1338);
	}
	public void run() {
		while(true) {
			try {
				Socket s = ss.accept();
				ssd.addConnection(s);
				InputStream in = s.getInputStream();
				byte[] buffer = new byte[16*1024];
				int len;
				while((len = in.read(buffer)) != -1) {
					ssd.broadcast(buffer, len);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

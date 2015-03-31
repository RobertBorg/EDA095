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
		int pos = 0;
		int posProcess = 0;
		byte[] buffer = new byte[1024*8];
		int state = 0;
		int packetStart = 0;
		int packetEnd = 0;
		while(true) {
			int read = 0;
			try {
				read = is.read(buffer, pos, pos < posProcess ? posProcess - pos : buffer.length - pos);
				pos += read;
				if(pos == buffer.length - 1)
					pos = 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			//lets see if we can find encapsulated packet AA55 ------ 55AA
			if(state == 0) {
				if(buffer[posProcess] == 0xAA && buffer[posProcess+1] == 0x55){
					++state;
					packetStart = (posProcess + 2) % buffer.length;
				} else {
					//kasta elakt exception
				}
			} else if(state == 1) { //packetstart found look for end
				ByteBuffer bb = ByteBuffer.wrap(buffer, packetStart, buffer.length - packetStart);
				int pktlength = bb.asIntBuffer().get();
				//boundscheck
				if(buffer[(packetStart + pktlength)%buffer.length] == 0x55 &&  buffer[(packetStart + pktlength +1)%buffer.length] == 0xAA) {
					packetEnd = (packetStart + pktlength)%buffer.length;
					state = 0;
				}
			}
			
		}
	}
	private boolean parse(ByteBuffer bb) {
		return false;
	}
}

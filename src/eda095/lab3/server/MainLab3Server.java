package eda095.lab3.server;

import java.io.IOException;

public class MainLab3Server {

	public static void main(String[] args) {
		SharedServerData ssd = new SharedServerData();
		try {
			MultiServer s = new MultiServer(1338, ssd);
			s.start();
		} catch (IOException e) {
			System.out.println("CAN'T SET UP SERVER HERE!!");
			e.printStackTrace();
		}

	}

}

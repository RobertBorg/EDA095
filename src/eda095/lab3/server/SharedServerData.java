package eda095.lab3.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedServerData {
	private HashMap<Socket, String> sn;
	private HashMap<String, Socket> ns;
	private ArrayList<Socket> connections;
	public SharedServerData() {
		sn = new HashMap<Socket, String>();
		ns = new HashMap<String, Socket>();
		connections = new ArrayList<Socket>();
	}
	public synchronized boolean addParticipant(Socket s, String name) {
		if(ns.containsKey(name) || sn.containsKey(s))
			return false;
		sn.put(s, name);
		ns.put(name, s);
		return true;
	}
	public synchronized boolean addConnection(Socket s ) {
		if(connections.contains(s))
			return false;
		connections.add(s);
		return true;
	}
}

package eda095.lab5.client;

import eda095.lab5.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MainLab5Client
{

    public static void main(String[] args) throws IOException
    {
        SharedData data = new SharedData();
        MulticastSocket ms = new MulticastSocket();
        MCResponseReceiver receiver = new MCResponseReceiver(data, ms);
        receiver.start();
        MCSender sender = new MCSender(ms);
        sender.start();
        Client client = new Client(data);
        client.start();


    }
}

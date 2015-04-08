package eda095.lab5.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MCResponseReceiver extends Thread
{
    private SharedData data;

    private MulticastSocket socket;

    public MCResponseReceiver(SharedData data, MulticastSocket socket)
    {

        this.socket = socket;
        this.data = data;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            byte[] receiveBuffer = new byte[16 * 1024];
            DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            try
            {
                socket.receive(packet);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String received = new String(packet.getData(), 0, packet.getLength());
            String[] hostAndUUID = received.split(" ");
            String[] addressAndPort = hostAndUUID[0].split(":");
            Host h = new Host(hostAndUUID[1], addressAndPort[0], addressAndPort[1]);
            data.addHost(h);
        }
    }
}

package eda095.lab5.server.threads;

import eda095.lab5.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServer extends Thread
{

    private MulticastSocket socket;

    private int timePort;

    private String uuid;

    public MCServer(int port, int timePort, String uuid)
    {
        try
        {
            this.timePort = timePort;
            this.socket = new MulticastSocket(port);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            socket.joinGroup(ia);
            this.uuid = uuid;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            byte[] buf = new byte[65536];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            try
            {
                socket.receive(dp);
                if (dp.getData()[0] == Constants.BROADCAST_COMMAND)
                {
                    byte[] sendBuffer = (InetAddress.getLocalHost().getHostAddress() + ':' + timePort + ' ' + uuid).getBytes();
                    DatagramPacket response = new DatagramPacket(sendBuffer, sendBuffer.length, dp.getAddress(), dp.getPort());
                    DatagramSocket socket = new DatagramSocket();
                    socket.send(response);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

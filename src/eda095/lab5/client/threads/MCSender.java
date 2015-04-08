package eda095.lab5.client.threads;

import eda095.lab5.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCSender extends Thread
{

    private MulticastSocket socket;

    public MCSender(MulticastSocket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            //Send the multicast
            try
            {
                socket.setTimeToLive(1);
                InetAddress ia = InetAddress.getByName("experiment.mcast.net");
                byte[] buf = { Constants.BROADCAST_COMMAND };
                DatagramPacket dp = new DatagramPacket(buf, buf.length, ia, 4099);
                socket.send(dp);
                Thread.sleep(5000);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

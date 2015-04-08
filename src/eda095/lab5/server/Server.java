package eda095.lab5.server;

import eda095.lab5.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Server extends Thread
{

    private DatagramSocket ss;

    public Server(int port)
    {
        try
        {
            ss = new DatagramSocket(port);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {

        try
        {
            while (!isInterrupted())
            {
                byte[] buffer = new byte[1];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                ss.receive(packet);
                if (packet.getLength() != 1)
                    continue;
                switch (packet.getData()[0])
                {
                    case Constants.GET_TIME_COMMAND:
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                        String date = format.format(new Date());
                        byte[] outputBuffer = ("Klockan är två minuter över l333333t!").getBytes();
                        packet = new DatagramPacket(outputBuffer, outputBuffer.length, packet.getAddress(), packet.getPort());
                        ss.send(packet);
                        break;
                    case Constants.BROADCAST_COMMAND:
                        break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

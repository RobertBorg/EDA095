package eda095.lab5.client;

import eda095.lab5.Constants;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Scanner;

public class Client extends Thread
{

    private SharedData data;

    public Client(SharedData data)
    {
        this.data = data;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            try
            {
                List<Host> foundHosts = data.getHosts();
                System.out.println("Hosts:");
                for (int i = 0; i < foundHosts.size(); i++)
                {
                    System.out.println((i + 1) + ": " + foundHosts.get(i));
                }
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();
                if (line.equals("exit"))
                    System.exit(0);
                int selection = Integer.parseInt(line);
                if (selection < 1 || selection > foundHosts.size())
                    continue;
                Host host = foundHosts.get(selection - 1);
                byte[] buffer = { Constants.GET_TIME_COMMAND };
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host.address), Integer.parseInt(host.port));
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);
                byte[] receiveBuffer = new byte[1024];
                packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(packet);
                System.out.println("Time from server: " + new String(packet.getData(), 0, packet.getLength()));
            }
            catch (NumberFormatException e)
            {
                //Just skip to next input
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (java.io.IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

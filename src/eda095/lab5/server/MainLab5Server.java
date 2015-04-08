package eda095.lab5.server;

import eda095.lab5.Constants;
import eda095.lab5.server.threads.MCServer;
import eda095.lab5.server.threads.Server;

import java.util.Random;
import java.util.UUID;

public class MainLab5Server
{

    public static void main(String[] args)
    {
        Random r = new Random();
        int port = 1337 + r.nextInt(20);
        Server server = new Server(port);
        String uuid = UUID.randomUUID().toString();
        System.out.println("I am " + uuid);
        MCServer mcServer = new MCServer(Constants.MULTICAST_PORT, port, uuid);
        mcServer.start();
        server.start();
    }
}

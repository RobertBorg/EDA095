package eda095.lab5.client;

import eda095.lab5.client.model.Host;

import java.util.ArrayList;
import java.util.List;

public class SharedData
{
    private List<Host> hosts = new ArrayList<Host>();

    public synchronized List<Host> getHosts() throws InterruptedException
    {
        if (hosts.size() == 0)
            wait();
        return hosts;
    }

    public synchronized void addHost(Host host)
    {
        if (!this.hosts.contains(host))
        {
            this.hosts.add(host);
            notifyAll();
        }
    }

}

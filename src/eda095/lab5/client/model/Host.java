package eda095.lab5.client.model;

public class Host
{

    public String uuid, address, port;

    public Host(String uuid, String address, String port)
    {
        this.uuid = uuid;
        this.address = address;
        this.port = port;

    }

    @Override
    public boolean equals(Object obj)
    {
        return uuid.equals(((Host) obj).uuid);
    }

    @Override
    public String toString()
    {
        return address + ':' + port + " - " + uuid;
    }
}


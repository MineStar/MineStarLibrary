package de.minestar.bungeebridge.protocol;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketHeadData {
    private final String channel;
    private final String targetServer;
    private final String subChannel;

    public PacketHeadData(DataInputStream dataInputStream) throws IOException {
        this.channel = dataInputStream.readUTF();
        this.targetServer = dataInputStream.readUTF();
        this.subChannel = dataInputStream.readUTF();
    }

    public String getChannel() {
        return channel;
    }

    public String getTargetServer() {
        return targetServer;
    }

    public String getSubChannel() {
        return subChannel;
    }
}

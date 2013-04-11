package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class DataSendPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.DATA_SEND;

    private String playerName;
    private String serverName;
    private int dataLength;
    private byte[] data;

    public DataSendPacket(String playerName, String serverName, byte[] data) {
        super(thisPacketType);
        this.playerName = playerName;
        this.serverName = serverName;
        this.dataLength = data.length;
        this.data = data;
    }

    public DataSendPacket(PacketType type) {
        super(thisPacketType);
    }

    public DataSendPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeUTF(this.serverName);
        dataOutputStream.writeInt(this.dataLength);
        dataOutputStream.write(this.data);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.serverName = dataInputStream.readUTF();
        this.dataLength = dataInputStream.readInt();
        this.data = new byte[this.dataLength];
        dataInputStream.read(this.data);
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getServerName() {
        return serverName;
    }

    public int getDataLength() {
        return dataLength;
    }

    public byte[] getData() {
        return data;
    }
}

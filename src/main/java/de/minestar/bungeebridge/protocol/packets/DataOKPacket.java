package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class DataOKPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.DATA_OK;

    private String playerName;

    public DataOKPacket(String playerName) {
        super(thisPacketType);
        this.playerName = playerName;
    }

    public DataOKPacket(PacketType type) {
        super(thisPacketType);
    }

    public DataOKPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
    }

    public String getPlayerName() {
        return playerName;
    }
}

package de.minestar.library.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.library.protocol.NetworkPacket;
import de.minestar.library.protocol.PacketType;

public class WhoRequestPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.WHO_REQUEST;

    private String playerName;

    public WhoRequestPacket(String playerName) {
        super(thisPacketType);
        this.playerName = playerName;
    }

    public WhoRequestPacket(PacketType type) {
        super(thisPacketType);
    }

    public WhoRequestPacket(DataInputStream dataInputStream) throws IOException {
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

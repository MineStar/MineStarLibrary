package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class ServerchangeDenyPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.SERVERCHANGE_DENY;

    private String playerName;
    private String reason;

    public ServerchangeDenyPacket(String playerName, String reason) {
        super(thisPacketType);
        this.playerName = playerName;
        this.reason = reason;
    }

    public ServerchangeDenyPacket(PacketType type) {
        super(thisPacketType);
    }

    public ServerchangeDenyPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeUTF(this.reason);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.reason = dataInputStream.readUTF();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getReason() {
        return reason;
    }
}

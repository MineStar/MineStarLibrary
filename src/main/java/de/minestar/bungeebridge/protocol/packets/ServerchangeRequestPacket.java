package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class ServerchangeRequestPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.SERVERCHANGE_REQUEST;

    private String playerName;
    private String serverName;

    public ServerchangeRequestPacket(String playerName, String serverName) {
        super(thisPacketType);
        this.playerName = playerName;
        this.serverName = serverName;
    }

    public ServerchangeRequestPacket(PacketType type) {
        super(thisPacketType);
    }

    public ServerchangeRequestPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeUTF(this.serverName);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.serverName = dataInputStream.readUTF();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getServerName() {
        return serverName;
    }
}

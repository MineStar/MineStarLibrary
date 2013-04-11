package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class ServerchangeOKPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.SERVERCHANGE_OK;

    private String playerName;
    private String serverName;
    private String message;

    public ServerchangeOKPacket(String playerName, String serverName, String message) {
        super(thisPacketType);
        this.playerName = playerName;
        this.serverName = serverName;
        this.message = message;
    }

    public ServerchangeOKPacket(PacketType type) {
        super(thisPacketType);
    }

    public ServerchangeOKPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeUTF(this.serverName);
        dataOutputStream.writeUTF(this.message);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.serverName = dataInputStream.readUTF();
        this.message = dataInputStream.readUTF();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getMessage() {
        return message;
    }
}

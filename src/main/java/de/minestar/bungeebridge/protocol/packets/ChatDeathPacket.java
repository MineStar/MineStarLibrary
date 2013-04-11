package de.minestar.bungeebridge.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.bungeebridge.protocol.NetworkPacket;
import de.minestar.bungeebridge.protocol.PacketType;

public class ChatDeathPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.CHAT_DEATH;

    private String serverName;
    private String message;

    public ChatDeathPacket(String serverName, String playerName) {
        super(thisPacketType);
        this.serverName = serverName;
        this.message = playerName;
    }

    public ChatDeathPacket(PacketType type) {
        super(thisPacketType);
    }

    public ChatDeathPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.serverName);
        dataOutputStream.writeUTF(this.message);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.serverName = dataInputStream.readUTF();
        this.message = dataInputStream.readUTF();
    }

    public String getServerName() {
        return serverName;
    }

    public String getMessage() {
        return message;
    }
}

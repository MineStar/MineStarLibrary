package de.minestar.minestarlibrary.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.minestarlibrary.protocol.NetworkPacket;
import de.minestar.minestarlibrary.protocol.PacketType;

public class ChatMessagePacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.CHAT_MESSAGE;

    private String serverName;
    private String message;

    public ChatMessagePacket(String serverName, String playerName) {
        super(thisPacketType);
        this.serverName = serverName;
        this.message = playerName;
    }

    public ChatMessagePacket(PacketType type) {
        super(thisPacketType);
    }

    public ChatMessagePacket(DataInputStream dataInputStream) throws IOException {
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

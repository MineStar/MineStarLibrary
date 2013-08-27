package de.minestar.library.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.library.protocol.NetworkPacket;
import de.minestar.library.protocol.PacketType;

public class ChatDeathPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.CHAT_DEATH;

    private String serverName;
    private String message;

    public ChatDeathPacket(String serverName, String message) {
        super(thisPacketType);
        this.serverName = serverName;
        this.message = message;
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

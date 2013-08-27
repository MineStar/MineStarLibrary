package de.minestar.library.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.library.protocol.NetworkPacket;
import de.minestar.library.protocol.PacketType;

public class WhoAnswerPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.WHO_ANSWER;

    private String playerName;
    private int msgCount;
    private String[] messages;

    public WhoAnswerPacket(String playerName, String[] messages) {
        super(thisPacketType);
        this.playerName = playerName;
        this.msgCount = messages.length;
        this.messages = messages;
    }

    public WhoAnswerPacket(PacketType type) {
        super(thisPacketType);
    }

    public WhoAnswerPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeInt(this.msgCount);
        for (String message : this.messages) {
            dataOutputStream.writeUTF(message);
        }
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.msgCount = dataInputStream.readInt();
        String[] messages = new String[this.msgCount];
        for (int i = 0; i < this.msgCount; i++) {
            messages[i] = dataInputStream.readUTF();
        }
        this.messages = messages;
    }

    public String getPlayerName() {
        return playerName;
    }
}

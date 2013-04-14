package de.minestar.minestarlibrary.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.minestarlibrary.protocol.NetworkPacket;
import de.minestar.minestarlibrary.protocol.PacketType;

public class FreeSlotsRequestPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.FREESLOTS_REQUEST;

    private String playerName;

    public FreeSlotsRequestPacket(String playerName) {
        super(thisPacketType);
        this.playerName = playerName;
    }

    public FreeSlotsRequestPacket(PacketType type) {
        super(thisPacketType);
    }

    public FreeSlotsRequestPacket(DataInputStream dataInputStream) throws IOException {
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

package de.minestar.library.protocol.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.minestar.library.protocol.NetworkPacket;
import de.minestar.library.protocol.PacketType;

public class FreeSlotsAnswerPacket extends NetworkPacket {

    private final static PacketType thisPacketType = PacketType.FREESLOTS_ANSWER;

    private String playerName;
    private int usedSlots;

    public FreeSlotsAnswerPacket(String playerName, int usedSlots) {
        super(thisPacketType);
        this.playerName = playerName;
        this.usedSlots = usedSlots;
    }

    public FreeSlotsAnswerPacket(PacketType type) {
        super(thisPacketType);
    }

    public FreeSlotsAnswerPacket(DataInputStream dataInputStream) throws IOException {
        super(thisPacketType, dataInputStream);
    }

    public PacketType getPacketType() {
        return thisPacketType;
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.playerName);
        dataOutputStream.writeInt(this.usedSlots);
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        this.playerName = dataInputStream.readUTF();
        this.usedSlots = dataInputStream.readInt();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getUsedSlots() {
        return usedSlots;
    }
}

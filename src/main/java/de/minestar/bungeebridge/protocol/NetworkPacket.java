package de.minestar.bungeebridge.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class NetworkPacket {

    private final PacketType type;

    public NetworkPacket(PacketType type) {
        this.type = type;
    }

    public NetworkPacket(PacketType type, DataInputStream dataInputStream) throws IOException {
        this(type);
        onReceive(dataInputStream);
    }

    public final PacketType getType() {
        return type;
    }

    public final ByteArrayOutputStream pack() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            dos.writeInt(type.ordinal());
            onSend(dos);
            return bos;
        } catch (Exception e) {
            return null;
        }
    }

    public abstract void onSend(DataOutputStream dataOutputStream) throws IOException;

    public abstract void onReceive(DataInputStream dataInputStream) throws IOException;

}

package de.minestar.minestarlibrary.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiPacket extends NetworkPacket implements Iterable<NetworkPacket> {

    private List<NetworkPacket> subPackets;

    public MultiPacket() {
        super(PacketType.MULTIPACKET);
        subPackets = new ArrayList<NetworkPacket>();
    }

    public MultiPacket addPacket(NetworkPacket packet) {
        subPackets.add(packet);
        return this;
    }

    public void clean() {
        subPackets.clear();
    }

    public Iterator<NetworkPacket> iterator() {
        return subPackets.iterator();
    }

    @Override
    public void onSend(DataOutputStream dataOutputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onReceive(DataInputStream dataInputStream) throws IOException {
        throw new UnsupportedOperationException();
    }
}

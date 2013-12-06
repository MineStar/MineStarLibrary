package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase {
    public byte data;

    public NBTTagByte(String paramString) {
        super(paramString);
    }

    public NBTTagByte(String paramString, byte paramByte) {
        super(paramString);
        this.data = paramByte;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeByte(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readByte();
    }

    public byte getTypeId() {
        return 1;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagByte(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagByte localNBTTagByte = (NBTTagByte) paramObject;
            return this.data == localNBTTagByte.data;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagByte(data);
    }
}

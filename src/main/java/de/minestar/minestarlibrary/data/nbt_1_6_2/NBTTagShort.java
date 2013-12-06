package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTBase {
    public short data;

    public NBTTagShort(String paramString) {
        super(paramString);
    }

    public NBTTagShort(String paramString, short paramShort) {
        super(paramString);
        this.data = paramShort;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeShort(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readShort();
    }

    public byte getTypeId() {
        return 2;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagShort(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagShort localNBTTagShort = (NBTTagShort) paramObject;
            return this.data == localNBTTagShort.data;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagShort(data);
    }
}
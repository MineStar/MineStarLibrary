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
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTTagByte JD-Core Version: 0.6.0
 */
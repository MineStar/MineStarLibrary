package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTBase {
    public int data;

    public NBTTagInt(String paramString) {
        super(paramString);
    }

    public NBTTagInt(String paramString, int paramInt) {
        super(paramString);
        this.data = paramInt;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeInt(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readInt();
    }

    public byte getTypeId() {
        return 3;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagInt(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagInt localNBTTagInt = (NBTTagInt) paramObject;
            return this.data == localNBTTagInt.data;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTTagInt JD-Core Version: 0.6.0
 */
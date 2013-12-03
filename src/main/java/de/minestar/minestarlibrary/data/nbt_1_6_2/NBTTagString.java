package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagString extends NBTBase {
    public String data;

    public NBTTagString(String paramString) {
        super(paramString);
    }

    public NBTTagString(String paramString1, String paramString2) {
        super(paramString1);
        this.data = paramString2;
        if (paramString2 == null)
            throw new IllegalArgumentException("Empty string not allowed");
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeUTF(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readUTF();
    }

    public byte getTypeId() {
        return 8;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagString(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagString localNBTTagString = (NBTTagString) paramObject;
            return ((this.data == null) && (localNBTTagString.data == null)) || ((this.data != null) && (this.data.equals(localNBTTagString.data)));
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.data.hashCode();
    }
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTTagString JD-Core Version: 0.6.0
 */
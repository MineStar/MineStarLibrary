package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTBase {
    public float data;

    public NBTTagFloat(String paramString) {
        super(paramString);
    }

    public NBTTagFloat(String paramString, float paramFloat) {
        super(paramString);
        this.data = paramFloat;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeFloat(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readFloat();
    }

    public byte getTypeId() {
        return 5;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagFloat(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagFloat localNBTTagFloat = (NBTTagFloat) paramObject;
            return this.data == localNBTTagFloat.data;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ Float.floatToIntBits(this.data);
    }
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTTagFloat JD-Core Version: 0.6.0
 */
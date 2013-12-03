package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTBase {
    public double data;

    public NBTTagDouble(String paramString) {
        super(paramString);
    }

    public NBTTagDouble(String paramString, double paramDouble) {
        super(paramString);
        this.data = paramDouble;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeDouble(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        this.data = paramDataInput.readDouble();
    }

    public byte getTypeId() {
        return 6;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagDouble(getName(), this.data);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagDouble localNBTTagDouble = (NBTTagDouble) paramObject;
            return this.data == localNBTTagDouble.data;
        }
        return false;
    }

    public int hashCode() {
        long l = Double.doubleToLongBits(this.data);
        return super.hashCode() ^ (int) (l ^ l >>> 32);
    }
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTTagDouble JD-Core Version: 0.6.0
 */
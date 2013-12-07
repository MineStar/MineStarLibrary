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

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagDouble(data);
    }

    @Override
    public NBTBase fromNative(net.minecraft.server.v1_7_R1.NBTBase base) {
        if (base instanceof net.minecraft.server.v1_7_R1.NBTTagDouble) {
            try {
                net.minecraft.server.v1_7_R1.NBTTagDouble tag = (net.minecraft.server.v1_7_R1.NBTTagDouble) base;
                return new NBTTagDouble("", tag.g());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
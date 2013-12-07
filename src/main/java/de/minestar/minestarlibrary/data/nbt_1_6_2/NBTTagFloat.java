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

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagFloat(data);
    }

    @Override
    public NBTBase fromNative(net.minecraft.server.v1_7_R1.NBTBase base) {
        if (base instanceof net.minecraft.server.v1_7_R1.NBTTagFloat) {
            try {
                net.minecraft.server.v1_7_R1.NBTTagFloat tag = (net.minecraft.server.v1_7_R1.NBTTagFloat) base;
                return new NBTTagFloat("", tag.h());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
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

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagInt(data);
    }

    @Override
    public NBTBase fromNative(net.minecraft.server.v1_7_R1.NBTBase base) {
        if (base instanceof net.minecraft.server.v1_7_R1.NBTTagInt) {
            try {
                net.minecraft.server.v1_7_R1.NBTTagInt tag = (net.minecraft.server.v1_7_R1.NBTTagInt) base;
                return new NBTTagInt("", tag.d());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}

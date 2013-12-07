package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Field;

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

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagString(data);
    }

    @Override
    public NBTBase fromNative(net.minecraft.server.v1_7_R1.NBTBase base) {
        if (base instanceof net.minecraft.server.v1_7_R1.NBTTagString) {
            try {
                net.minecraft.server.v1_7_R1.NBTTagString tag = (net.minecraft.server.v1_7_R1.NBTTagString) base;
                NBTTagString newTag = new NBTTagString("");
                Field field = tag.getClass().getDeclaredField("data");
                field.setAccessible(true);
                String data = (String) (field.get(tag));
                newTag.data = data;
                return newTag;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
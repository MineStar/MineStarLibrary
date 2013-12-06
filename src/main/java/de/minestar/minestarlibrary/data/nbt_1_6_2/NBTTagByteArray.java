package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagByteArray extends NBTBase {
    public byte[] data;

    public NBTTagByteArray(String paramString) {
        super(paramString);
    }

    public NBTTagByteArray(String paramString, byte[] paramArrayOfByte) {
        super(paramString);
        this.data = paramArrayOfByte;
    }

    void write(DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeInt(this.data.length);
        paramDataOutput.write(this.data);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        int i = paramDataInput.readInt();
        this.data = new byte[i];
        paramDataInput.readFully(this.data);
    }

    public byte getTypeId() {
        return 7;
    }

    public String toString() {
        return "[" + this.data.length + " bytes]";
    }

    public NBTBase clone() {
        byte[] arrayOfByte = new byte[this.data.length];
        System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
        return new NBTTagByteArray(getName(), arrayOfByte);
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            return Arrays.equals(this.data, ((NBTTagByteArray) paramObject).data);
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        return new net.minecraft.server.v1_7_R1.NBTTagByteArray(data);
    }
}
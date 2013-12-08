package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase {
    public NBTTagEnd() {
        super(null);
    }

    void load(DataInput paramDataInput, int paramInt) {
    }

    void write(DataOutput paramDataOutput) {
    }

    public byte getTypeId() {
        return 0;
    }

    public String toString() {
        return "END";
    }

    public NBTBase clone() {
        return new NBTTagEnd();
    }
}
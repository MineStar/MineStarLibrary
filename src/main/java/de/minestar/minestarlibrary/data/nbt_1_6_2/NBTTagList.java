package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NBTTagList extends NBTBase {
    private List<NBTBase> list = new ArrayList<NBTBase>();
    private byte type;

    public NBTTagList() {
        super("");
    }

    public NBTTagList(String paramString) {
        super(paramString);
    }

    void write(DataOutput paramDataOutput) throws IOException {
        if (!this.list.isEmpty())
            this.type = ((NBTBase) this.list.get(0)).getTypeId();
        else {
            this.type = 1;
        }
        paramDataOutput.writeByte(this.type);
        paramDataOutput.writeInt(this.list.size());
        for (int i = 0; i < this.list.size(); i++)
            ((NBTBase) this.list.get(i)).write(paramDataOutput);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        if (paramInt > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        }
        this.type = paramDataInput.readByte();
        int i = paramDataInput.readInt();

        this.list = new ArrayList<NBTBase>();
        for (int j = 0; j < i; j++) {
            NBTBase localNBTBase = NBTBase.createTag(this.type, null);
            localNBTBase.load(paramDataInput, paramInt + 1);
            this.list.add(localNBTBase);
        }
    }

    public byte getTypeId() {
        return 9;
    }

    public String toString() {
        return "" + this.list.size() + " entries of type " + NBTBase.getTagName(this.type);
    }

    public void add(NBTBase paramNBTBase) {
        this.type = paramNBTBase.getTypeId();
        this.list.add(paramNBTBase);
    }

    public NBTBase get(int paramInt) {
        return (NBTBase) this.list.get(paramInt);
    }

    public int size() {
        return this.list.size();
    }

    public NBTBase clone() {
        NBTTagList localNBTTagList = new NBTTagList(getName());
        localNBTTagList.type = this.type;
        for (NBTBase localNBTBase1 : this.list) {
            NBTBase localNBTBase2 = localNBTBase1.clone();
            localNBTTagList.list.add(localNBTBase2);
        }
        return localNBTTagList;
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagList localNBTTagList = (NBTTagList) paramObject;
            if (this.type == localNBTTagList.type) {
                return this.list.equals(localNBTTagList.list);
            }
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.list.hashCode();
    }

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        net.minecraft.server.v1_7_R1.NBTTagList list = new net.minecraft.server.v1_7_R1.NBTTagList();
        for (NBTBase base : this.list) {
            list.add(base.toNative());
        }
        return list;
    }
}
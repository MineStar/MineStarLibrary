package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_7_R1.CrashReport;
import net.minecraft.server.v1_7_R1.CrashReportSystemDetails;
import net.minecraft.server.v1_7_R1.ReportedException;

public class NBTTagCompound extends NBTBase {
    private Map<String, NBTBase> map = new HashMap<String, NBTBase>();

    public NBTTagCompound() {
        super("");
    }

    public NBTTagCompound(String paramString) {
        super(paramString);
    }

    void write(DataOutput paramDataOutput) throws IOException {
        for (NBTBase localNBTBase : this.map.values()) {
            NBTBase.a(localNBTBase, paramDataOutput);
        }
        paramDataOutput.writeByte(0);
    }

    void load(DataInput paramDataInput, int paramInt) throws IOException {
        if (paramInt > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        }
        this.map.clear();
        NBTBase localNBTBase;
        while ((localNBTBase = NBTBase.b(paramDataInput, paramInt + 1)).getTypeId() != 0)
            this.map.put(localNBTBase.getName(), localNBTBase);
    }

    public Collection<NBTBase> c() {
        return this.map.values();
    }

    public byte getTypeId() {
        return 10;
    }

    public void set(String paramString, NBTBase paramNBTBase) {
        this.map.put(paramString, paramNBTBase.setName(paramString));
    }

    public void setByte(String paramString, byte paramByte) {
        this.map.put(paramString, new NBTTagByte(paramString, paramByte));
    }

    public void setShort(String paramString, short paramShort) {
        this.map.put(paramString, new NBTTagShort(paramString, paramShort));
    }

    public void setInt(String paramString, int paramInt) {
        this.map.put(paramString, new NBTTagInt(paramString, paramInt));
    }

    public void setLong(String paramString, long paramLong) {
        this.map.put(paramString, new NBTTagLong(paramString, paramLong));
    }

    public void setFloat(String paramString, float paramFloat) {
        this.map.put(paramString, new NBTTagFloat(paramString, paramFloat));
    }

    public void setDouble(String paramString, double paramDouble) {
        this.map.put(paramString, new NBTTagDouble(paramString, paramDouble));
    }

    public void setString(String paramString1, String paramString2) {
        this.map.put(paramString1, new NBTTagString(paramString1, paramString2));
    }

    public void setByteArray(String paramString, byte[] paramArrayOfByte) {
        this.map.put(paramString, new NBTTagByteArray(paramString, paramArrayOfByte));
    }

    public void setIntArray(String paramString, int[] paramArrayOfInt) {
        this.map.put(paramString, new NBTTagIntArray(paramString, paramArrayOfInt));
    }

    public void setCompound(String paramString, NBTTagCompound paramNBTTagCompound) {
        this.map.put(paramString, paramNBTTagCompound.setName(paramString));
    }

    public void setBoolean(String paramString, boolean paramBoolean) {
        setByte(paramString, (byte) (paramBoolean ? 1 : 0));
    }

    public NBTBase get(String paramString) {
        return (NBTBase) this.map.get(paramString);
    }

    public boolean hasKey(String paramString) {
        return this.map.containsKey(paramString);
    }

    public byte getByte(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0;
            return ((NBTTagByte) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 1, localClassCastException));
        }
    }

    public short getShort(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0;
            return ((NBTTagShort) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 2, localClassCastException));
        }
    }

    public int getInt(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0;
            return ((NBTTagInt) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 3, localClassCastException));
        }
    }

    public long getLong(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0L;
            return ((NBTTagLong) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 4, localClassCastException));
        }
    }

    public float getFloat(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0.0F;
            return ((NBTTagFloat) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 5, localClassCastException));
        }
    }

    public double getDouble(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return 0.0D;
            return ((NBTTagDouble) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 6, localClassCastException));
        }
    }

    public String getString(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return "";
            return ((NBTTagString) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 8, localClassCastException));
        }
    }

    public byte[] getByteArray(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return new byte[0];
            return ((NBTTagByteArray) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 7, localClassCastException));
        }
    }

    public int[] getIntArray(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return new int[0];
            return ((NBTTagIntArray) this.map.get(paramString)).data;
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 11, localClassCastException));
        }
    }

    public NBTTagCompound getCompound(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return new NBTTagCompound(paramString);
            return (NBTTagCompound) this.map.get(paramString);
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 10, localClassCastException));
        }
    }

    public NBTTagList getList(String paramString) {
        try {
            if (!this.map.containsKey(paramString))
                return new NBTTagList(paramString);
            return (NBTTagList) this.map.get(paramString);
        } catch (ClassCastException localClassCastException) {
            throw new ReportedException(a(paramString, 9, localClassCastException));
        }
    }

    public boolean getBoolean(String paramString) {
        return getByte(paramString) != 0;
    }

    public void remove(String paramString) {
        this.map.remove(paramString);
    }

    public String toString() {
        String str1 = getName() + ":[";
        for (String str2 : this.map.keySet()) {
            str1 = str1 + str2 + ":" + this.map.get(str2) + ",";
        }
        return str1 + "]";
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    private CrashReport a(String paramString, int paramInt, ClassCastException paramClassCastException) {
        CrashReport localCrashReport = CrashReport.a(paramClassCastException, "Reading NBT data");
        CrashReportSystemDetails localCrashReportSystemDetails = localCrashReport.a("Corrupt NBT tag", 1);

//        localCrashReportSystemDetails.a("Tag type found", new CrashReportCorruptNBTTag(this, paramString));

//        localCrashReportSystemDetails.a("Tag type expected", new CrashReportCorruptNBTTag2(this, paramInt));

        localCrashReportSystemDetails.a("Tag name", paramString);
        if ((getName() != null) && (getName().length() > 0))
            localCrashReportSystemDetails.a("Tag parent", getName());

        return localCrashReport;
    }

    public NBTBase clone() {
        NBTTagCompound localNBTTagCompound = new NBTTagCompound(getName());
        for (String str : this.map.keySet()) {
            localNBTTagCompound.set(str, ((NBTBase) this.map.get(str)).clone());
        }
        return localNBTTagCompound;
    }

    public boolean equals(Object paramObject) {
        if (super.equals(paramObject)) {
            NBTTagCompound localNBTTagCompound = (NBTTagCompound) paramObject;
            return this.map.entrySet().equals(localNBTTagCompound.map.entrySet());
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.map.hashCode();
    }

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        net.minecraft.server.v1_7_R1.NBTTagCompound compound = new net.minecraft.server.v1_7_R1.NBTTagCompound();
        for (Map.Entry<String, NBTBase> entry : this.map.entrySet()) {
            compound.set(entry.getKey(), entry.getValue().toNative());
        }
        return compound;
    }

    @Override
    public NBTBase fromNative(net.minecraft.server.v1_7_R1.NBTBase base) {
        if (base instanceof net.minecraft.server.v1_7_R1.NBTTagCompound) {
            try {
                net.minecraft.server.v1_7_R1.NBTTagCompound tag = (net.minecraft.server.v1_7_R1.NBTTagCompound) base;
                NBTTagCompound newTag = new NBTTagCompound("");
                Field field = tag.getClass().getDeclaredField("map");
                field.setAccessible(true);

                Map<String, NBTBase> newData = new HashMap<String, NBTBase>();
                Map<String, net.minecraft.server.v1_7_R1.NBTBase> oldData = (HashMap<String, net.minecraft.server.v1_7_R1.NBTBase>) (field.get(tag));
                for (Map.Entry<String, net.minecraft.server.v1_7_R1.NBTBase> baseData : oldData.entrySet()) {
                    NBTBase newTagBase = NBTBase.convertFromNative(baseData.getValue());
                    if (newTagBase != null) {
                        newData.put(baseData.getKey(), newTagBase);
                    }
                }
                newTag.map = newData;
                return newTag;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}

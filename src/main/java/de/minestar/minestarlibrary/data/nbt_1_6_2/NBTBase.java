package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.v1_7_R1.CrashReport;
import net.minecraft.server.v1_7_R1.CrashReportSystemDetails;
import net.minecraft.server.v1_7_R1.ReportedException;

public abstract class NBTBase {
    public static final String[] b = {"END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"};
    private String name;

    abstract void write(DataOutput paramDataOutput) throws IOException;

    abstract void load(DataInput paramDataInput, int paramInt) throws IOException;

    public abstract byte getTypeId();

    protected NBTBase(String paramString) {
        if (paramString == null)
            this.name = "";
        else
            this.name = paramString;
    }

    public NBTBase setName(String paramString) {
        if (paramString == null)
            this.name = "";
        else {
            this.name = paramString;
        }
        return this;
    }

    public String getName() {
        if (this.name == null)
            return "";
        return this.name;
    }

    public static NBTBase a(DataInput paramDataInput) throws IOException {
        return b(paramDataInput, 0);
    }

    public static NBTBase b(DataInput paramDataInput, int paramInt) throws IOException {
        byte b1 = paramDataInput.readByte();
        if (b1 == 0)
            return new NBTTagEnd();

        String str = paramDataInput.readUTF();

        NBTBase localNBTBase = createTag(b1, str);
        try {
            localNBTBase.load(paramDataInput, paramInt);
        } catch (Exception localIOException) {
            CrashReport localCrashReport = CrashReport.a(localIOException, "Loading NBT data");
            CrashReportSystemDetails localCrashReportSystemDetails = localCrashReport.a("NBT Tag");
            localCrashReportSystemDetails.a("Tag name", str);
            localCrashReportSystemDetails.a("Tag type", Byte.valueOf(b1));
            throw new ReportedException(localCrashReport);
        }

        return localNBTBase;
    }

    public static void a(NBTBase paramNBTBase, DataOutput paramDataOutput) throws IOException {
        paramDataOutput.writeByte(paramNBTBase.getTypeId());
        if (paramNBTBase.getTypeId() == 0)
            return;

        paramDataOutput.writeUTF(paramNBTBase.getName());

        paramNBTBase.write(paramDataOutput);
    }

    public static NBTBase createTag(byte paramByte, String paramString) {
        switch (paramByte) {
            case 0 :
                return new NBTTagEnd();
            case 1 :
                return new NBTTagByte(paramString);
            case 2 :
                return new NBTTagShort(paramString);
            case 3 :
                return new NBTTagInt(paramString);
            case 4 :
                return new NBTTagLong(paramString);
            case 5 :
                return new NBTTagFloat(paramString);
            case 6 :
                return new NBTTagDouble(paramString);
            case 7 :
                return new NBTTagByteArray(paramString);
            case 11 :
                return new NBTTagIntArray(paramString);
            case 8 :
                return new NBTTagString(paramString);
            case 9 :
                return new NBTTagList(paramString);
            case 10 :
                return new NBTTagCompound(paramString);
        }
        return null;
    }

    public static String getTagName(byte paramByte) {
        switch (paramByte) {
            case 0 :
                return "TAG_End";
            case 1 :
                return "TAG_Byte";
            case 2 :
                return "TAG_Short";
            case 3 :
                return "TAG_Int";
            case 4 :
                return "TAG_Long";
            case 5 :
                return "TAG_Float";
            case 6 :
                return "TAG_Double";
            case 7 :
                return "TAG_Byte_Array";
            case 11 :
                return "TAG_Int_Array";
            case 8 :
                return "TAG_String";
            case 9 :
                return "TAG_List";
            case 10 :
                return "TAG_Compound";
        }
        return "UNKNOWN";
    }

    public abstract NBTBase clone();

    public boolean equals(Object paramObject) {
        if (!(paramObject instanceof NBTBase)) {
            return false;
        }
        NBTBase localNBTBase = (NBTBase) paramObject;
        if (getTypeId() != localNBTBase.getTypeId()) {
            return false;
        }
        if (((this.name == null) && (localNBTBase.name != null)) || ((this.name != null) && (localNBTBase.name == null))) {
            return false;
        }

        return (this.name == null) || (this.name.equals(localNBTBase.name));
    }

    public int hashCode() {
        return this.name.hashCode() ^ getTypeId();
    }
}

/*
 * Location: C:\Users\GeMo\Desktop\spigot-1.6.2-R0.2-SNAPSHOT_1094.jar Qualified
 * Name: net.minecraft.server.v1_6_R2.NBTBase JD-Core Version: 0.6.0
 */
package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBTCompressedStreamTools {
    public static NBTTagCompound a(InputStream inputstream) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = a((DataInput) datainputstream);
        } finally {
            datainputstream.close();
        }

        return nbttagcompound;
    }

    public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(outputstream));

        try {
            a(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            dataoutputstream.close();
        }
    }

    public static NBTTagCompound a(byte[] abyte) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte))));

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = a((DataInput) datainputstream);
        } finally {
            datainputstream.close();
        }

        return nbttagcompound;
    }

    public static byte[] a(NBTTagCompound nbttagcompound) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));

        try {
            a(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            dataoutputstream.close();
        }

        return bytearrayoutputstream.toByteArray();
    }

    public static NBTTagCompound a(DataInput datainput) throws IOException {
        NBTBase nbtbase = NBTBase.a(datainput);

        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound) nbtbase;
        } else {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
        NBTBase.a(nbttagcompound, dataoutput);
    }
}
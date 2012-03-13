package de.minestar.minestarlibrary.data.tools;

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

import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;

public class CompressedStreamTools {

    public static NBTTagCompound loadGzippedCompoundFromOutputStream(InputStream input) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(input)));
        try {
            NBTTagCompound nbttagcompound = read(datainputstream);
            return nbttagcompound;
        } finally {
            datainputstream.close();
        }
    }

    public static void writeGzippedCompoundToOutputStream(NBTTagCompound nbttag, OutputStream output) throws IOException {
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(output));
        try {
            writeTo(nbttag, dataoutputstream);
        } finally {
            dataoutputstream.close();
        }
    }

    public static NBTTagCompound loadMapFromByteArray(byte byteArray[]) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(byteArray))));
        try {
            NBTTagCompound nbttagcompound = read(datainputstream);
            return nbttagcompound;
        } finally {
            datainputstream.close();
        }
    }

    public static byte[] writeMapToByteArray(NBTTagCompound nbttag) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));
        try {
            writeTo(nbttag, dataoutputstream);
        } finally {
            dataoutputstream.close();
        }
        return bytearrayoutputstream.toByteArray();
    }

    public static NBTTagCompound read(DataInput input) throws IOException {
        NBTBase nbtbase = NBTBase.b(input);
        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound) nbtbase;
        } else {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void writeTo(NBTTagCompound nbttag, DataOutput output) throws IOException {
        NBTBase.a(nbttag, output);
    }
}

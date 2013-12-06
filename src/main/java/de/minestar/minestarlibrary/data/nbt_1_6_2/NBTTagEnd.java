package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    @Override
    public net.minecraft.server.v1_7_R1.NBTBase toNative() {
        try {
            Constructor<net.minecraft.server.v1_7_R1.NBTTagEnd> constructor = net.minecraft.server.v1_7_R1.NBTTagEnd.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            try {
                return (net.minecraft.server.v1_7_R1.NBTBase) constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
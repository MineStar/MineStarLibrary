/*
 * Copyright (C) 2013 MineStar.de 
 * 
 * This file is part of MineStarLibrary.
 * 
 * MineStarLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MineStarLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MineStarLibrary.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.data.nbt_1_6_2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Random;

public class NBTConverter {

    public static final Random random = new Random();
    public static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String getRandomFileName() {
        return getRandomFileName(12);
    }

    private static String getRandomFileName(int length) {
        String fileName = "";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            fileName += alphabet.charAt(index);
        }
        fileName += ".tmp";
        return fileName;
    }

    private static boolean writeNativeNBT(DataOutput output, net.minecraft.server.v1_7_R2.NBTBase base) {
        try {
            Method method = base.getClass().getDeclaredMethod("write", DataOutput.class);
            method.setAccessible(true);
            method.invoke(base, output);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean readNativeNBT(DataInput input, net.minecraft.server.v1_7_R2.NBTBase base) {
        try {
            Method method = base.getClass().getDeclaredMethod("load", DataInput.class, int.class);
            method.setAccessible(true);
            method.invoke(base, input, 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean writeNBT(DataOutput output, NBTBase base) {
        try {
            base.write(output);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean readNBT(DataInput input, NBTBase base) {
        try {
            base.load(input, 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static net.minecraft.server.v1_7_R2.NBTBase createNativeTag(byte typeID) {
        try {
            Method method = net.minecraft.server.v1_7_R2.NBTBase.class.getDeclaredMethod("createTag", byte.class);
            method.setAccessible(true);
            net.minecraft.server.v1_7_R2.NBTBase newTag = (net.minecraft.server.v1_7_R2.NBTBase) method.invoke(null, typeID);
            return newTag;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static net.minecraft.server.v1_7_R2.NBTBase toNative(NBTBase base) {
        // create the new NBTTag
        net.minecraft.server.v1_7_R2.NBTBase newTag = createNativeTag(base.getTypeId());
        if (newTag == null) {
            return null;
        }

        // create a temporary file
        // we will write our code into it, and then read from it...
        File tempFile = new File(getRandomFileName());
        if (tempFile.exists()) {
            tempFile.delete();
        }

        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(tempFile));
            // write the native tag into the file
            if (writeNBT(outputStream, base)) {
                // close the current stream
                outputStream.close();

                // try to read the converted tag from the file
                DataInputStream inputStream = new DataInputStream(new FileInputStream(tempFile));
                readNativeNBT(inputStream, newTag);
                inputStream.close();
            } else {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete the temporary file
        if (tempFile.exists()) {
            tempFile.delete();
        }
        return newTag;
    }

    public static NBTBase fromNative(net.minecraft.server.v1_7_R2.NBTBase base) {
        // create the new NBTTag
        NBTBase newTag = NBTBase.createTag(base.getTypeId(), "");

        // create a temporary file
        // we will write our code into it, and then read from it...
        File tempFile = new File(getRandomFileName());
        if (tempFile.exists()) {
            tempFile.delete();
        }

        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(tempFile));
            // write the native tag into the file
            if (writeNativeNBT(outputStream, base)) {
                // close the current stream
                outputStream.close();

                // try to read the converted tag from the file
                DataInputStream inputStream = new DataInputStream(new FileInputStream(tempFile));
                readNBT(inputStream, newTag);
                inputStream.close();
            } else {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete the temporary file
        if (tempFile.exists()) {
            tempFile.delete();
        }
        return newTag;
    }
}

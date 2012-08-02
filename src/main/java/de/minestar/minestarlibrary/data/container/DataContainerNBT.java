package de.minestar.minestarlibrary.data.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;

import org.bukkit.Location;

import com.bukkit.gemo.utils.BlockUtils;

import de.minestar.minestarlibrary.data.GenericValue;
import de.minestar.minestarlibrary.data.tools.CompressedStreamTools;

public class DataContainerNBT extends DataContainerNone {

    private NBTTagCompound NBTTag;

    public DataContainerNBT() {
        super();
    }

    @Override
    public void load(File file) {
        if (file != null && file.exists()) {
            try {
                // OPEN STREAM
                FileInputStream FIS = new FileInputStream(file);
                this.NBTTag = CompressedStreamTools.loadGzippedCompoundFromOutputStream(FIS);

                // LOAD DATA
                if (this.NBTTag != null) {
                    this.loadBoolean();
                    this.loadByte();
                    this.loadByteArray();
                    this.loadDouble();
                    this.loadFloat();
                    this.loadInteger();
                    // TODO: ITEMSTACKS
//                    this.loadItemStack();
//                    this.loadItemStackArray();
                    this.loadLocation();
                    this.loadLong();
                    this.loadShort();
                    this.loadString();
                }

                // CLOSE STREAM
                FIS.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void save(File file) {
        try {
            // DELETE OLD FILE
            if (file.exists()) {
                file.delete();
            }

            // CREATE FILE
            FileOutputStream FOS = new FileOutputStream(file);
            NBTTagCompound rootTag = new NBTTagCompound();

            // INIT VARS
            ConcurrentHashMap<String, GenericValue> currentMap;
            NBTTagCompound thisTag;
            String name;

            // SAVE BOOLEAN
            currentMap = this.getMap(Boolean.class);
            thisTag = new NBTTagCompound();
            name = "BOOLEAN";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setBoolean(entry.getKey(), (Boolean) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE BYTE
            currentMap = this.getMap(Byte.class);
            thisTag = new NBTTagCompound();
            name = "BYTE";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setByte(entry.getKey(), (Byte) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE BYTEARRAY
            currentMap = this.getMap(Byte[].class);
            thisTag = new NBTTagCompound();
            name = "BYTEARRAY";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setByteArray(entry.getKey(), (byte[]) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE DOUBLE
            currentMap = this.getMap(Double.class);
            thisTag = new NBTTagCompound();
            name = "DOUBLE";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setDouble(entry.getKey(), (Double) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE FLOAT
            currentMap = this.getMap(Float.class);
            thisTag = new NBTTagCompound();
            name = "FLOAT";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setFloat(entry.getKey(), (Float) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE INTEGER
            currentMap = this.getMap(Integer.class);
            thisTag = new NBTTagCompound();
            name = "INTEGER";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setInt(entry.getKey(), (Integer) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE LONG
            currentMap = this.getMap(Long.class);
            thisTag = new NBTTagCompound();
            name = "LONG";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setLong(entry.getKey(), (Long) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE SHORT
            currentMap = this.getMap(Short.class);
            thisTag = new NBTTagCompound();
            name = "SHORT";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setDouble(entry.getKey(), (Short) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE STRING
            currentMap = this.getMap(String.class);
            thisTag = new NBTTagCompound();
            name = "STRING";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setString(entry.getKey(), (String) entry.getValue().getValue());
            }
            rootTag.setCompound(name, thisTag);

            // SAVE LOCATION
            currentMap = this.getMap(Location.class);
            thisTag = new NBTTagCompound();
            name = "LOCATION";
            for (Map.Entry<String, GenericValue> entry : currentMap.entrySet()) {
                thisTag.setString(entry.getKey(), BlockUtils.LocationToString((Location) entry.getValue().getValue()));
            }
            rootTag.setCompound(name, thisTag);

            // TODO: ITEMSTACKS

            // GZIP THE TEMP-FILE
            CompressedStreamTools.writeGzippedCompoundToOutputStream(rootTag, FOS);

            // CLOSE STREAM
            FOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBoolean() {
        String name = "BOOLEAN";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getBoolean(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadByte() {
        String name = "BYTE";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getByte(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadByteArray() {
        String name = "BYTEARRAY";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getByteArray(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadDouble() {
        String name = "DOUBLE";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getDouble(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadFloat() {
        String name = "FLOAT";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getFloat(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadInteger() {
        String name = "INTEGER";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getInt(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadLong() {
        String name = "LONG";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getLong(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadShort() {
        String name = "SHORT";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getShort(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadString() {
        String name = "STRING";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), thisCompound.getString(thisTag.getName()));
                }
            }
        }
    }

    @Override
    public void loadLocation() {
        String name = "LOCATION";
        if (this.NBTTag.hasKey(name)) {
            NBTTagCompound thisCompound = NBTTag.getCompound(name);
            for (Object base : thisCompound.c()) {
                if (base instanceof NBTBase) {
                    NBTBase thisTag = (NBTBase) base;
                    this.setValue(thisTag.getName(), BlockUtils.LocationFromString(thisCompound.getString(thisTag.getName())));
                }
            }
        }
    }
}

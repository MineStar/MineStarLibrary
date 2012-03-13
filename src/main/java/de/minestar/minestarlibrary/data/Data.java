package de.minestar.minestarlibrary.data;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import de.minestar.minestarlibrary.data.container.DataContainerNBT;
import de.minestar.minestarlibrary.data.container.DataContainerNone;
import de.minestar.minestarlibrary.data.container.IDataContainer;

public class Data {

    // VARS
    private IDataContainer dataContainer;
    private File file;

    public Data() {
        this.dataContainer = new DataContainerNone();
    }

    public Data(String fileName, DataType type) {
        this(new File("/"), fileName, type);
    }

    public Data(File dataFolder, String fileName, DataType type) {
        this.file = new File(dataFolder, fileName + type.getEnding());
        switch (type) {
            case NBT : {
                this.dataContainer = new DataContainerNBT();
                break;
            }
            default : {
                this.dataContainer = new DataContainerNone();
                break;
            }
        }
    }

    /**
     * This method will save the file
     */
    public void save() {
        if (this.dataContainer != null) {
            this.dataContainer.save(this.file);
        } else {
            throw new RuntimeException("Error while saving Data: DataLoader is NULL!");
        }
    }

    /**
     * This method will load all the data from the file
     */
    public void load() {
        if (this.dataContainer != null) {
            this.dataContainer.load(this.file);
        } else {
            throw new RuntimeException("Error while loading Data: DataLoader is NULL!");
        }
    }

    // ///////////////////////////////////////////////
    //
    // SET VALUES
    //
    // ///////////////////////////////////////////////

    public void setValue(String key, Object value) {
        this.dataContainer.setValue(key, value);
    }

    public <T> GenericValue<T> getValue(String key, Class<T> clazz) {
        return this.dataContainer.getValue(key, clazz);
    }

    public void setBoolean(String key, boolean value) {
        this.setValue(key, value);
    }

    public void setByte(String key, byte value) {
        this.setValue(key, value);
    }

    public void setByteArray(String key, byte[] value) {
        this.setValue(key, value);
    }

    public void setDouble(String key, double value) {
        this.setValue(key, value);
    }

    public void setFloat(String key, float value) {
        this.setValue(key, value);
    }

    public void setInteger(String key, int value) {
        this.setValue(key, value);
    }

    public void setLong(String key, long value) {
        this.setValue(key, value);
    }

    public void setShort(String key, short value) {
        this.setValue(key, value);
    }

    public void setString(String key, String value) {
        this.setValue(key, value);
    }

    public void setLocation(String key, Location value) {
        this.setValue(key, value);
    }

    public void setItemStack(String key, ItemStack value) {
        this.setValue(key, value);
    }

    public void setItemStackArray(String key, ItemStack[] value) {
        this.setValue(key, value);
    }

    // ///////////////////////////////////////////////
    //
    // GET VALUES
    //
    // ///////////////////////////////////////////////

    public boolean getBoolean(String key) {
        GenericValue<Boolean> value = this.getValue(key, boolean.class);
        if (value == null)
            return false;
        return value.getValue();
    }

    public byte getByte(String key) {
        GenericValue<Byte> value = this.getValue(key, byte.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public byte[] getByteArray(String key) {
        GenericValue<byte[]> value = this.getValue(key, byte[].class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public double getDouble(String key) {
        GenericValue<Double> value = this.getValue(key, double.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public float getFloat(String key) {
        GenericValue<Float> value = this.getValue(key, float.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public int getInteger(String key) {
        GenericValue<Integer> value = this.getValue(key, int.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public long getLong(String key) {
        GenericValue<Long> value = this.getValue(key, long.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public short getShort(String key) {
        GenericValue<Short> value = this.getValue(key, short.class);
        if (value == null)
            return 0;
        return value.getValue();
    }

    public String getString(String key) {
        GenericValue<String> value = this.getValue(key, String.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Location getLocation(String key) {
        GenericValue<Location> value = this.getValue(key, Location.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public ItemStack getItemStack(String key) {
        GenericValue<ItemStack> value = this.getValue(key, ItemStack.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public ItemStack[] getItemStackArray(String key) {
        GenericValue<ItemStack[]> value = this.getValue(key, ItemStack[].class);
        if (value == null)
            return null;
        return value.getValue();
    }
}

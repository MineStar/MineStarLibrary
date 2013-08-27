package de.minestar.library.data;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import de.minestar.library.data.container.DataContainerNBT;
import de.minestar.library.data.container.DataContainerNone;
import de.minestar.library.data.container.IDataContainer;

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

    public void removeValue(String key, Class<?> clazz) {
        this.dataContainer.removeValue(key, clazz);
    }

    public <T> GenericValue<T> getValue(String key, Class<T> clazz) {
        return this.dataContainer.getValue(key, clazz);
    }

    public void setBoolean(String key, Boolean value) {
        this.setValue(key, value);
    }

    public void setByte(String key, Byte value) {
        this.setValue(key, value);
    }

    public void setByteArray(String key, Byte[] value) {
        this.setValue(key, value);
    }

    public void setDouble(String key, Double value) {
        this.setValue(key, value);
    }

    public void setFloat(String key, Float value) {
        this.setValue(key, value);
    }

    public void setInteger(String key, Integer value) {
        this.setValue(key, value);
    }

    public void setLong(String key, Long value) {
        this.setValue(key, value);
    }

    public void setShort(String key, Short value) {
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

    public Boolean getBoolean(String key) {
        GenericValue<Boolean> value = this.getValue(key, Boolean.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Byte getByte(String key) {
        GenericValue<Byte> value = this.getValue(key, Byte.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Byte[] getByteArray(String key) {
        GenericValue<Byte[]> value = this.getValue(key, Byte[].class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Double getDouble(String key) {
        GenericValue<Double> value = this.getValue(key, Double.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Float getFloat(String key) {
        GenericValue<Float> value = this.getValue(key, Float.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Integer getInteger(String key) {
        GenericValue<Integer> value = this.getValue(key, Integer.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Long getLong(String key) {
        GenericValue<Long> value = this.getValue(key, Long.class);
        if (value == null)
            return null;
        return value.getValue();
    }

    public Short getShort(String key) {
        GenericValue<Short> value = this.getValue(key, Short.class);
        if (value == null)
            return null;
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

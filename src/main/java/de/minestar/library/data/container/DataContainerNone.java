package de.minestar.library.data.container;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import de.minestar.library.data.GenericValue;

@SuppressWarnings("rawtypes")
public class DataContainerNone implements IDataContainer {

    private ConcurrentHashMap<String, ConcurrentHashMap<String, GenericValue>> valueMap;

    public DataContainerNone() {
        this.initVars();
    }

    /**
     * This method will initialize all needed var-fields
     */
    private void initVars() {
        // INIT THE MAP
        this.valueMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, GenericValue>>();

        // INIT ALL OBJECTS THAT ARE CURRENTLY USED
        // TODO: WE WANT TO MAKE THIS AUTOMATICLY
        this.initObject(Boolean.class);
        this.initObject(Byte.class);
        this.initObject(Byte[].class);
        this.initObject(Double.class);
        this.initObject(Float.class);
        this.initObject(Integer.class);
        this.initObject(Long.class);
        this.initObject(Short.class);
        this.initObject(String.class);
        this.initObject(Location.class);
        this.initObject(ItemStack.class);
        this.initObject(ItemStack[].class);
    }

    private void initObject(Class clazz) {
        this.valueMap.put(clazz.getName(), new ConcurrentHashMap<String, GenericValue>());
    }

    public ConcurrentHashMap<String, GenericValue> getMap(Class clazz) {
        return this.valueMap.get(clazz.getName());
    }

    public void setValue(String key, Object value) {
        ConcurrentHashMap<String, GenericValue> thisValues = this.valueMap.get(value.getClass().getName());
        if (thisValues == null) {
            throw new RuntimeException(value.getClass().getName() + " IS CURRENTLY NOT SUPPORTED!");
        }

        GenericValue<Object> thisV = new GenericValue<Object>(value);
        thisValues.put(key, thisV);
    }

    public void removeValue(String key, Class<?> clazz) {
        ConcurrentHashMap<String, GenericValue> thisValues = this.valueMap.get(clazz.getName());
        if (thisValues == null) {
            throw new RuntimeException(clazz.getName() + " IS CURRENTLY NOT SUPPORTED!");
        }
        thisValues.remove(key);
    }

    @SuppressWarnings("unchecked")
    public <T> GenericValue<T> getValue(String key, Class<T> clazz) {
        ConcurrentHashMap<String, GenericValue> thisValues = this.valueMap.get(clazz.getName());
        if (thisValues != null) {
            return thisValues.get(key);
        }
        return null;
    }

    @Override
    public void load(File file) {
        throw new RuntimeException("LOADING is currently not supported!");
    }

    @Override
    public void save(File file) {
        throw new RuntimeException("SAVING is currently not supported!");
    }

    @Override
    public void loadBoolean() {
        throw new RuntimeException("Boolean is currently not supported!");
    }

    @Override
    public void loadByte() {
        throw new RuntimeException("Byte is currently not supported!");
    }

    @Override
    public void loadByteArray() {
        throw new RuntimeException("Byte[] is currently not supported!");
    }

    @Override
    public void loadDouble() {
        throw new RuntimeException("Double is currently not supported!");
    }

    @Override
    public void loadFloat() {
        throw new RuntimeException("Float is currently not supported!");
    }

    @Override
    public void loadInteger() {
        throw new RuntimeException("Integer is currently not supported!");
    }

    @Override
    public void loadLong() {
        throw new RuntimeException("Long is currently not supported!");
    }

    @Override
    public void loadShort() {
        throw new RuntimeException("Short is currently not supported!");
    }

    @Override
    public void loadString() {
        throw new RuntimeException("String is currently not supported!");
    }

    @Override
    public void loadItemStack() {
        throw new RuntimeException("ItemStack is currently not supported!");
    }

    @Override
    public void loadItemStackArray() {
        throw new RuntimeException("ItemStack[] is currently not supported!");
    }

    @Override
    public void loadLocation() {
        throw new RuntimeException("Location is currently not supported!");
    }
}

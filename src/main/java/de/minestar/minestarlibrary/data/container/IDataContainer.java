package de.minestar.minestarlibrary.data.container;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import de.minestar.minestarlibrary.data.GenericValue;

public interface IDataContainer {

    public abstract void load(File file);

    public abstract void save(File file);

    public abstract void setValue(String key, Object value);

    public abstract void removeValue(String key, Class<?> clazz);

    public abstract <T> GenericValue<T> getValue(String key, Class<T> clazz);

    @SuppressWarnings("rawtypes")
    public abstract ConcurrentHashMap<String, GenericValue> getMap(Class clazz);

    // /////////////////////////////////////////
    // NATIVE DATA-TYPES
    // /////////////////////////////////////////

    public abstract void loadBoolean();

    public abstract void loadByte();

    public abstract void loadByteArray();

    public abstract void loadDouble();

    public abstract void loadFloat();

    public abstract void loadInteger();

    public abstract void loadLong();

    public abstract void loadShort();

    public abstract void loadString();

    // /////////////////////////////////////////
    // EXTENDED DATA-TYPES
    // /////////////////////////////////////////

    public abstract void loadItemStack();

    public abstract void loadItemStackArray();

    public abstract void loadLocation();

}

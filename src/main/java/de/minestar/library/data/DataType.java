package de.minestar.library.data;

public enum DataType {

    NONE("NONE", ""),

    NBT("NBT", ".dat"),

    XML("XML", ".xml"),

    YML("YML", ".yml");

    private final String name, ending;

    private DataType(String name, String ending) {
        this.name = name;
        this.ending = ending;
    }

    public String getName() {
        return this.name;
    }

    public String getEnding() {
        return this.ending;
    }
}

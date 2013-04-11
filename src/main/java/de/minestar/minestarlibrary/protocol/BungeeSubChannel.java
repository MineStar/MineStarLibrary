package de.minestar.minestarlibrary.protocol;

public enum BungeeSubChannel {

    CONNECT("Connect"),

    FORWARD("Forward");

    private final String name;

    private BungeeSubChannel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

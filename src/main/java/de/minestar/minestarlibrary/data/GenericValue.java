package de.minestar.minestarlibrary.data;

public class GenericValue<R> {
    private R value;

    public GenericValue(R value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public R getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(R value) {
        this.value = value;
    }
}

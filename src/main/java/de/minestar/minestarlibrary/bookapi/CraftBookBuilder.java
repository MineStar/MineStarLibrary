package de.minestar.minestarlibrary.bookapi;

import java.util.List;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * The implementation of BookBuilder
 */
public class CraftBookBuilder extends BookBuilder {

    static {
        // create the instance of the thing we need
        BookBuilder.instance = new CraftBookBuilder();
    }

    @Override
    public Book getBook(ItemStack itemstack, String author, String title, List<String> pages) {
        try {
            return new CraftBook((CraftItemStack) itemstack, author, title, pages);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

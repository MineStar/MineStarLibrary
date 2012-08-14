package de.minestar.minestarlibrary.bookapi;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
/**
 * A simple class to get an instance of org.bukkit.book.Book
 */
public class BookBuilder {

    protected static BookBuilder instance = null;

    /**
     * Gets the Book from an ItemStack (must be Material.WRITTEN_BOOK)
     * 
     * @param itemstack
     * @return Book
     */
    public Book getBook(ItemStack itemstack, String author, String title, ArrayList<String> pages) {
        if (instance == null) {
            return null;
        }
        return instance.getBook(itemstack, author, title, pages);
    }

}

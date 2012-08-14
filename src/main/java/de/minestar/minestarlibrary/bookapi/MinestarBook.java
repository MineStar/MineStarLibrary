package de.minestar.minestarlibrary.bookapi;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

public class MinestarBook {

    private final ItemStack itemstack;
    private final org.bukkit.inventory.ItemStack bukkitItemStack;

    public static MinestarBook createWrittenBook(String author, String title, List<String> pages) {
        CraftItemStack itemStack = new CraftItemStack(Material.WRITTEN_BOOK);
        return MinestarBook.getBook(itemStack, author, title, pages);
    }

    public static MinestarBook createBookAndQuill(List<String> pages) {
        CraftItemStack itemStack = new CraftItemStack(Material.BOOK_AND_QUILL);
        return MinestarBook.getBook(itemStack, "", "", pages);
    }

    public static MinestarBook getBook(CraftItemStack itemStack, String author, String title, List<String> pages) {
        if (itemStack.getTypeId() == Material.WRITTEN_BOOK.getId() || itemStack.getTypeId() == Material.BOOK_AND_QUILL.getId()) {
            return new MinestarBook(itemStack, author, title, pages);
        } else {
            return null;
        }
    }

    private MinestarBook(CraftItemStack itemStack, String author, String title, List<String> pages) {
        this.bukkitItemStack = itemStack;
        this.itemstack = itemStack.getHandle();
        if (this.itemstack.tag == null) {
            this.itemstack.tag = new NBTTagCompound();
            this.setAuthor(author);
            this.setTitle(title);
            this.setPages(pages);
        }
    }

    public boolean hasTitle() {
        return itemstack.tag.hasKey("title");
    }

    public boolean hasAuthor() {
        return itemstack.tag.hasKey("author");
    }

    public boolean hasPages() {
        return itemstack.tag.hasKey("pages");
    }

    public String getTitle() {
        return itemstack.tag.getString("title");
    }

    public String getAuthor() {
        return itemstack.tag.getString("author");
    }

    public String[] getPages() {
        NBTTagList list = (NBTTagList) itemstack.tag.get("pages");
        String[] pages = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            pages[i] = ((NBTTagString) list.get(i)).data;
        }
        return pages;
    }

    public List<String> getListPages() {
        NBTTagList list = (NBTTagList) itemstack.tag.get("pages");
        List<String> pages = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            pages.add(((NBTTagString) list.get(i)).data);
        }
        return pages;
    }

    public void setTitle(String title) {
        // sanity checking on the title
        if (title.length() > 16) {
            title = title.substring(0, 16);
        }
        itemstack.tag.setString("title", title);
    }

    public void setAuthor(String author) {
        // sanity checking on the author
        if (author.length() > 16) {
            author = author.substring(0, 16);
        }
        itemstack.tag.setString("author", author);
    }

    public void setPages(String[] pages) {
        NBTTagList list = new NBTTagList();
        int size = pages.length;
        for (int i = 0; i < size; i++) {
            String page = pages[i];
            // sanity checking on the page
            if (page.length() > 256) {
                page = page.substring(0, 256);
            }
            if (page != null && !page.equals("") && !page.isEmpty()) {
                NBTTagString p = new NBTTagString(page);
                p.setName(page);
                p.data = page;
                list.add(p);
            }
        }
        list.setName("pages");
        itemstack.tag.set("pages", list);
    }

    public void setPages(List<String> pages) {
        NBTTagList list = new NBTTagList();
        int size = pages.size();
        for (int i = 0; i < size; i++) {
            String page = pages.get(i);
            if (page.length() > 256) {
                page = page.substring(0, 256);
            }
            if (page != null && !page.equals("") && !page.isEmpty()) {
                NBTTagString p = new NBTTagString(page);
                p.setName(page);
                p.data = page;
                list.add(p);
            }
        }
        list.setName("pages");
        itemstack.tag.set("pages", list);
    }

    public ItemStack getItemStack() {
        return this.itemstack;
    }

    public org.bukkit.inventory.ItemStack getBukkitItemStack() {
        return this.bukkitItemStack;
    }

}

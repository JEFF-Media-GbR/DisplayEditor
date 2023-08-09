package com.jeffmedia.displayeditor.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeffmedia.displayeditor.DisplayEditorPlugin;
import com.jeffmedia.displayeditor.Keys;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class GUIManager {

    private final DisplayEditorPlugin plugin;

    public GUIManager(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveAndEmpty(Player player) {
        ItemStack[] hotbar = new ItemStack[9];
        for(int i = 0; i < 9; i++) {
            System.out.println("Saving " + player.getInventory().getItem(i) + " from slot " + i);
            hotbar[i] = player.getInventory().getItem(i);
            player.getInventory().setItem(i, null);
        }
        player.getPersistentDataContainer().set(Keys.HOTBAR, DataType.ITEM_STACK_ARRAY, hotbar);
    }

    public void restore(Player player) {
        if(!player.getPersistentDataContainer().has(Keys.HOTBAR, DataType.ITEM_STACK_ARRAY)) {
            return;
        }

        ItemStack[] hotbar = Objects.requireNonNull(player.getPersistentDataContainer().get(Keys.HOTBAR, DataType.ITEM_STACK_ARRAY));

        for(int i = 0; i < 9; i++) {
            System.out.println("Restoring " + hotbar[i] + " to slot " + i);
            player.getInventory().setItem(i, hotbar[i]);
        }

        player.getPersistentDataContainer().remove(Keys.HOTBAR);
    }

    public void createGui(Player player) {
        saveAndEmpty(player);

        player.getInventory().setItem(0, plugin.getGuiCreator().getChangeValueButton());
        player.getInventory().setItem(1, plugin.getGuiCreator().getChangeEditorButton());
        player.getInventory().setItem(8, plugin.getGuiCreator().getLeaveEditorButton());

    }

}

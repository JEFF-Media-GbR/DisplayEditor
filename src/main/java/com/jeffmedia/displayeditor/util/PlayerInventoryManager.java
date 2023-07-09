package com.jeffmedia.displayeditor.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerInventoryManager {

    private final NamespacedKey originalHotbarInventoryKey;

    public PlayerInventoryManager(Plugin plugin) {
        originalHotbarInventoryKey = new NamespacedKey(plugin, "originalHotbarInventory");
    }

    public void saveAndEmpty(Player player) {
        ItemStack[] hotbar = new ItemStack[9];
        for(int i = 0; i < 9; i++) {
            hotbar[i] = player.getInventory().getItem(i);
            player.getInventory().setItem(i, null);
        }
        player.getPersistentDataContainer().set(originalHotbarInventoryKey, DataType.ITEM_STACK_ARRAY, hotbar);
    }

    public void restore(Player player) {
        if(!player.getPersistentDataContainer().has(originalHotbarInventoryKey, DataType.ITEM_STACK_ARRAY)) {
            return;
        }

        ItemStack[] hotbar = Objects.requireNonNull(player.getPersistentDataContainer().get(originalHotbarInventoryKey, DataType.ITEM_STACK_ARRAY));

        for(int i = 0; i < 9; i++) {
            player.getInventory().setItem(i, hotbar[i]);
        }

        player.getPersistentDataContainer().remove(originalHotbarInventoryKey);
    }

    public void saveAndEmptyAll() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            saveAndEmpty(player);
        }
    }

    public void restoreAll() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            restore(player);
        }
    }

}

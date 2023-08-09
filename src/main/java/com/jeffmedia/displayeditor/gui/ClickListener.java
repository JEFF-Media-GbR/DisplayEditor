package com.jeffmedia.displayeditor.gui;

import com.jeffmedia.displayeditor.DisplayEditorPlugin;
import com.jeffmedia.displayeditor.Keys;
import com.jeffmedia.displayeditor.data.ClickType;
import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickListener implements Listener {

    private final DisplayEditorPlugin plugin;

    public ClickListener(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }

        Player player = event.getPlayer();
        DisplayEditor editor = plugin.getEditor(player);
        if (editor == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }

        GUIButtonType type = meta.getPersistentDataContainer().getOrDefault(Keys.GUI_KEY, GUIButtonType.DATA_TYPE, GUIButtonType.NONE);
        if (type == GUIButtonType.NONE) {
            return;
        }

        ClickType clickType = ClickType.fromInteractEvent(event);
        if (clickType == ClickType.NONE) {
            return;
        }

        event.setCancelled(true);
        ScrollDirection direction = clickType.getDirection();

        switch (type) {

            case CHANGE_VALUE -> {
                if (direction == ScrollDirection.UP) {
                    editor.increase();
                } else if (direction == ScrollDirection.DOWN) {
                    editor.decrease();
                }
            }

            case CHANGE_EDITOR -> {
                if (direction == ScrollDirection.UP) {
                    editor.nextEditor();
                } else if (direction == ScrollDirection.DOWN) {
                    editor.previousEditor();
                }
            }

            case QUIT -> plugin.endEditSession(player);
        }

    }
}

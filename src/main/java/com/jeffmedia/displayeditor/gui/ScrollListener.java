package com.jeffmedia.displayeditor.gui;

import com.jeffmedia.displayeditor.DisplayEditorPlugin;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.data.ScrollDirection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ScrollListener implements Listener {

    private final DisplayEditorPlugin plugin;

    public ScrollListener(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onScroll(PlayerItemHeldEvent event) {
        DisplayEditor editor = plugin.getEditor(event.getPlayer());
        if (editor == null) {
            // Not editing
            return;
        }
        event.setCancelled(true);
        boolean isShift = event.getPlayer().isSneaking();
        ScrollDirection direction = ScrollDirection.fromEvent(event);

        if (isShift) {
            switch (direction) {
                case UP:
                    editor.nextEditor();
                    break;
                case DOWN:
                    editor.previousEditor();
                    break;
                case NONE:
                    break;
            }
        } else {
            switch (direction) {
                case UP:
                    editor.increase();
                    break;
                case DOWN:
                    editor.decrease();
                    break;
                case NONE:
                    break;
            }
        }

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            event.getPlayer().getInventory().setHeldItemSlot(4);
            event.getPlayer().updateInventory();
        }, 2);
    }
}

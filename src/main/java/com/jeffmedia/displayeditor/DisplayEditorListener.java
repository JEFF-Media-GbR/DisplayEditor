package com.jeffmedia.displayeditor;

import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.util.ParticleGenerator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class DisplayEditorListener implements Listener {

    private final DisplayEditorPlugin plugin;

    public DisplayEditorListener(DisplayEditorPlugin plugin) {
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
        ParticleGenerator direction = ParticleGenerator.fromEvent(event);

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

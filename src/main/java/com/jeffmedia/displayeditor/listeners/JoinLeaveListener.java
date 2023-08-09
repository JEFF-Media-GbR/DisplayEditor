package com.jeffmedia.displayeditor.listeners;

import com.jeffmedia.displayeditor.DisplayEditorPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final DisplayEditorPlugin plugin;

    public JoinLeaveListener(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getGuiManager().restore(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        plugin.endEditSession(event.getPlayer());
    }
}

package com.jeffmedia.displayeditor;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("displayeditor|de")
public class DisplayEditorCommand extends BaseCommand {

    private final DisplayEditorPlugin plugin;

    public DisplayEditorCommand(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }

    @Subcommand("edit")
    public void onStartEdit(Player player, String selector) {
        List<Entity> entities = Bukkit.selectEntities(player, selector);
        entities.removeIf(entity -> !(entity instanceof Display));
        entities.sort((a, b) -> {
            double aDist = a.getLocation().distanceSquared(player.getLocation());
            double bDist = b.getLocation().distanceSquared(player.getLocation());
            return Double.compare(aDist, bDist);
        });
        Display display = (Display) entities.get(0);
        if(display == null) {
            player.sendMessage("No display found");
            return;
        }
        plugin.startEditSession(player, display);
        player.sendMessage("Started editing display");
    }

    private Display getTargetedDisplay(Player player) {
        return player.getWorld().getEntitiesByClass(Display.class).stream().min((a, b) -> {
            double aDist = a.getLocation().distanceSquared(player.getLocation());
            double bDist = b.getLocation().distanceSquared(player.getLocation());
            return Double.compare(aDist, bDist);
        }).orElse(null);
    }
}

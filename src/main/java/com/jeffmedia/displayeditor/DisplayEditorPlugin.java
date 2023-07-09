package com.jeffmedia.displayeditor;

import co.aikar.commands.PaperCommandManager;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class DisplayEditorPlugin extends JavaPlugin {

    private final Map<Player, DisplayEditor> editors = new HashMap<>();

    @Override
    public void onEnable() {
        PaperCommandManager acf = new PaperCommandManager(this);
        acf.registerCommand(new DisplayEditorCommand(this));
        getServer().getPluginManager().registerEvents(new DisplayEditorListener(this), this);
        getServer().getScheduler().runTaskTimer(this, new DisplayEditorTask(this), 1, 1);

    }

    public void startEditSession(Player player, Display display) {
        DisplayEditor editor = new DisplayEditor(display, player);
        editors.put(player, editor);
    }

    public DisplayEditor getEditor(Player player) {
        return editors.get(player);
    }

    public Collection<DisplayEditor> getEditors() {
        return editors.values();
    }

    public void debug(String... line) {
        for(String s : line) {
            getLogger().info("[D] " + s);
        }
    }

}

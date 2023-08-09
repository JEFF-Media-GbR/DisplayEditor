package com.jeffmedia.displayeditor;

import co.aikar.commands.PaperCommandManager;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.gui.ClickListener;
import com.jeffmedia.displayeditor.gui.GUICreator;
import com.jeffmedia.displayeditor.gui.ScrollListener;
import com.jeffmedia.displayeditor.listeners.JoinLeaveListener;
import com.jeffmedia.displayeditor.util.GUIManager;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class DisplayEditorPlugin extends JavaPlugin {

    private final Map<Player, DisplayEditor> editors = new HashMap<>();
    @Getter
    private final GUICreator guiCreator = new GUICreator(this);
    @Getter
    private final GUIManager guiManager = new GUIManager(this);
//    @Getter
//    private final PersistentDataType<?, GUIButtonType> guiButtonDataType = DataType.asEnum(GUIButtonType.class);

    @Override
    public void onEnable() {
        PaperCommandManager acf = new PaperCommandManager(this);
        acf.registerCommand(new DisplayEditorCommand(this));

        //getServer().getPluginManager().registerEvents(new ScrollListener(this), this);
        getServer().getPluginManager().registerEvents(new ClickListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);

        getServer().getScheduler().runTaskTimer(this, new DisplayEditorTask(this), 1, 1);
    }

    public void startEditSession(Player player, Display display) {
        DisplayEditor editor = new DisplayEditor(display, player);
        editors.put(player, editor);
        guiManager.createGui(player);
    }

    public void endEditSession(Player player) {
        editors.remove(player);
        guiManager.restore(player);
    }

    public DisplayEditor getEditor(Player player) {
        return editors.get(player);
    }

    public Collection<DisplayEditor> getEditors() {
        return editors.values();
    }

    public void debug(String... line) {
        for (String s : line) {
            getLogger().info("[D] " + s);
        }
    }

    @Override
    public void onDisable() {
        for(Player player : getServer().getOnlinePlayers()) {
            endEditSession(player);
        }
    }
}

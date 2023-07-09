package com.jeffmedia.displayeditor;

import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;

public class DisplayEditorTask implements Runnable {

    private static final char RAQUO = '»';
    private static final char LAQUO = '«';

    private final DisplayEditorPlugin plugin;

    public DisplayEditorTask(DisplayEditorPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void run() {
        for(DisplayEditor editor : plugin.getEditors()) {
            Player player = editor.getPlayer();

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(getActionBarMessage(editor, 1)));

            //BoundingBox hitbox = display.getBoundingBox();
            //Utils.showOutline(player, hitbox);
        }
    }

    private static String getEditorActionBarMessage(DisplayEditor displayEditor, ValueEditor<?> valueEditor, boolean enabled) {
        ChatColor colorTitle = enabled ? ChatColor.GOLD : ChatColor.GRAY;
        ChatColor colorValue = enabled ? ChatColor.YELLOW : ChatColor.WHITE;
        String formattedValue;
        Object value = valueEditor.getValue(displayEditor);
        if(value instanceof Float) {
            formattedValue = String.format("%.2f", value);
        } else if(value instanceof Integer) {
            formattedValue = String.format("%d", value);
        } else {
            formattedValue = value.toString();
        }
        return colorTitle + valueEditor.getName() + ChatColor.RESET + colorTitle + ": " + colorValue + formattedValue;
    }

    private static String getActionBarMessage(DisplayEditor editor, int offset) {
        StringBuilder sb = new StringBuilder();
        for(int i = -offset; i <= offset; i++) {
            boolean enabled = i == 0;
            boolean isLeftFromCurrent = i < 0;
            boolean isLast = i == offset;
            ValueEditor<?> subEditor = editor.getEditorFromCurrentOffset(i);
            sb.append(getEditorActionBarMessage(editor, subEditor, enabled));
            if(!isLast) {
                sb.append(ChatColor.DARK_GRAY).append(" ");
                if(isLeftFromCurrent) {
                    sb.append(LAQUO);
                } else {
                    sb.append(RAQUO);
                }
                sb.append(" ").append(ChatColor.RESET);
            }
        }
        return sb.toString();
    }


}

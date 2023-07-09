package com.jeffmedia.displayeditor;

import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.FloatEditor;
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

    private static String getEditorActionBarMessage(FloatEditor editor, Display display, boolean enabled) {
        ChatColor colorTitle = enabled ? ChatColor.GOLD : ChatColor.GRAY;
        ChatColor colorValue = enabled ? ChatColor.YELLOW : ChatColor.WHITE;
        String formattedValue = String.format("%.2f", editor.getValue(display));
        return colorTitle + editor.getName() + ChatColor.RESET + colorTitle + ": " + colorValue + formattedValue;
    }

    private static String getActionBarMessage(DisplayEditor editor, int offset) {
        StringBuilder sb = new StringBuilder();
        for(int i = -offset; i <= offset; i++) {
            boolean enabled = i == 0;
            boolean isLeftFromCurrent = i < 0;
            boolean isLast = i == offset;
            FloatEditor subEditor = editor.getEditorFromCurrentOffset(i);
            sb.append(getEditorActionBarMessage(subEditor, editor.getEntity(), enabled));
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

package com.jeffmedia.displayeditor.data.axis;

import org.bukkit.ChatColor;

public enum WorldAxis {
    X(ChatColor.RED), Y(ChatColor.GREEN), Z(ChatColor.BLUE), W(null);

    private final ChatColor chatColor;

    WorldAxis(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public String getChatColor() {
        return chatColor == null ? "" : chatColor.toString();
    }
}

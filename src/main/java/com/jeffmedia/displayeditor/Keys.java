package com.jeffmedia.displayeditor;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.util.Objects;
import java.util.UUID;

public class Keys {

    public static final NamespacedKey GUI_KEY = get("gui");
    public static final NamespacedKey HOTBAR = get("hotbar");

    private static NamespacedKey get(String key) {
        return Objects.requireNonNull(NamespacedKey.fromString("displayeditor:" + key));
    }


}

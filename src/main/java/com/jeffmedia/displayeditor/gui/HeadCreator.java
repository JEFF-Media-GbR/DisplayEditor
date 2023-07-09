package com.jeffmedia.displayeditor.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class HeadCreator {

    private static final UUID DUMMY_UUID = UUID.fromString("06942069-dead-cafe-babe-694206942069");

    public static PlayerProfile getProfileFromUrl(URL url) {
        PlayerProfile profile = Bukkit.createPlayerProfile(DUMMY_UUID);
        PlayerTextures textures = profile.getTextures();
        textures.setSkin(url);
        profile.setTextures(textures);
        return profile;
    }

    public static PlayerProfile getProfileFromId(String id) {
        try {
            return getProfileFromUrl(new URL("http://textures.minecraft.net/texture/" + id));
        } catch (MalformedURLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static URL getUrlFromBase64(String base64) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(base64));
        // We simply remove the "beginning" and "ending" part of the JSON, so we're left with only the URL. You could use a proper
        // JSON parser for this, but that's not worth it. The String will always start exactly with this stuff anyway
        return new URL(decoded.substring("{\"textures\":{\"SKIN\":{\"url\":\"".length(), decoded.length() - "\"}}}".length()));
    }

    public static ItemStack fromProfile(PlayerProfile profile, String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) Objects.requireNonNull(item.getItemMeta());
        meta.setOwnerProfile(profile);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack fromBase64(String base64, String name) {
        try {
            return fromProfile(getProfileFromUrl(getUrlFromBase64(base64)), name);
        }catch (MalformedURLException exception) {
            throw new RuntimeException(exception);
        }
    }

}

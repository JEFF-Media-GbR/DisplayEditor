package com.jeffmedia.displayeditor.gui;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeffmedia.displayeditor.DisplayEditorPlugin;
import com.jeffmedia.displayeditor.Keys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class GUICreator {

    private static final UUID DUMMY_UUID = UUID.fromString("06942069-dead-cafe-babe-694206942069");
    private final DisplayEditorPlugin plugin;

    private final ItemStack changeValueButton = createButton(Material.BOOK, "Incr./Decr. Value", GUIButtonType.CHANGE_VALUE);
    public ItemStack getChangeValueButton() {
        return changeValueButton.clone();
    }


    private final ItemStack changeEditorButton = createButton(Material.BOOKSHELF, "Next/Prev. Editor", GUIButtonType.CHANGE_EDITOR);
    public ItemStack getChangeEditorButton() {
        return changeEditorButton.clone();
    }

    private final ItemStack leaveEditorButton = createButton(Material.ACACIA_DOOR, "Leave Editor", GUIButtonType.QUIT);
    public ItemStack getLeaveEditorButton() {
        return leaveEditorButton.clone();
    }

    public GUICreator(DisplayEditorPlugin plugin) {
        this.plugin = Objects.requireNonNull(plugin);
    }

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

    private static ItemStack createHeadFromProfile(PlayerProfile profile, String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) Objects.requireNonNull(item.getItemMeta());
        meta.setOwnerProfile(profile);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createButton(PlayerProfile profile, String name, GUIButtonType type) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) Objects.requireNonNull(item.getItemMeta());
        meta.setOwnerProfile(profile);
        item.setItemMeta(meta);
        return createButton(() -> item, name, type);
    }

    public ItemStack createButton(Material material, String name, GUIButtonType type) {
        return createButton(() -> new ItemStack(material), name, type);
    }

    public ItemStack createButton(Supplier<ItemStack> itemSupplier, String name, GUIButtonType type) {
        ItemStack item = itemSupplier.get();
        ItemMeta meta = Objects.requireNonNull(item.getItemMeta());
        meta.setDisplayName(name);
        meta.getPersistentDataContainer().set(Keys.GUI_KEY, GUIButtonType.DATA_TYPE, type);
        item.setItemMeta(meta);
        return item;
    }

}

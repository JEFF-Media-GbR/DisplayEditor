package com.jeffmedia.displayeditor.editors;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.data.axis.LocationdAxis;
import com.jeffmedia.displayeditor.editors.values.*;
import com.jeffmedia.displayeditor.data.axis.QuaternionfAxis;
import com.jeffmedia.displayeditor.data.RotationSide;
import com.jeffmedia.displayeditor.data.axis.Vec3fAxis;
import lombok.Getter;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DisplayEditor {

    private static final List<ValueEditor<?>> editors = new ArrayList<>();

    static {

        // Coords X, Y, Z
        for (LocationdAxis axis : LocationdAxis.values()) {
            editors.add(new CoordsEditor(axis));
        }

        // Pitch
        editors.add(new PitchEditor());

        // Yaw
        editors.add(new YawEditor());

        // Billboard
        editors.add(new BillboardEditor());

        // Scale X, Y, Z
        for (Vec3fAxis axis : Vec3fAxis.values()) {
            editors.add(new ScaleEditor(axis));
        }

        // Translation X, Y, Z
        for (Vec3fAxis axis : Vec3fAxis.values()) {
            editors.add(new TranslationEditor(axis));
        }

        // Rotation Left X, Y, Z, W, Right X, Y, Z, W
        for (RotationSide side : RotationSide.values()) {
            for (QuaternionfAxis axis : QuaternionfAxis.values()) {
                editors.add(new RotationEditor(side, axis));
            }
        }

    }

    @Getter
    private final Display entity;
    @Getter
    private final Player player;
    private final ItemStack[] hotbar = new ItemStack[9];
    @Getter
    private float step = 0.1f;
    private int currentEditor = 0;

    public DisplayEditor(Display entity, Player player) {
        this.entity = entity;
        this.player = player;
        for (int i = 0; i < 9; i++) {
            hotbar[i] = player.getInventory().getItem(i);
            player.getInventory().setItem(i, null);
        }
        player.getInventory().setHeldItemSlot(4);
    }

    public ValueEditor<?> getCurrentEditor() {
        return editors.get(currentEditor);
    }

    public ValueEditor<?> getEditorFromCurrentOffset(int offset) {
        int index = currentEditor + offset;
        if (index < 0) {
            index = editors.size() - 1;
        } else if (index >= editors.size()) {
            index = 0;
        }
        return editors.get(index);
    }

    public void nextEditor() {
        if (currentEditor < editors.size() - 1) {
            currentEditor++;
        } else {
            currentEditor = 0;
        }
    }

    public void previousEditor() {
        if (currentEditor > 0) {
            currentEditor--;
        } else {
            currentEditor = editors.size() - 1;
        }
    }

    public void increase() {
        getCurrentEditor().change(this, ScrollDirection.UP);
    }

    public void decrease() {
        getCurrentEditor().change(this, ScrollDirection.DOWN);
    }

}

package com.jeffmedia.displayeditor;

import com.jeffmedia.displayeditor.editors.ScaleEditor;
import lombok.Getter;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DisplayEditor {

    private static final List<FloatEditor> editors = new ArrayList<>();

    static {
        editors.add(new ScaleEditor(Axis.X));
        editors.add(new ScaleEditor(Axis.Y));
        editors.add(new ScaleEditor(Axis.Z));
    }

    @Getter
    private final Display entity;
    @Getter
    private final Player player;
    private final ItemStack[] hotbar = new ItemStack[9];
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

    public FloatEditor getCurrentEditor() {
        return editors.get(currentEditor);
    }

    public FloatEditor getEditorFromCurrentOffset(int offset) {
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

    public void increase(float amount) {
        FloatEditor editor = getCurrentEditor();
        float value = editor.getValue(entity);
        editor.setValue(entity, value + amount);
    }

    public void increase() {
        increase(step);
    }

    public void decrease() {
        increase(-step);
    }

}

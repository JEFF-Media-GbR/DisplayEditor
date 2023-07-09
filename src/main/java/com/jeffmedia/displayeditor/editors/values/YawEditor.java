package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import org.bukkit.Location;

public class YawEditor implements ValueEditor<Float> {
    @Override
    public Float getValue(DisplayEditor editor) {
        return editor.getEntity().getLocation().getYaw();
    }

    @Override
    public void change(DisplayEditor editor, ScrollDirection direction) {
        Location loc = editor.getEntity().getLocation();
        loc.setYaw(getValue(editor) + direction.getMultiplier());
        editor.getEntity().teleport(loc);
    }

    @Override
    public String getName() {
        return "Yaw";
    }
}

package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.data.axis.LocationdAxis;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CoordsEditor implements ValueEditor<Double>  {

    private final LocationdAxis locationdAxis;

    public CoordsEditor(LocationdAxis locationdAxis) {
        this.locationdAxis = locationdAxis;
    }

    @Override
    public Double getValue(DisplayEditor editor) {
        return locationdAxis.getValue(editor.getEntity().getLocation());
    }

    @Override
    public void change(DisplayEditor editor, ScrollDirection direction) {
        Location location = editor.getEntity().getLocation();
        locationdAxis.setValue(location, getValue(editor) + direction.getMultiplier() * editor.getStep());
        editor.getEntity().teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public String getName() {
        return "Pos. " + locationdAxis.coloredName();
    }
}

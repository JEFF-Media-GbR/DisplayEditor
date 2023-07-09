package com.jeffmedia.displayeditor.editors;

import com.jeffmedia.displayeditor.util.Axis;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;

public class ScaleEditor implements FloatEditor {

    private final Axis axis;

    public ScaleEditor(Axis axis) {
        this.axis = axis;
    }

    @Override
    public float getValue(Display display) {
        return axis.getValue(display.getTransformation().getScale());
    }

    @Override
    public void setValue(Display display, float value) {
        Transformation transformation = display.getTransformation();
        axis.setValue(transformation.getScale(), value);
        display.setTransformation(transformation);
    }

    @Override
    public String getName() {
        return "Scale " + axis.name();
    }
}

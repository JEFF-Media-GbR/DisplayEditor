package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.editors.FloatEditor;
import com.jeffmedia.displayeditor.util.QuaternionfAxis;
import com.jeffmedia.displayeditor.util.RotationSide;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;

public class RotationEditor implements FloatEditor {

    private final RotationSide rotationSide;
    private final QuaternionfAxis quaternionfAxis;

    public RotationEditor(RotationSide rotationSide, QuaternionfAxis quaternionfAxis) {
        this.rotationSide = rotationSide;
        this.quaternionfAxis = quaternionfAxis;
    }

    @Override
    public float getValue(Display display) {
        return quaternionfAxis.getValue(rotationSide.getFromTransformation(display.getTransformation()));
    }

    @Override
    public void setValue(Display display, float value) {
        Transformation transformation = display.getTransformation();
        quaternionfAxis.setValue(rotationSide.getFromTransformation(transformation), value);
        display.setTransformation(transformation);
    }

    @Override
    public String getName() {
        return "Rot. " + rotationSide.name() + " " + quaternionfAxis.coloredName();
    }
}

package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import com.jeffmedia.displayeditor.data.axis.QuaternionfAxis;
import com.jeffmedia.displayeditor.data.RotationSide;
import org.bukkit.util.Transformation;

public class RotationEditor implements ValueEditor<Float> {

    private final RotationSide rotationSide;
    private final QuaternionfAxis quaternionfAxis;

    public RotationEditor(RotationSide rotationSide, QuaternionfAxis quaternionfAxis) {
        this.rotationSide = rotationSide;
        this.quaternionfAxis = quaternionfAxis;
    }

    @Override
    public Float getValue(DisplayEditor editor) {
        return quaternionfAxis.getValue(rotationSide.getFromTransformation(editor.getEntity().getTransformation()));
    }

    @Override
    public void change(DisplayEditor editor, ScrollDirection direction) {
        Transformation transformation = editor.getEntity().getTransformation();
        quaternionfAxis.setValue(rotationSide.getFromTransformation(transformation), getValue(editor) + direction.getMultiplier() * editor.getStep());
        editor.getEntity().setTransformation(transformation);
    }

    @Override
    public String getName() {
        return "Rot. " + rotationSide.name() + " " + quaternionfAxis.coloredName();
    }
}

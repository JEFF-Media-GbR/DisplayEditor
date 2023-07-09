package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.editors.FloatEditor;
import com.jeffmedia.displayeditor.util.Vec3fAxis;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;

public class TranslationEditor implements FloatEditor {

    private final Vec3fAxis vec3fAxis;

    public TranslationEditor(Vec3fAxis vec3fAxis) {
        this.vec3fAxis = vec3fAxis;
    }

    @Override
    public float getValue(Display display) {
        return vec3fAxis.getValue(display.getTransformation().getTranslation());
    }

    @Override
    public void setValue(Display display, float value) {
        Transformation transformation = display.getTransformation();
        vec3fAxis.setValue(transformation.getTranslation(), value);
        display.setTransformation(transformation);
    }

    @Override
    public String getName() {
        return "Transl. " + vec3fAxis.coloredName();
    }
}


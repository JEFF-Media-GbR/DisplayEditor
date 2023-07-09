package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import com.jeffmedia.displayeditor.data.axis.Vec3fAxis;
import org.bukkit.util.Transformation;

public class TranslationEditor implements ValueEditor<Float> {

    private final Vec3fAxis vec3fAxis;

    public TranslationEditor(Vec3fAxis vec3fAxis) {
        this.vec3fAxis = vec3fAxis;
    }

    @Override
    public Float getValue(DisplayEditor editor) {
        return vec3fAxis.getValue(editor.getEntity().getTransformation().getTranslation());
    }

    @Override
    public void change(DisplayEditor editor, ScrollDirection direction) {
        Transformation transformation = editor.getEntity().getTransformation();
        vec3fAxis.setValue(transformation.getTranslation(), getValue(editor) + direction.getMultiplier() * editor.getStep());
        editor.getEntity().setTransformation(transformation);
    }

    @Override
    public String getName() {
        return "Transl. " + vec3fAxis.coloredName();
    }
}


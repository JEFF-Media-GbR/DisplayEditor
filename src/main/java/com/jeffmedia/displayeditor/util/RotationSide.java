package com.jeffmedia.displayeditor.util;

import org.bukkit.util.Transformation;
import org.joml.Quaternionf;

public enum RotationSide {
    LEFT {
        @Override
        public Quaternionf getFromTransformation(Transformation transformation) {
            return transformation.getLeftRotation();
        }
    },
    RIGHT {
        @Override
        public Quaternionf getFromTransformation(Transformation transformation) {
            return transformation.getRightRotation();
        }
    };

    public abstract Quaternionf getFromTransformation(Transformation transformation);
}

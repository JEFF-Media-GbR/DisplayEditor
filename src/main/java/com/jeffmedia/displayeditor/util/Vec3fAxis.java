package com.jeffmedia.displayeditor.util;

import org.joml.Vector3f;

public enum Vec3fAxis {
    X(WorldAxis.X) {
        @Override
        public float getValue(Vector3f vec) {
            return vec.x;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.x = value;
        }
    },

    Y(WorldAxis.Y) {
        @Override
        public float getValue(Vector3f vec) {
            return vec.y;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.y = value;
        }
    },

    Z(WorldAxis.Z) {
        @Override
        public float getValue(Vector3f vec) {
            return vec.z;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.z = value;
        }
    };

    private final WorldAxis axis;

    Vec3fAxis(WorldAxis axis) {
        this.axis = axis;
    }

    public abstract float getValue(Vector3f vec);
    public abstract void setValue(Vector3f vec, float value);

    public String coloredName() {
        return axis.getChatColor() + name();
    }
}

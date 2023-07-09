package com.jeffmedia.displayeditor.util;

import org.joml.Vector3f;

public enum Axis {
    X {
        @Override
        public float getValue(Vector3f vec) {
            return vec.x;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.x = value;
        }
    },

    Y {
        @Override
        public float getValue(Vector3f vec) {
            return vec.y;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.y = value;
        }
    },

    Z {
        @Override
        public float getValue(Vector3f vec) {
            return vec.z;
        }

        @Override
        public void setValue(Vector3f vec, float value) {
            vec.z = value;
        }
    };

    public abstract float getValue(Vector3f vec);
    public abstract void setValue(Vector3f vec, float value);
}

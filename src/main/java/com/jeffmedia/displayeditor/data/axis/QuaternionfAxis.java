package com.jeffmedia.displayeditor.data.axis;

import org.joml.Quaternionf;

public enum QuaternionfAxis {
    X(WorldAxis.X) {
        @Override
        public float getValue(Quaternionf vec) {
            return vec.x;
        }

        @Override
        public void setValue(Quaternionf vec, float value) {
            vec.x = value;
        }
    },

    Y(WorldAxis.Y) {
        @Override
        public float getValue(Quaternionf vec) {
            return vec.y;
        }

        @Override
        public void setValue(Quaternionf vec, float value) {
            vec.y = value;
        }
    },

    Z(WorldAxis.Z) {
        @Override
        public float getValue(Quaternionf vec) {
            return vec.z;
        }

        @Override
        public void setValue(Quaternionf vec, float value) {
            vec.z = value;
        }
    },

    W(WorldAxis.W) {
        @Override
        public float getValue(Quaternionf vec) {
            return vec.w;
        }

        @Override
        public void setValue(Quaternionf vec, float value) {
            vec.w = value;
        }
    };

    private final WorldAxis axis;

    QuaternionfAxis(WorldAxis axis) {
        this.axis = axis;
    }

    public abstract float getValue(Quaternionf vec);
    public abstract void setValue(Quaternionf vec, float value);

    public String coloredName() {
        return axis.getChatColor() + name();
    }
}

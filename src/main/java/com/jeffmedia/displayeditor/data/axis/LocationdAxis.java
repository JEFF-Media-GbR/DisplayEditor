package com.jeffmedia.displayeditor.data.axis;

import org.bukkit.Location;

public enum LocationdAxis {

    X(WorldAxis.X) {
        @Override
        public double getValue(Location loc) {
            return loc.getX();
        }

        @Override
        public void setValue(Location loc, double value) {
            loc.setX(value);
        }
    }, Y(WorldAxis.Y) {
        @Override
        public double getValue(Location loc) {
            return loc.getY();
        }

        @Override
        public void setValue(Location loc, double value) {
            loc.setY(value);
        }
    }, Z(WorldAxis.Z) {
        @Override
        public double getValue(Location loc) {
            return loc.getZ();
        }

        @Override
        public void setValue(Location loc, double value) {
            loc.setZ(value);
        }
    };

    private final WorldAxis axis;

    LocationdAxis(WorldAxis axis) {
        this.axis = axis;
    }

    public abstract double getValue(Location loc);
    public abstract void setValue(Location loc, double value);

    public String coloredName() {
        return axis.getChatColor() + name();
    }

}

package com.jeffmedia.displayeditor.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Utility;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class Utils {

    public Set<Location> getBoundingBoxOutline(BoundingBox box, double step) {
        Set<Location> locations = new HashSet<>();
        double minX = box.getMinX();
        double minY = box.getMinY();
        double minZ = box.getMinZ();
        double maxX = box.getMaxX();
        double maxY = box.getMaxY();
        double maxZ = box.getMaxZ();

        for (double x = minX; x <= maxX; x += step) {
            for (double y = minY; y <= maxY; y += step) {
                for (double z = minZ; z <= maxZ; z += step) {
                    if (x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                        locations.add(new Location(null, x, y, z));
                    }
                }
            }
        }

        return locations;
    }

    public void showOutline(Player player, BoundingBox box) {
        Set<Location> locations = getBoundingBoxOutline(box, 0.1);
        for (Location location : locations) {
            player.spawnParticle(Particle.REDSTONE, location, 1, 0, 0, 0, new Particle.DustOptions(Color.GREEN, 1));
        }
    }

}

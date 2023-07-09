package com.jeffmedia.displayeditor.util;

import org.bukkit.event.player.PlayerItemHeldEvent;

public enum ParticleGenerator {
    UP,
    DOWN,
    NONE;

    public static ParticleGenerator fromEvent(PlayerItemHeldEvent event) {
        int old = event.getPreviousSlot();
        int current = event.getNewSlot();

        if(old > current) {
            return UP;
        } else if(old < current) {
            return DOWN;
        } else {
            return NONE;
        }
    }
}

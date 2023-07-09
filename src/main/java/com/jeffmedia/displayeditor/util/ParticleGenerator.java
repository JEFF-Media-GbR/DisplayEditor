package com.jeffmedia.displayeditor;

import org.bukkit.event.player.PlayerItemHeldEvent;

public enum ScrollDirection {
    UP,
    DOWN,
    NONE;

    public static ScrollDirection fromEvent(PlayerItemHeldEvent event) {
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

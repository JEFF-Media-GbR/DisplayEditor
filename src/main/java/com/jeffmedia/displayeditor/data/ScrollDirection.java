package com.jeffmedia.displayeditor.data;

import lombok.Getter;
import org.bukkit.event.player.PlayerItemHeldEvent;

public enum ScrollDirection {
    UP(1),
    DOWN(-1),
    NONE(0);

    ScrollDirection(float multiplier) {
        this.multiplier = multiplier;
    }

    @Getter private final float multiplier;

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

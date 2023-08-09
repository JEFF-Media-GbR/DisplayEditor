package com.jeffmedia.displayeditor.data;

import lombok.Getter;
import org.bukkit.event.player.PlayerInteractEvent;

public enum ClickType {
    LEFT(ScrollDirection.DOWN),
    RIGHT(ScrollDirection.UP),
    NONE(ScrollDirection.NONE);

    @Getter private final ScrollDirection direction;

    ClickType(ScrollDirection direction) {
        this.direction = direction;
    }

    public static ClickType fromInteractEvent(PlayerInteractEvent event) {
        switch (event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                return LEFT;

            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                return RIGHT;

            default:
                return NONE;
        }
    }
}

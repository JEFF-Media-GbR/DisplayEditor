package com.jeffmedia.displayeditor.data;

import lombok.Getter;

public enum ScrollDirection {
    UP(1), DOWN(-1);

    @Getter private final float multiplier;

    ScrollDirection(float multiplier) {
        this.multiplier = multiplier;
    }
}

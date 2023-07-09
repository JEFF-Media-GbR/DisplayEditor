package com.jeffmedia.displayeditor;

import org.bukkit.entity.Display;

public interface FloatEditor {

    float getValue(Display display);

    void setValue(Display display, float value);

    String getName();

}

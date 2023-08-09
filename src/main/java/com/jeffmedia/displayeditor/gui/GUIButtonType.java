package com.jeffmedia.displayeditor.gui;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.persistence.PersistentDataType;

public enum GUIButtonType {
    CHANGE_VALUE,
    CHANGE_EDITOR,
    QUIT,
    NONE;

    public static final PersistentDataType<?, GUIButtonType> DATA_TYPE = DataType.asEnum(GUIButtonType.class);
}

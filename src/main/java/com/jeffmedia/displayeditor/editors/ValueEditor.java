package com.jeffmedia.displayeditor.editors;

import com.jeffmedia.displayeditor.data.ScrollDirection;

public interface ValueEditor<T> {

    T getValue(DisplayEditor editor);

    //void setValue(Display display, float value);

    void change(DisplayEditor editor, ScrollDirection direction);

    String getName();

}

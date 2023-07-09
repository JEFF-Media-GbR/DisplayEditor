package com.jeffmedia.displayeditor.editors.values;

import com.jeffmedia.displayeditor.data.ScrollDirection;
import com.jeffmedia.displayeditor.editors.DisplayEditor;
import com.jeffmedia.displayeditor.editors.ValueEditor;
import com.jeffmedia.displayeditor.util.EnumIterator;
import org.bukkit.entity.Display;

public class BillboardEditor implements ValueEditor<Display.Billboard> {
    @Override
    public Display.Billboard getValue(DisplayEditor editor) {
        return editor.getEntity().getBillboard();
    }

    @Override
    public void change(DisplayEditor editor, ScrollDirection direction) {
        Display.Billboard next = direction == ScrollDirection.UP ? EnumIterator.getNext(getValue(editor)) : EnumIterator.getPrevious(getValue(editor));
        editor.getEntity().setBillboard(next);
    }

    @Override
    public String getName() {
        return "Billboard";
    }
}

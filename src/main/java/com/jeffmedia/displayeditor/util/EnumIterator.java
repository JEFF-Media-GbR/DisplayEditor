package com.jeffmedia.displayeditor.util;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("unchecked")
public class EnumIterator {

    public <E extends Enum<E>> E getNext(E e) {
        E[] values = (E[]) e.getClass().getEnumConstants();
        int index = e.ordinal();
        index = (index + 1) % values.length;
        return values[index];
    }

    public <E extends Enum<E>> E getPrevious(E e) {
        E[] values = (E[]) e.getClass().getEnumConstants();
        int index = e.ordinal();
        index = (index - 1) % values.length;
        if(index < 0) {
            index = values.length - 1;
        }
        return values[index];
    }

}

package org.ms.explorer.type;

import java.util.ArrayList;
import java.util.List;

public enum NumerusType {
    SINGULAR(1),
    PLURAL(2);

    private int value;
    private static List<NumerusType> list = null;

    static {
        list = new ArrayList<NumerusType>();

        list.add(SINGULAR);
        list.add(PLURAL);
    }

    NumerusType(int value) {
        this.value = value;
    }

    public static NumerusType getType(int value) {
        for (NumerusType numerusType : list) {
            if (numerusType.value == value) {
                return numerusType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

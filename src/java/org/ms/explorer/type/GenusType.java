package org.ms.explorer.type;

import java.util.ArrayList;
import java.util.List;

public enum GenusType {

    MASKULINUM(1),
    FEMININUM(2),
    NEUTRUM(3);

    private int value;
    private static List<GenusType> list = null;

    static {
        list = new ArrayList<GenusType>();

        list.add(MASKULINUM);
        list.add(FEMININUM);
        list.add(NEUTRUM);
    }

    GenusType(int value) {
        this.value = value;
    }

    public static GenusType getType(int value) {
        for (GenusType genusType : list) {
            if (genusType.value == value) {
                return genusType;
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

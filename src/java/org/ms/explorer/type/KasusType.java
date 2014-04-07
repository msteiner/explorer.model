package org.ms.explorer.type;

import java.util.ArrayList;
import java.util.List;

public enum KasusType {

    NOMINATIV(0),
    AKKUSATIV(1),
    GENITIV(2),
    DATIV(3);

    private int value;
    private static List<KasusType> list = null;

    static {
        list = new ArrayList<KasusType>();

        list.add(NOMINATIV);
        list.add(AKKUSATIV);
        list.add(GENITIV);
        list.add(DATIV);
    }

    KasusType(int value) {
        this.value = value;
    }

    public static KasusType getType(int value) {
        for (KasusType kasusType : list) {
            if (kasusType.value == value) {
                return kasusType;
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

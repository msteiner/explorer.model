package org.ms.explorer.registry;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

/**
 * Index für die Suche nach Entities. Handling:
 * |KEY     | VALUE1, VALUE2, VALUE...
 * "Baum" -> {ID1, ID2, ID3, ...}
 * "Hund" -> {ID4}
 */
public class NameIndex extends HashMap<String, List<String>> {
    @SuppressWarnings("compatibility:-7244153733873967743")
    private static final long serialVersionUID = 1L;

    private static NameIndex nameIndex = null;
    private List<String> ids = null;
    
    private NameIndex() {
        super();
    }
    
    public static NameIndex instance() {
        if (nameIndex == null) {
            nameIndex = new NameIndex();
        }
        return nameIndex;
    }
    
    public void addEntry(String name, String id) {
        
        if (nameIndex.get(name) != null) {
            ids = nameIndex.get(name);
            ids.add(id);
        }
        else {
            ids = new ArrayList<String>();
            nameIndex.put(name, ids);
        }
    }

    public List<String> getIds(String name) {
        
        if (this.containsKey(name)) {
            return this.get(name);
        }
        
        return new ArrayList<String>();
    }
}

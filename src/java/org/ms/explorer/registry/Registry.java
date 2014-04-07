package org.ms.explorer.registry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;

import org.ms.explorer.element.Element;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;

public class Registry extends HashMap<String, Element> {

    private static Registry registry = null;
    @SuppressWarnings("compatibility:-1956300216917767540")
    private static final long serialVersionUID = 1L;
    
    private static NameIndex nameIndex = null;

    private Registry() {
        // hide constructor
    }

    public static Registry instance() {
        if (registry == null) {
            nameIndex = NameIndex.instance();
            registry = new Registry();
        }
        return registry;
    }

    /* public Element getEntry(String key) {
        return registry.get(key);
    } */

    public void addEntry(Element element) {
        if (exist(element)) {
            element = findElement(element);
            element.markObserved();
        }
        else {
            element.setId(element.createUUID());
            element.markObserved();
            List<String> nameIndexValues = new ArrayList<String>();
            if (element instanceof Entity) {
                nameIndexValues.add(element.getId());
                nameIndex.put(((Entity) element).getName(), nameIndexValues);
            }
            else if (element instanceof Transmitter) {
                // do noting yet.
            }
            
            registry.put(element.getId(), element);
        }
    }
    
    private boolean exist(Element searchValues) {
        
        if (findElement(searchValues) != null) {
            return true;
        }
        return false;
    }
    
    public Element findElement(Element searchValues) {
        
        Element element = null;
        
        List<Element> elements = findElements(searchValues);
        if (elements != null && elements.size() > 0) {
            // Offen: Algorithmus, nach welchem entschieden wird, ob ein Objekt existiert.
            // Beispiel: "Blatt" -> Papier, Baum.
            element = elements.get(0);
        }
        
        return element;
    }
    
    /**
     * Abfrage:
     * NameIndex: [["Blatt"]["ID1", "ID2", "ID3"]]
     * Registry:  [["ID1"][ELEMENT["ID1", "Blatt", ...]]] // -> Baum
     *            [["ID2"][ELEMENT["ID2", "Blatt", ...]]] // -> Papier
     *            [["ID3"][ELEMENT["ID3", "Blatt", ...]]] // -> ...
     * @param searchValues
     * @return
     */
    public List<Element> findElements(Element searchValues) {
        
        List<Element> elements = new ArrayList<Element>();
        List<String> ids = null;
        
        if (searchValues instanceof Entity) {
            String name = ((Entity) searchValues).getName();
            if (nameIndex.containsKey(name)) {
                ids = nameIndex.get(name);
                for (String id : ids) {
                    elements.add(this.get(id));
                }
            }
        }
        return elements;
    }
    
    public NameIndex getNameIndex() {
        return nameIndex;
    }
}

package org.ms.explorer.bo.registry;

import org.ms.explorer.bo.ElementBO;
import org.ms.explorer.element.Element;
import org.ms.explorer.registry.Registry;

public class ElementRegistryBO implements ElementBO {
    
    public ElementRegistryBO() {
        super();
    }
    
    private static Registry registry = Registry.instance();

    @Override
    public Element updateElement(Element element) throws Exception {
        //registry.addEntry(key, filename);
        // TODO Implement this method
        return null;
    }
}

package org.ms.explorer.registry;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import org.ms.explorer.element.Artikel;
import org.ms.explorer.element.Element;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;
import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;
import org.ms.explorer.util.TestTools;

public class RegistryTest extends TestTools {
    
    Registry registry = Registry.instance();
    
    public RegistryTest() {
    }

    /**
     * @see Registry#addEntry(Element)
     */
    @Test
    public void testAddEntry() {
        
        final String NAME = "Blatt";
        
        Artikel artikel = createArtikel(KasusType.DATIV, GenusType.NEUTRUM, NumerusType.SINGULAR);
        Entity entity = createEntity(NAME, artikel, null);
        Entity searchValue = createEntity(NAME, artikel, null);
        registry.addEntry(entity);
        System.out.println("registry=" + registry);
        System.out.println("nameIndex=" + registry.getNameIndex());
        
        List<Element> elements = registry.findElements(searchValue);
        assertNotNull("Expected 1 element but was null.", elements);
        assertEquals("Expected 1 element but none was found.", 1, elements.size());
        
        Entity result = (Entity) elements.get(0);
        assertEquals("Expected " + NAME + " but found " + result.getName(), NAME, result.getName());
        assertNotNull("Expected an id but didn't found.", result.getId());
        assertNotNull("Expected a observed date but didn't found.", result.getObserved());
    }
    
    /**
     * @see Registry#addEntry(Element)
     */
    @Test
    public void testAdd2EntriesWithTransmitter() {
        
        final String NAME1 = "Baum";
        final String NAME2 = "Blatt";
        
        Artikel artikel1 = createArtikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        Artikel artikel2 = createArtikel(KasusType.NOMINATIV, GenusType.NEUTRUM, NumerusType.SINGULAR);
        
        Entity baum = createEntity(NAME1, artikel1, null);
        Entity blatt = createEntity(NAME2, artikel2, null);
        
        Entity searchValue1 = createEntity(NAME1, artikel1, null);
        Entity searchValue2 = createEntity(NAME2, artikel2, null);
        
        Transmitter transmitter1 = createTransmitter(baum, Transmitter.RelationType.HAS,
                                         Transmitter.Quantity.N, blatt);
        Transmitter transmitter2 = createTransmitter(blatt, Transmitter.RelationType.IS_ELEMENT_OF,
                                         null, baum);

        registry.addEntry(baum);
        registry.addEntry(blatt);
        registry.addEntry(transmitter1);
        registry.addEntry(transmitter2);
        
        System.out.println("registry=" + registry);
        System.out.println("nameIndex=" + registry.getNameIndex());
        
        List<Element> elements1 = registry.findElements(searchValue1);
        assertNotNull("Expected 1 element but was null.", elements1);
        assertEquals("Expected 1 element but none was found.", 1, elements1.size());
        Entity result1 = (Entity) elements1.get(0);
        assertEquals("Expected " + NAME1 + " but found " + result1.getName(), NAME1, result1.getName());
        assertNotNull("Expected an id but didn't found.", result1.getId());
        assertNotNull("Expected a observed date but didn't found.", result1.getObserved());

        List<Element> elements2 = registry.findElements(searchValue2);
        assertNotNull("Expected 1 element but was null.", elements2);
        assertEquals("Expected 1 element but none was found.", 1, elements2.size());
        Entity result2 = (Entity) elements2.get(0);
        assertEquals("Expected " + NAME2 + " but found " + result2.getName(), NAME2, result2.getName());
        assertNotNull("Expected an id but didn't found.", result2.getId());
        assertNotNull("Expected a observed date but didn't found.", result2.getObserved());
        
        
    }
    
    /**
     * @see Registry#findElement(Element)
     */
    @Test
    public void testFindElement() {
        fail("Unimplemented");
    }

    /**
     * @see Registry#findElements(Element)
     */
    @Test
    public void testFindElements() {
        fail("Unimplemented");
    }
}

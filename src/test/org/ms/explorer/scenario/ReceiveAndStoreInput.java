package org.ms.explorer.scenario;

import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;

import org.ms.explorer.bo.ElementBO;
import org.ms.explorer.bo.file.ElementFileBO;
import org.ms.explorer.element.Artikel;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;
import org.ms.explorer.registry.Registry;
import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;
import org.ms.explorer.util.TestTools;

public class ReceiveAndStoreInput extends TestTools {
    
    ElementBO bo = new ElementFileBO();
    
    public ReceiveAndStoreInput() {
    }
    
    @Test
    public void addTreeAndColor() {
        // erfasse "Borke (ist Braun)"
        Artikel artikel1 = new Artikel(KasusType.NOMINATIV, GenusType.FEMININUM, NumerusType.SINGULAR);
        Artikel artikel2 = new Artikel(KasusType.NOMINATIV, GenusType.NEUTRUM, NumerusType.PLURAL);
        Artikel artikel3 = new Artikel(KasusType.NOMINATIV, GenusType.NEUTRUM, NumerusType.SINGULAR);
        Artikel artikel4 = new Artikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        
        Entity borke = createEntity("Borke", new Artikel(KasusType.NOMINATIV, GenusType.FEMININUM, NumerusType.SINGULAR), null);
        Entity braun = createEntity("Braun", new Artikel(KasusType.NOMINATIV, GenusType.NEUTRUM, NumerusType.SINGULAR), null);
        Transmitter transmitter1 = createTransmitter(borke, Transmitter.RelationType.IS, braun);
        
        Registry.instance().addEntry(transmitter1);
        
        
        Entity blatt = null;
        Entity baum = null;
        //Transmitter transmitter1 = null;
        Transmitter transmitter2 = null;
        
        try {
            baum = createEntity("00001", "Baum", artikel1, null);
            blatt = createEntity("00002", "Blatt", artikel2, null);
            
            transmitter1 = createTransmitter(baum, "T_1", Transmitter.RelationType.HAS, Transmitter.Quantity.N, 10, new Date(), blatt);
            baum.addTransmitter(transmitter1);
            transmitter2 = createTransmitter(baum, "T_2", Transmitter.RelationType.HAS, Transmitter.Quantity.EINS, 10, new Date(), borke);
            baum.addTransmitter(transmitter2);
            System.out.println("... update...");
                    bo.updateElement(baum);
            System.out.println("... done.");

        //            List<String> lines = bo.readFile();
        //            for (String line : lines) {
        //                System.out.println(line);
        //            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
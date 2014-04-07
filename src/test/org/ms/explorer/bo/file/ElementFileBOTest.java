package org.ms.explorer.bo.file;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ms.explorer.element.Artikel;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;
import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;
import org.ms.explorer.util.TestTools;

public class ElementFileBOTest extends TestTools {

    ElementFileBO bo = new ElementFileBO();

    @Test
    public void testAddOneEntity() {

        Artikel artikel = new Artikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        Entity expected = createEntity(null, "Baum", artikel, null);
        Entity actual;

        try {
            expected = (Entity) bo.updateElement(expected);
            actual = (Entity) bo.findElement(expected, 1);

            printEntity(actual);

            assertEquals("Expected \n   " + expected + "\n but found \n" +
                    "   " + actual + "\n", expected, actual);
            assertNull(actual.getTransmitterIds());
            assertNull(actual.getTransmitters());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddTwoEntities() {

        Artikel artikel = new Artikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        Entity expected_1 = createEntity(null, "Baum", artikel, null);
        Entity actual_1;
        Entity expected_2 = createEntity(null, "Busch", artikel, null);
        Entity actual_2;

        try {
            expected_1 = (Entity) bo.updateElement(expected_1);
            actual_1 = (Entity) bo.findElement(expected_1, 1);

            printEntity(actual_1);

            assertEquals(expected_1, actual_1);
            assertNull(actual_1.getTransmitterIds());
            assertNull(actual_1.getTransmitters());

            expected_2 = (Entity) bo.updateElement(expected_2);
            actual_2 = (Entity) bo.findElement(expected_2, 1);

            printEntity(actual_2);

            assertEquals(expected_2, actual_2);
            assertNull(actual_2.getTransmitterIds());
            assertNull(actual_2.getTransmitters());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOneTransmitter() {

        Entity entityFrom = null;
        Entity entityTo = null;

        Transmitter transmitter = createTransmitter(entityFrom,
                "T_0001",
                Transmitter.RelationType.HAS,
                Transmitter.Quantity.N,
                20,
                new Date(),
                entityTo);

        try {
            transmitter = (Transmitter) bo.updateElement(transmitter);
            transmitter = (Transmitter) bo.findElement(transmitter, 1);
            List<String> lines = bo.readFile(transmitter.getId());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddEntityWithThreeTransmitters() {

        Entity entityFrom = null;
        Transmitter transmitter1 = null;
        Transmitter transmitter2 = null;
        Transmitter transmitter3 = null;
        Artikel artikel = new Artikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        try {
            entityFrom = createEntity("00004", "Tree", artikel, null);
            transmitter1 = createTransmitter(entityFrom, "T_1", Transmitter.RelationType.HAS, Transmitter.Quantity.N, 10, new Date(), null);
            entityFrom.addTransmitter(transmitter1);
            transmitter2 = createTransmitter(entityFrom, "T_2", Transmitter.RelationType.HAS, Transmitter.Quantity.EINS, 10, new Date(), null);
            entityFrom.addTransmitter(transmitter2);
            transmitter3 = createTransmitter(entityFrom, "T_3", Transmitter.RelationType.IS, null, 10, new Date(), null);
            entityFrom.addTransmitter(transmitter3);
//            bo.updateEntity(entityFrom);

//            List<String> lines = bo.readFile();
//            for (String line : lines) {
//                System.out.println(line);
//            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testAddEntityWithTwoTransmittersAndTwoToEntities() {

        Entity entityFrom = null;
        Entity entityTo1 = null;
        Entity entityTo2 = null;
        Transmitter transmitter1 = null;
        Transmitter transmitter2 = null;
        Artikel artikel1 = new Artikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);
        Artikel artikel2 = new Artikel(KasusType.NOMINATIV, GenusType.NEUTRUM, NumerusType.PLURAL);
        Artikel artikel3 = new Artikel(KasusType.NOMINATIV, GenusType.FEMININUM, NumerusType.SINGULAR);
        try {
            entityFrom = createEntity("00001", "Baum", artikel1, null);
            entityTo1 = createEntity("00002", "Blatt", artikel2, null);
            entityTo2 = createEntity("00003", "Borke", artikel3, null);
            transmitter1 = createTransmitter(entityFrom, "T_1", Transmitter.RelationType.HAS, Transmitter.Quantity.N, 10, new Date(), entityTo1);
            entityFrom.addTransmitter(transmitter1);
            transmitter2 = createTransmitter(entityFrom, "T_2", Transmitter.RelationType.HAS, Transmitter.Quantity.EINS, 10, new Date(), entityTo2);
            entityFrom.addTransmitter(transmitter2);
            System.out.println("... update...");
//            bo.updateEntity(entityFrom);
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

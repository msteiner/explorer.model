package org.ms.explorer.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ms.explorer.element.Artikel;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;
import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;

public abstract class TestTools {
    public TestTools() {
        super();
    }

    public Entity createEntity(String id, String name, List<Transmitter> transmitters) {

        Artikel artikel = createArtikel(KasusType.NOMINATIV, GenusType.MASKULINUM, NumerusType.SINGULAR);

        return createEntity(id, name, artikel, transmitters);
    }

    public Entity createEntity(String name, Artikel artikel, List<Transmitter> transmitters) {

        Entity entity = new Entity();

        entity.setName(name);
        entity.setArtikel(artikel);
        if (transmitters != null) {
            entity.addTransmitter(transmitters);
        }

        return entity;
    }

    public Entity createEntity(String id, String name, Artikel artikel, List<Transmitter> transmitters) {

        Entity entity = new Entity();

        entity.setId(id);
        entity.setName(name);
        entity.setArtikel(artikel);
        if (transmitters != null) {
            entity.addTransmitter(transmitters);
        }

        return entity;
    }

    public List<Transmitter> createTransmitters(Transmitter... transmitters) {

        List<Transmitter> list = new ArrayList<Transmitter>();

        for (Transmitter transmitter : transmitters) {
            list.add(transmitter);
        }

        return list;
    }

    public Transmitter createTransmitter() {
        return createTransmitter(null, "t_0", null, null, 0, new Date(), null);
    }

    public Transmitter createTransmitter(Entity entityFrom, String id, Transmitter.RelationType relationType,
                                         Transmitter.Quantity quantity, int weight, Date observed, Entity entityTo) {

        Transmitter transmitter = new Transmitter();

        transmitter.setFrom(entityFrom);
        transmitter.setTo(entityTo);
        transmitter.setId(id);
        transmitter.setRelationType(relationType);
        transmitter.setQuantity(quantity);
        transmitter.setWeight(weight);
        transmitter.setObserved(observed);

        return transmitter;
    }

    public Transmitter createTransmitter(Entity entityFrom, Transmitter.RelationType relationType,
                                         Transmitter.Quantity quantity, Entity entityTo) {

        Transmitter transmitter = new Transmitter();

        transmitter.setFrom(entityFrom);
        transmitter.setTo(entityTo);
        transmitter.setRelationType(relationType);
        transmitter.setQuantity(quantity);

        return transmitter;
    }

    public Transmitter createTransmitter(Entity entityFrom, Transmitter.RelationType relationType, Entity entityTo) {

        Transmitter transmitter = new Transmitter();

        transmitter.setFrom(entityFrom);
        transmitter.setTo(entityTo);
        transmitter.setRelationType(relationType);

        return transmitter;
    }

    public Artikel createArtikel(KasusType kasusType, GenusType genusType, NumerusType numerusType) {
        return new Artikel(kasusType, genusType, numerusType);
    }

    public static void printEntity(Entity entity) {
        System.out.println("+++ entity=" + entity);
        if (entity.hasTransmitters()) {
            for (Transmitter transmitter : entity.getTransmitters()) {
                System.out.println("   +++ transmitter=" + transmitter);
            }
        }
    }
}

package org.ms.explorer.element;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Entity extends Element implements Serializable {

    @SuppressWarnings("compatibility:-8524527822371079286")
        
    private static final long serialVersionUID = 1L;
    private String name;
    private Artikel artikel = null;
    private List<Transmitter> transmitters;
    private List<String> transmitterIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public List<Transmitter> getTransmitters() {
//        if (transmitters == null) {
//            transmitters = new ArrayList<>();
//        }
        return transmitters;
    }

    public boolean hasTransmitters() {

        if (transmitters != null && transmitters.size() > 0) {
            return true;
        }

        return false;
    }

    public void setTransmitters(List<Transmitter> transmitters) {
        this.transmitters = transmitters;
    }

    public void addTransmitter(Transmitter transmitter) {
        if (transmitters == null) {
            transmitters = new ArrayList<Transmitter>();
        }
        transmitters.add(transmitter);
    }

    public void addTransmitter(List<Transmitter> transmitters) {
        if (this.transmitters == null) {
            this.transmitters = new ArrayList<Transmitter>();
        }
        this.transmitters.addAll(transmitters);
    }

    public List<String> getTransmitterIds() {
        return transmitterIds;
    }

    public void setTransmitterIds(List<String> transmitterIds) {
        this.transmitterIds = transmitterIds;
    }

    public void addTransmitterId(String transmitterId) {
        if (transmitterIds == null) {
            transmitterIds = new ArrayList<String>();
        }
        transmitterIds.add(transmitterId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        if (artikel != null ? !artikel.equals(entity.artikel) : entity.artikel != null) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (transmitterIds != null ? !transmitterIds.equals(entity.transmitterIds) : entity.transmitterIds != null)
            return false;
        if (transmitters != null ? !transmitters.equals(entity.transmitters) : entity.transmitters != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (artikel != null ? artikel.hashCode() : 0);
        result = 31 * result + (transmitters != null ? transmitters.hashCode() : 0);
        result = 31 * result + (transmitterIds != null ? transmitterIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + getId() + '\'' +
                ", observed='" + getObserved() + '\'' +
                ", name='" + name + '\'' +
                ", artikel=" + artikel +
                ", transmitters=" + transmitters +
                '}';
    }
}
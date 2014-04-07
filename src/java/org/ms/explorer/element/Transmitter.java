package org.ms.explorer.element;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transmitter extends Element implements Serializable {

    @SuppressWarnings("compatibility:-2870515226928661622")
        
    private static final long serialVersionUID = 1L;
    private Entity from;
    private Entity to;
    private RelationType relationType;
    private int weight;
    private Quantity quantity;
    private Date observed;

    public enum RelationType {

        HAS(0), IS(1), IS_ELEMENT_OF(2);

        private int value;
        private static List<RelationType> list;

        static {
            list = new ArrayList<RelationType>();

            list.add(HAS);
            list.add(IS);
            list.add(IS_ELEMENT_OF);
        }

        RelationType(int value) {
            this.value = value;
        }

        public static RelationType getRelationType(int value) {

            for (RelationType relationType : list) {
                if (relationType.value == value) {
                    return relationType;
                }
            }

            return null;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Quantity {

        NULL(0), EINS(1), N(2);

        private int value;
        private static List<Quantity> list;

        static {
            list = new ArrayList<Quantity>();

            list.add(NULL);
            list.add(EINS);
            list.add(N);
        }

        Quantity(int value) {
            this.value = value;
        }

        public static Quantity getQuantity(int value) {

            for (Quantity quantity : list) {
                if (quantity.value == value) {
                    return quantity;
                }
            }

            return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    public Entity getFrom() {
        return from;
    }

    public void setFrom(Entity from) {
        this.from = from;
    }

    public Entity getTo() {
        return to;
    }

    public void setTo(Entity to) {
        this.to = to;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Transmitter{" +
                "id='" + getId() + '\'' +
                ", observed=" + getObserved() +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", relationType=" + relationType +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
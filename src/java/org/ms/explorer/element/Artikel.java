package org.ms.explorer.element;

import java.io.Serializable;

import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;

public class Artikel implements Serializable {

    @SuppressWarnings("compatibility:5396513988177510266")
        
    private static final long serialVersionUID = 1L;
    private KasusType kasus;
    private GenusType genus;
    private NumerusType numerus;

    public Artikel() {
        super();
    }

    public Artikel(KasusType kasus, GenusType genus, NumerusType numerus) {
        this.kasus = kasus;
        this.genus = genus;
        this.numerus = numerus;
    }

    public KasusType getKasus() {
        return kasus;
    }

    public void setKasus(KasusType kasus) {
        this.kasus = kasus;
    }

    public GenusType getGenus() {
        return genus;
    }

    public void setGenus(GenusType genus) {
        this.genus = genus;
    }

    public NumerusType getNumerus() {
        return numerus;
    }

    public void setNumerus(NumerusType numerus) {
        this.numerus = numerus;
    }

    @Override
    public String toString() {
        return "ArtikelType{" + "kasus=" + kasus + ", genus=" + genus + ", numerus=" + numerus + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return true;
    }
}

package org.ms.explorer.element;

import java.util.Date;
import java.util.UUID;

public abstract class Element {
    
    public Element() {
        super();
        observed = new Date();
    }
    
    private String id;
    private Date observed;
    
    public String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getObserved() {
        return observed;
    }

    public void setObserved(Date observed) {
        this.observed = observed;
    }
    
    public void markObserved() {
        this.observed = new Date();
    }
}

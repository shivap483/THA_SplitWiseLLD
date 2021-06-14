package org.com.splitwise.Models;


import java.util.Date;
import java.util.Objects;


public abstract class Auditable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditable auditable = (Auditable) o;
        return id == auditable.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private long id;
    private Date created;
    private Date modified;


}

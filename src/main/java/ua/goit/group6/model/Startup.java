package ua.goit.group6.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "startup")
public class Startup extends News{

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Startup)) return false;
        if (!super.equals(o)) return false;

        Startup startup = (Startup) o;

        return getName() != null ? getName().equals(startup.getName()) : startup.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Startup{ "  +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}

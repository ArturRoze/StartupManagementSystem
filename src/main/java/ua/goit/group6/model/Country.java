package ua.goit.group6.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country")
public class Country extends Model{

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
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        return getName() != null ? getName().equals(country.getName()) : country.getName() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "Country{" + super.toString() +
                "name='" + name + '\'' +
                "} " ;
    }
}

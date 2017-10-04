package ua.goit.group6.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class Region extends Model{

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
        if (!(o instanceof Region)) return false;
        if (!super.equals(o)) return false;

        Region region = (Region) o;

        return getName() != null ? getName().equals(region.getName()) : region.getName() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "Region{" + super.toString()+
                "name='" + name + '\'' +
                "} " ;
    }
}

package ua.goit.group6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "industry")
public class Industry extends Model {

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
        if (!(o instanceof Industry)) return false;
        if (!super.equals(o)) return false;

        Industry industry = (Industry) o;

        return getName() != null ? getName().equals(industry.getName()) : industry.getName() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "Industry{" + super.toString() +
                "name='" + name + '\'' +
                "} ";
    }
}

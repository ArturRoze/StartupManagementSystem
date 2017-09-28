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
    public String toString() {
        return "Region{" + super.toString()+
                "name='" + name + '\'' +
                "} " ;
    }
}

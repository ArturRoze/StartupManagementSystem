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
    public String toString() {
        return "Country{" + super.toString() +
                "name='" + name + '\'' +
                "} " ;
    }
}

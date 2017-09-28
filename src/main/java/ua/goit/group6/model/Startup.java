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
    public String toString() {
        return "Startup{ "  +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}

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
    public String toString() {
        return "Industry{" + super.toString() +
                "name='" + name + '\'' +
                "} ";
    }
}

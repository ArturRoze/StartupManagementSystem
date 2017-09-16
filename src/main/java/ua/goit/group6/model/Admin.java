package ua.goit.group6.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends BasicUser {

    @Override
    public String toString() {
        return "Admin{} " + super.toString();
    }
}

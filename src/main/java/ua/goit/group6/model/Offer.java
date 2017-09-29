package ua.goit.group6.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer extends News {

    @Override
    public String toString() {
        return "Offer{} " + super.toString();
    }
}

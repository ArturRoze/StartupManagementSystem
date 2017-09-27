package ua.goit.group6.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BasicUser {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Startup> startups;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Startup> getStartups() {
        return startups;
    }

    public void setStartups(List<Startup> startups) {
        this.startups = startups;
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", country=" + country +
                ", startups=" + startups.size() +
                "} " ;
    }
}

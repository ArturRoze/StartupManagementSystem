package ua.goit.group6.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    private Set<Startup> startups = new TreeSet<>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Offer> offers = new TreeSet<>();

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

    public Set<Startup> getStartups() {
        return startups;
    }

    public void setStartups(Set<Startup> startups) {
        this.startups = startups;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(user.getDescription()) : user.getDescription() != null)
            return false;
        if (getCountry() != null ? !getCountry().getName().equals(user.getCountry().getName()) : user.getCountry().getName() != null) return false;
        if (getStartups() != null ? (getStartups().size() != user.getStartups().size()) : user.getStartups() != null)
            return false;
        return getOffers() != null ? getOffers().size() == user.getOffers().size() : user.getOffers() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().getName().hashCode() : 0);
        result = 31 * result + (getStartups() != null ? getStartups().size() : 0);
        result = 31 * result + (getOffers() != null ? getOffers().size() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{ " + super.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", country=" + (country != null ? country.getName() : null)  +
                ", startups=" + startups.size() +
                ", offers=" + offers.size() +
                "} " ;
    }
}

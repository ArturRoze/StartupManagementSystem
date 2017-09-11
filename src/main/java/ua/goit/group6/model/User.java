package ua.goit.group6.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BasicUser {
    @Column(name = "capital")
    private BigDecimal capital;

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "startupOwners")
    private Set<StartUp> userStartUps = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contributor")
    private Set<Contribution> contributions = new HashSet<>();

    public User() {
    }

    public Set<StartUp> getUserStartUps() {
        return userStartUps;
    }

    public void setUserStartUps(Set<StartUp> userStartUps) {
        this.userStartUps = userStartUps;
    }

    public Set<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }
}

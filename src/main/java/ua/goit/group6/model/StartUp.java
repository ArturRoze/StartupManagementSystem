package ua.goit.group6.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "startup")
public class StartUp {

    @Id
    private String name;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @Column(name = "budget")
    private BigDecimal budget;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "startup_owners",
            joinColumns = @JoinColumn(name = "startup_name"),
            inverseJoinColumns = @JoinColumn(name = "login_email"))
    private Set<User> startupOwners = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "startUp")
    private Set<Contribution> contributions = new HashSet<>();

    public StartUp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Set<User> getStartupOwners() {
        return startupOwners;
    }

    public void setStartupOwners(Set<User> startupOwners) {
        this.startupOwners = startupOwners;
    }

    public Set<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }


}

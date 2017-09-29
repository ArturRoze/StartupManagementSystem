package ua.goit.group6.model;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class News extends Model {

    @Column
    private String description;

    @Column
    private int budget;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column (name = "registration_date")
    private Timestamp registrationDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", user=" + user.getLogin() +
                ", country=" + country.getName() +
                ", industry=" + industry.getName() +
                ", registrationDate=" + registrationDate +
                "} " ;
    }
}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        if (!super.equals(o)) return false;

        News news = (News) o;

        if (getBudget() != news.getBudget()) return false;
        if (getDescription() != null ? !getDescription().equals(news.getDescription()) : news.getDescription() != null)
            return false;
        if (getUser() != null ? !getUser().equals(news.getUser()) : news.getUser() != null) return false;
        if (getCountry() != null ? !getCountry().equals(news.getCountry()) : news.getCountry() != null) return false;
        if (getIndustry() != null ? !getIndustry().equals(news.getIndustry()) : news.getIndustry() != null)
            return false;
        return getRegistrationDate() != null ? getRegistrationDate().equals(news.getRegistrationDate()) : news.getRegistrationDate() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
//        result = 31 * result + getBudget();
//        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
//        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
//        result = 31 * result + (getIndustry() != null ? getIndustry().hashCode() : 0);
//        result = 31 * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return super.toString() +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", user=" + user.getLogin() +
                ", country=" + (country != null ? country.getName() : null) +
                ", industry=" + (industry != null ? industry.getName() : null) +
                ", registrationDate=" + registrationDate +
                "} " ;
    }
}

package ua.goit.group6.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "contribution")
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "transaction_time")
    private Timestamp timeTransaction;

    @Column
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_email")
    private User contributor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "startup_name")
    private StartUp startUp;

    public Contribution() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(Timestamp timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getContributor() {
        return contributor;
    }

    public void setContributor(User contributor) {
        this.contributor = contributor;
    }

    public StartUp getStartUp() {
        return startUp;
    }

    public void setStartUp(StartUp startUp) {
        this.startUp = startUp;
    }
}

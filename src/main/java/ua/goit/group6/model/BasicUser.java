package ua.goit.group6.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BasicUser extends Model{

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column (name = "registration_date")
    private Timestamp registrationDate;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof BasicUser)) return false;
        if (!super.equals(o)) return false;

        BasicUser basicUser = (BasicUser) o;

        if (getLogin() != null ? !getLogin().equals(basicUser.getLogin()) : basicUser.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(basicUser.getPassword()) : basicUser.getPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(basicUser.getEmail()) : basicUser.getEmail() != null) return false;
        return getRegistrationDate() != null ? getRegistrationDate().equals(basicUser.getRegistrationDate()) : basicUser.getRegistrationDate() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
//        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
//        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
//        result = 31 * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "BasicUser{" + super.toString() +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                "} " ;
    }
}

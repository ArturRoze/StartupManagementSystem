package ua.goit.group6.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides information about user
 *
 * @author Boiko Ivan
 * @see UserDetails
 * @see User
 */
public class UserDetailed implements UserDetails {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailed.class);

    private BasicUser user;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public UserDetailed(User user) {
        LOGGER.info("Create UserDetailed wrapping around {}", user);
        this.user = user;
        this.grantedAuthorities = new ArrayList<>();
        LOGGER.trace("Setting authorities");
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public UserDetailed(Admin admin) {
        LOGGER.info("Create UserDetailed wrapping around {}", admin);
        this.user = admin;
        this.grantedAuthorities = new ArrayList<>();
        LOGGER.trace("Setting authorities");
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        LOGGER.trace("Getting password");
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        LOGGER.trace("Getting login");
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        LOGGER.trace("Setting isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        LOGGER.trace("Setting isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LOGGER.trace("Setting isCredentialsNonExpired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        LOGGER.trace("Setting isEnabled");
        return !getAuthorities().isEmpty();
    }

    public long getId(){
        return user.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailed)) return false;

        UserDetailed that = (UserDetailed) o;

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserDetailed{" +
                "user=" + user +
                ", grantedAuthorities=" + getAuthorities() +
                '}';
    }
}

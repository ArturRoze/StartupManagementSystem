package ua.goit.group6.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides information about admin
 *
 * @author Boiko Ivan
 * @see UserDetails
 * @see Admin
 */
public class AdminDetailed implements UserDetails {


    private final Logger LOGGER = LoggerFactory.getLogger(AdminDetailed.class);

    private Admin admin;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public AdminDetailed(Admin admin) {
        LOGGER.info("Create AdminDetailed wrapping around {}", admin);
        this.admin = admin;
        this.grantedAuthorities = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LOGGER.trace("Setting authorities");
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        LOGGER.trace("Getting password");
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        LOGGER.trace("Getting login");
        return admin.getLogin();
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
        return admin.getId();
    }

    @Override
    public String toString() {
        return "AdminDetailed{" +
                "admin=" + admin +
                ", grantedAuthorities=" + getAuthorities() +
                '}';
    }
}

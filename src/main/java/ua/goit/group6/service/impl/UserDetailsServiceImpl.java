package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.AdminDetailed;
import ua.goit.group6.model.User;
import ua.goit.group6.model.UserDetailed;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

/**
 * Provides information about user
 *
 * @author Boiko Ivan
 * @see UserDetailsService
 * @see UserService
 * @see AdminService
 * @see UserDetailed
 * @see AdminDetailed
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
        LOGGER.info("UserDetailsServiceImpl created");
    }

    /**
     * Implementation of {@link UserDetailsService} method
     * 
     * @param username login of logging in user
     * @return a {@link AdminDetailed} if such {@link Admin} exists or
     *          {@link UserDetailed} if such {@link User} exists
     * @throws UsernameNotFoundException if neither admin nor user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("Loading user by username:'{}'", username);

        Admin admin = adminService.getByLogin(username);

        if (admin != null) {
            return new AdminDetailed(admin);
        } else {
            LOGGER.info("Admin with login:'{}' not found", username);
        }

        User user = userService.getByLogin(username);

        if (user != null)
            return new UserDetailed(user);
        else {
            LOGGER.info("User with login:'{}' not found", username);
            throw new UsernameNotFoundException("User with login '" + username + "' not exists");
        }
    }
}

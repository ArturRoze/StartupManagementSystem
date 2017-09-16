package ua.goit.group6.service.impl;

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
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminService.getByLogin(username);

        if (admin != null)
            return new AdminDetailed(admin);

        User user = userService.getByLogin(username);

        if (user != null)
            return new UserDetailed(user);
        else throw new UsernameNotFoundException("User with login '" + username + "' not exists");
    }
}

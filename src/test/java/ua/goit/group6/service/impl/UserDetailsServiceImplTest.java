package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.model.UserDetailed;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        MockitoAnnotations.initMocks(UserDetailsServiceImpl.class);
    }

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private AdminService adminService;

    @Mock
    private UserService userService;

    private UserDetails details;

    @Test
    public void loadUserByUsernameAdminIsNotNull() {

        Admin admin = mock(Admin.class);

        when(adminService.getByLogin(anyString())).thenReturn(admin);

        details = userDetailsService.loadUserByUsername(anyString());

        assertNotNull(details);

    }

    @Test
    public void loadUserByUsernameAdminIsNullUserIsNotNull() {

        User user = mock(User.class);

        when(adminService.getByLogin(anyString())).thenReturn(null);
        when(userService.getByLogin(anyString())).thenReturn(user);

        details = userDetailsService.loadUserByUsername(anyString());

        assertNotNull(details);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameAdminIsNullUserIsNull() {

        when(adminService.getByLogin(anyString())).thenReturn(null);
        when(userService.getByLogin(anyString())).thenReturn(null);

        userDetailsService.loadUserByUsername(anyString());
    }

    @Ignore //TODO do it
    @Test
    public void loadUserByUsernameAdminIsNotNullUserIsNotNull() {

        Admin admin = mock(Admin.class);
        User user = mock(User.class);

        String username = anyString();

        when(adminService.getByLogin(username)).thenReturn(admin);
        when(userService.getByLogin(username)).thenReturn(user);

        UserDetails userDetailed = new UserDetailed(adminService.getByLogin(username));

        details = userDetailsService.loadUserByUsername(username);

        assertEquals(userDetailed, details);
    }

}
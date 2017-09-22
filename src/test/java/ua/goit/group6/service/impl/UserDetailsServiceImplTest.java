package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import static org.junit.Assert.*;
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
    UserDetailsServiceImpl userDetailsService;

    @Mock
    AdminService adminService;

    @Mock
    UserService userService;

    @Test
    public void loadByUserNameAdminIsNull() {

        Admin admin = mock(Admin.class);

        when(adminService.getByLogin(anyString())).thenReturn(admin);

        UserDetails details = userDetailsService.loadUserByUsername(anyString());

        assertNotNull(details);

    }

}
package ua.goit.group6.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.group6.configuration.MvcConfiguration;
import ua.goit.group6.configuration.SecurityConfiguration;
import ua.goit.group6.controller.configuration.TestControllersConfiguration;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for main controller
 *
 * @author Boiko Ivan
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestControllersConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class MainControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StartupService startupService;

    @Autowired
    private WebApplicationContext context;

    private User user;
    private Admin admin;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        admin = mock(Admin.class);

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void guestIndexTest() throws Exception {
        mvc.perform(get("/").with(anonymous()))
                .andExpect(model().attribute("startups", equalTo(startupService.getLastNDesc(anyInt()))))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestLoginTest() throws Exception {
        mvc.perform(get("/login").with(anonymous()))
                .andExpect(view().name("login_form"))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore //TODO complete test
    public void guestRegistrationTest() throws Exception {
//        userService.save(user);
//        verify(userService, times(1)).save(user);

        mvc.perform(post("registration").with(anonymous()))
                .andExpect(view().name("registration_form"))
//                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestNewsTest() throws Exception {
        mvc.perform(get("/news").with(anonymous()))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().isFound());
    }

    @Test
    public void guestLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(anonymous()))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Test
    public void authenticatedIndexTest() throws Exception {
        mvc.perform(get("/").with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("startups", equalTo(startupService.getLastNDesc(anyInt()))))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticatedLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(user("user").roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Test
    //TODO finish after creating startups and offers
    public void authenticatedNewsTest() throws Exception {
        mvc.perform(get("/news").with(user("user").roles("ADMIN", "USER")))
                //TODO startups and offers
                .andExpect(view().name("news"))
                .andExpect(status().isOk());
    }

    @Test
    public void initDefaultUsersTest() throws Exception {
        userService.save(user);
        verify(userService, times(1)).save(user);

        adminService.save(admin);
        verify(adminService, times(1)).save(admin);
    }
}
package ua.goit.group6.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.group6.configuration.MvcConfiguration;
import ua.goit.group6.configuration.SecurityConfiguration;
import ua.goit.group6.controller.configuration.TestControllersConfiguration;
import ua.goit.group6.model.Country;
import ua.goit.group6.model.User;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.UserService;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Boiko Ivan
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestControllersConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private WebApplicationContext context;

    private User user;

    private Integer id;

    private Country country;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        id = 1;
        country = mock(Country.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //Guest tests

    @Test
    public void guestUserProfileTest() throws Exception {
        mvc.perform(get("/users/profile/" + id).with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void guestUserDeleteTest() throws Exception {
        mvc.perform(get("/users/profile/{}/delete", id).with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void guestUserProfileUpdateFormTest() throws Exception {
        mvc.perform(get("/users/profile/" + id + "/edit").with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void guestUserProfileUpdateTest() throws Exception {
        mvc.perform(post("/users/profile/" + id + "/update").with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void guestUsersListTest() throws Exception {
        mvc.perform(get("/users").with(anonymous()))
                .andExpect(status().isFound());
    }

    //Authenticated users tests

    @Test
    public void profileTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);

        mvc.perform(get("/users/profile/" + id).with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("user", equalTo(userService.getById(id))))
                .andExpect(view().name("user_profile"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mvc.perform(get("/users/profile/" + id + "/delete").with(user("user").roles("USER", "ADMIN")))
                .andExpect(redirectedUrl("/logout"))
                .andExpect(status().isFound());
    }

    @Test
    public void updateFormTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);
        when(countryService.getAll()).thenReturn(Collections.singletonList(country));

        mvc.perform(get("/users/profile/" + id + "/edit").with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("user", equalTo(userService.getById(id))))
                .andExpect(model().attribute("countries", equalTo(countryService.getAll())))
                .andExpect(view().name("user_update_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);

        mvc.perform(post("/users/profile/" + id + "/update").with(user("admin").roles("USER", "ADMIN"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("password", "pass")
                .param("email", "email")
                .param("first_name", "s")
                .param("last_name", "a")
                .param("description", "email")
                .param("country_id", ""))

                .andExpect(redirectedUrl("/users/profile/" + id))
                .andExpect(status().isFound());
    }

    @Test
    public void userUsersListTest() throws Exception {
        mvc.perform(get("/users/list").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void adminUsersListTest() throws Exception {
        when(userService.getAll()).thenReturn(Collections.singletonList(user));

        mvc.perform(get("/users/list").with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("users", equalTo(userService.getAll())))
                .andExpect(view().name("users_list"))
                .andExpect(status().isOk());
    }
}
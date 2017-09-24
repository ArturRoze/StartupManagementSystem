package ua.goit.group6.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.group6.configuration.MvcConfiguration;
import ua.goit.group6.configuration.SecurityConfiguration;
import ua.goit.group6.controller.configuration.TestControllersConfiguration;
import ua.goit.group6.model.City;
import ua.goit.group6.model.Country;
import ua.goit.group6.model.User;
import ua.goit.group6.service.CityService;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.UserService;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private CityService cityService;

    @Autowired
    private WebApplicationContext context;

    private User user;
    private Long id;

    private Country country;
    private City city;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        id = 1L;
        country = mock(Country.class);
        city = mock(City.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void guestProfileTest() throws Exception {
        String url = "/users/profile/" + id;
        mvc.perform(get(url).with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void profileTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);

        mvc.perform(get("/users/profile/" + id).with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("user", userService.getById(id)))
                .andExpect(view().name("profile"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestDeleteTest() throws Exception {
        mvc.perform(get("/users/profile/" + id + "/delete").with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void deleteTEst() throws Exception {
        mvc.perform(get("/users/profile/" + id + "/delete").with(user("user").roles("USER", "ADMIN")))
                .andExpect(redirectedUrl("/logout"))
                .andExpect(status().isFound());
    }

    @Test
    public void guestProfileUpdateFormTest() throws Exception {
        String url = "/users/profile/" + id + "/update";
        mvc.perform(get(url).with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void updateFormTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);
        when(countryService.getAll()).thenReturn(Collections.singletonList(country));

        mvc.perform(get("/users/profile/" + id + "/update").with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("user", userService.getById(id)))
                .andExpect(model().attribute("countries", countryService.getAll()))
                .andExpect(view().name("user_update_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
    }

    @Test
    public void guestListTest() throws Exception {
        mvc.perform(get("/users").with(anonymous()))
                .andExpect(status().isFound());
    }

    @Test
    public void userListTest() throws Exception {
        mvc.perform(get("/users").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void adminListTest() throws Exception {
        when(userService.getAll()).thenReturn(Collections.singletonList(user));

        mvc.perform(get("/users").with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("users", userService.getAll()))
                .andExpect(view().name("users_list"))
                .andExpect(status().isOk());
    }
}
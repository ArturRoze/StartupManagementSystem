package ua.goit.group6.controller;

import org.junit.Before;
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
import ua.goit.group6.model.Startup;
import ua.goit.group6.model.User;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.IndustryService;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        MvcConfiguration.class,
        SecurityConfiguration.class,
        TestControllersConfiguration.class})
public class StartupControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StartupService startupService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private UserService userService;

    private Startup startup;

    private Integer id;

    private User user;

    @Before
    public void setUp() throws Exception {
        startup = mock(Startup.class);
        id = 1;
        user = mock(User.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void listStartupsTest() throws Exception {
        when(startupService.getAll()).thenReturn(Collections.singletonList(startup));

        mvc.perform(get("/startups").with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("startups", equalTo(startupService.getAllByRegistration())))
                .andExpect(view().name("startups_list"))
                .andExpect(status().isOk());
    }

    @Test
    public void infoStartupTest() throws Exception {
        when(startupService.getById(id)).thenReturn(startup);

        mvc.perform(get("/startups/" + id).with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("startup", equalTo(startupService.getById(id))))
                .andExpect(view().name("startup_info"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStartupByIdTest() throws Exception {
        mvc.perform(get("/startups/" + id + "/delete").with(user("user").roles("USER", "ADMIN")))
                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }

    @Test
    public void newStartupFormTest() throws Exception {
        when(startupService.getById(id)).thenReturn(startup);

        mvc.perform(get("/startups/new/startup").with(user("user").roles("USER")))
                .andExpect(model().attribute("startup", equalTo(countryService.getById(id))))
                .andExpect(model().attribute("startup", equalTo(industryService.getById(id))))
                .andExpect(view().name("startup_add_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void createStartupTest() throws Exception {
        when(userService.getById(id)).thenReturn(user);

        mvc.perform(post("/startups/new/startup/").with(user("user").roles("USER"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("user_id", "1")
                .param("name", "aa")
                .param("budget", "11")
                .param("description", "desc")
                .param("country_id", "")
                .param("industry_id", ""))

                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }

    @Test
    public void updateStartupFormTest() throws Exception {
        when(startupService.getById(id)).thenReturn(startup);

        mvc.perform(get("/startups/" + id + "/edit").with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("startup", equalTo(startupService.getById(id))))
                .andExpect(model().attribute("countries", equalTo(countryService.getAll())))
                .andExpect(model().attribute("industries", equalTo(industryService.getAll())))
                .andExpect(view().name("startup_update_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStartupTest() throws Exception {
        when(startupService.getById(id)).thenReturn(startup);

        mvc.perform(post("/startups/" + id + "/update").with(user("user").roles("USER", "ADMIN"))
                .param("name", "A")
                .param("budget", "1")
                .param("description", "description")
                .param("country_id", "1")
                .param("industry_id", "1"))

                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }
}
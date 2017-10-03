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
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.NewsService;
import ua.goit.group6.service.StartupService;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    private StartupService startupService;
    @Autowired
    private NewsService newsService;

    @Autowired
    private WebApplicationContext context;

    private int newsOnPage = 1;

    private Startup startup;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //Guest tests

    @Ignore //TODO not correct work
    @Test
    public void guestIndexTest() throws Exception {
//        startup = mock(Startup.class);
//
//        when(startupService.getLastNDesc(newsOnPage)).thenReturn(Collections.singletonList(startup));

        mvc.perform(get("/").with(anonymous()))
//                .andExpect(model().attribute("startups", equalTo(Collections.singletonList(startup))))
                .andExpect(model().attribute("startups", equalTo(startupService.getLastNDesc(newsOnPage))))
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
    public void guestLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(anonymous()))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Test
    public void guestRegistrationFormTest() throws Exception {
        mvc.perform(get("/registration").with(anonymous()))
                .andExpect(view().name("registration_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestRegistrationTest() throws Exception {
        mvc.perform(post("/register").with(anonymous())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("login", "login")
                .param("password", "pass"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(status().isFound());
    }

    @Test
    public void guestNewsTest() throws Exception {
        mvc.perform(get("/news").with(anonymous()))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().isFound());
    }

    //Authenticated tests

    @Test
    public void authenticatedIndexTest() throws Exception {
        mvc.perform(get("/").with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("startups", equalTo(startupService.getLastNDesc(newsOnPage))))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticatedLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(user("user").roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Ignore //TODO complete test
    @Test
    public void authenticatedNewsTest() throws Exception {
        when(newsService.getCountOfPages(newsOnPage)).thenReturn(1);
        int pagesCount = newsService.getCountOfPages(newsOnPage);

        mvc.perform(get("/news").with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("current_page", 1))
                .andExpect(model().attribute("pages_count", equalTo(pagesCount)))
                .andExpect(model().attribute("news_list", equalTo(newsService.getNPageWithMNews(1, newsOnPage))))
                .andExpect(view().name("news"))
                .andExpect(status().isOk());
    }

}
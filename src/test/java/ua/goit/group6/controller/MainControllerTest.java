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
import ua.goit.group6.model.Offer;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.NewsService;
import ua.goit.group6.service.StartupService;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
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

    private int pageNumber;
    private int newsOnPage;

    private Startup startup;
    private Offer offer;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //Guest tests

    @Test
    public void guestIndexTest() throws Exception {

        mvc.perform(get("/").with(anonymous()))
                .andExpect(model().attribute("startups", startupService.getLastNDesc(anyInt())))
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
    public void authenticatedNewsTest_onePage() throws Exception {

        startup = mock(Startup.class);
        offer = mock(Offer.class);

        pageNumber = 2;
        newsOnPage = 2;

        when(newsService.getAll()).thenReturn(Arrays.asList(startup, startup, offer));

        when(newsService.getCountOfPages(newsOnPage)).thenReturn(2);

        int pagesCount = newsService.getCountOfPages(newsOnPage);

        assertEquals(2, pagesCount);

        mvc.perform(get("/news").with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("current_page", pageNumber))
//                .andExpect(model().attribute("pages_count", pagesCount))
                .andExpect(model().attribute("news_list", equalTo(newsService.getNPageWithMNews(pageNumber, newsOnPage))))
                .andExpect(view().name("news"))
                .andExpect(status().isOk());
    }

}
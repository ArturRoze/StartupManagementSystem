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
import ua.goit.group6.model.User;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.IndustryService;
import ua.goit.group6.service.OfferService;
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
public class OfferControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private OfferService offerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private UserService userService;

    private Offer offer;

    private Integer id;

    private User user;

    @Before
    public void setUp() throws Exception {
        offer = mock(Offer.class);
        id = 1;
        user = mock(User.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void infoOfferTest() throws Exception {
        when(offerService.getById(id)).thenReturn(offer);

        mvc.perform(get("/offers/" + id).with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("offer", equalTo(offerService.getById(id))))
                .andExpect(view().name("offer_info"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOfferTest() throws Exception {
        mvc.perform(get("/offers/" + id + "/delete").with(user("user").roles("USER", "ADMIN")))
                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }

    @Test
    public void newOfferTest() throws Exception {
        when(offerService.getById(id)).thenReturn(offer);

        mvc.perform(get("/offers/new/offer").with(user("user").roles("USER")))
                .andExpect(model().attribute("offer", equalTo(countryService.getById(id))))
                .andExpect(model().attribute("offer", equalTo(industryService.getById(id))))
                .andExpect(view().name("offer_add_form"))
                .andExpect(status().isOk());
    }

    @Test
    public void createOfferTest() throws Exception {

        when(userService.getById(id)).thenReturn(user);
        when(user.getId()).thenReturn(id);

        mvc.perform(post("/offers/new/offer/").with(user("user").roles("USER"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("user_id", "1")
                .param("budget", "11")
                .param("description", "desc")
                .param("country_id", "")
                .param("industry_id", ""))

                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }

    @Test
    public void updateOfferFormTest() throws Exception {
        when(offerService.getById(id)).thenReturn(offer);

        mvc.perform(get("/offers/" + id + "/edit").with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("offer", equalTo(offerService.getById(id))))
                .andExpect(model().attribute("countries", equalTo(countryService.getAll())))
                .andExpect(model().attribute("industries", equalTo(offerService.getAll())))
                .andExpect(view().name("offer_update_form"))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore //TODO
    public void updateOfferTest() throws Exception {
        mvc.perform(post("/offers/" + id + "/update").with(user("user").roles("USER", "ADMIN"))
                .param("budget", "budget")
                .param("description", "description")
                .param("country_id", "country_id")
                .param("industry_id", "industry_id"))

                .andExpect(redirectedUrl("/news"))
                .andExpect(status().isFound());
    }
}
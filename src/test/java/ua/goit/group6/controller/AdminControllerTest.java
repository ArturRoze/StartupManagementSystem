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
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;

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
public class AdminControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AdminService adminService;

    private Admin admin;

    private Integer id;

    @Before
    public void setUp() throws Exception {
        admin = mock(Admin.class);
        id = 1;
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void profileAdminTest() throws Exception {
        when(adminService.getById(id)).thenReturn(admin);

        mvc.perform(get("/admins/profile/" + id).with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("admin", equalTo(adminService.getById(id))))
                .andExpect(view().name("admin_profile"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAdminTest() throws Exception {
        mvc.perform(get("/admins/profile/" + id + "/delete").with(user("admin").roles("ADMIN")))
                .andExpect(redirectedUrl("/logout"))
                .andExpect(status().isFound());
    }

    @Test
    public void saveAdminTest() throws Exception {
        mvc.perform(post("/admins/new/admin/").with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("password", "pass")
                .param("email", "email")
                .param("login", "login"))

                .andExpect(redirectedUrl("/admins/list"))
                .andExpect(status().isFound());
    }

    @Test
    public void updateAdminFormTest() throws Exception {
        when(adminService.getById(id)).thenReturn(admin);

        mvc.perform(get("/admins/profile/" + id + "/update").with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("admin", equalTo(adminService.getById(id))))
                .andExpect(view().name("admin_update_form"))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore //TODO
    public void updateAdminTest() throws Exception {
        when(admin.getId()).thenReturn(id);
        mvc.perform(post("/admins/profile/" + id + "/update/").with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("password", "pass")
                .param("email", "email"))

                .andExpect(redirectedUrl("/admins/profile/" + admin.getId()))
                .andExpect(status().isFound());
    }

    @Test
    public void adminListTest() throws Exception {
        when(adminService.getAll()).thenReturn(Collections.singletonList(admin));

        mvc.perform(get("/admins/list").with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("admins", equalTo(adminService.getAll())))
                .andExpect(view().name("admins_list"))
                .andExpect(status().isOk());
    }
}

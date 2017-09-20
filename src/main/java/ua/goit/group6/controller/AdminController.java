package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;

import java.io.IOException;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating admin controller");
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") String idString) {
        ModelAndView profile = new ModelAndView("profile");
        long id = Long.parseLong(idString);
        Admin admin = adminService.getById(id);
        profile.addObject("admin", admin);
        return profile;
    }

    @GetMapping("/profile/{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        long id = Long.parseLong(idString);
        adminService.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("profile/{id}/update")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("update_form");
        long id = Long.parseLong("id");
        Admin admin = adminService.getById(id);
        updateForm.addObject("admin", admin);
        return updateForm;
    }

    @PostMapping(value = "/profile/{id}/update/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@PathVariable("id") String idString,
                         @RequestParam("login") String login,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email)

            throws IOException {
        Admin admin = new Admin();
        admin.setId(Long.parseLong(idString));
        admin.setLogin(login);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setEmail(email);
        return "redirect:admins/profile/{id}";
    }


    @GetMapping
    public ModelAndView list() {
        ModelAndView admins = new ModelAndView("admins_list");
        admins.addObject("admins", adminService.getAll());
        return admins;
    }

}

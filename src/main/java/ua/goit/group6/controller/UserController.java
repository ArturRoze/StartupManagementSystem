package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.User;
import ua.goit.group6.service.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating user controller");
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") String idString) {
        ModelAndView profile = new ModelAndView("profile");
        long id = Long.parseLong(idString);
        User user = userService.getById(id);
        profile.addObject("user", user);
        return profile;
    }

    @GetMapping("/profile/{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        long id = Long.parseLong(idString);
        User user = userService.getById(id);
        userService.delete(user);
        //TODO make deleteById(id)
        return "redirect:/";
    }

    @GetMapping("/profile/{id}/update")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("update_form");
        long id = Long.parseLong(idString);
        User user = userService.getById(id);
        updateForm.addObject("user", user);
        return updateForm;
    }


}

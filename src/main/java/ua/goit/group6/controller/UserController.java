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
import ua.goit.group6.service.CityService;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final CountryService countryService;

    private final CityService cityService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, CountryService countryService, CityService cityService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating user controller");
        this.userService = userService;
        this.countryService = countryService;
        this.cityService = cityService;
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
        updateForm.addObject("countries", countryService.getAll());
        updateForm.addObject("cities", cityService.getAll());
        return updateForm;
    }

    @PostMapping(value = "/profile/{id}/update/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@PathVariable("id") String idString,
                         @RequestParam("password") String password,
                         @RequestParam("first_name") String firstName,
                         @RequestParam("last_name") String lastName,
                         @RequestParam("description") String description,
                         @RequestParam("country_id") String countryIdString,
                         @RequestParam("city_id") String cityIidString)
            throws IOException {
        User user = new User();
        user.setId(Long.parseLong(idString));
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDescription(description);
        user.setCountry(countryService.getById(Long.parseLong(countryIdString)));
        user.setCity(cityService.getById(Long.parseLong(cityIidString)));
        return "redirect:users/profile/{id}";
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView users = new ModelAndView("users_list");
        users.addObject("users", userService.getAll());
        return users;
    }
}

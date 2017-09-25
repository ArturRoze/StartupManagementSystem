package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.User;
import ua.goit.group6.service.CityService;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.UserService;

/**
 * Controller for {@link User}
 *
 * @author Boiko Ivan
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    private final CountryService countryService;


    /**
     * Constructor for controller
     *
     * @param userService    {@link UserService} bean
     * @param countryService {@link CountryService} bean
     */
    @Autowired
    public UserController(UserService userService, CountryService countryService) {
        LOGGER.info("Creating user controller");
        this.userService = userService;
        this.countryService = countryService;
    }

    /**
     * Mapping for url ":/users/profile/{id}"
     * Method collects data from database and sends it to {@link User} profile page
     *
     * @param idString the id of user from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and user from database
     */
    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") String idString) {
        ModelAndView profile = new ModelAndView("user_profile");
        long id = Long.parseLong(idString);
        User user = userService.getById(id);
        profile.addObject("user", user);
        LOGGER.info("Building profile page for " + user);
        return profile;
    }

    /**
     * Mapping for url ":/users/profile/{id}/delete"
     * Method deletes {@link User} with chosen id from database
     *
     * @param idString the id of user to delete from url
     * @return redirect link to logout
     */
    @GetMapping("/profile/{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        userService.deleteById(Long.parseLong(idString));
        LOGGER.info("Redirecting to index page after deleting user with id='" + idString + "'");
        //TODO make logout for user but not for admin
        return "redirect:/logout";
    }

    /**
     * Mapping for url "/users/profile/{id}/update"
     * Method collects data from database and sends it to {@link User} update form
     *
     * @param idString the id of user to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * user to update, list of all {@link ua.goit.group6.model.Country}
     * and list of all {@link ua.goit.group6.model.City} from database
     */
    @GetMapping("/profile/{id}/update")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("user_update_form");
        long id = Long.parseLong(idString);
        User user = userService.getById(id);
        updateForm.addObject("user", user);
        updateForm.addObject("countries", countryService.getAll());
        LOGGER.info("Building update page for " + user);
        return updateForm;
    }

    /**
     * Mapping for url "/users/profile/{id}/update/"
     * Method updates {@link User} in database with parameters which come from page form
     *
     * @param idString        the id of user to update from url
     * @param password        new password for user from request
     * @param firstName       new first name for user from request
     * @param lastName        new last name for user from request
     * @param description     new description for user from request
     * @param countryIdString new id of {@link ua.goit.group6.model.Country} for user from request
     * @return redirect link to this user profile
     */
    @PostMapping(value = "/profile/{id}/update/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@PathVariable("id") String idString,
                         @RequestParam("password") String password,
                         @RequestParam("first_name") String firstName,
                         @RequestParam("last_name") String lastName,
                         @RequestParam("description") String description,
                         @RequestParam("country_id") String countryIdString) {
        LOGGER.info("Returning from user update form");
        User user = userService.getById(Long.parseLong(idString));
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDescription(description);
        user.setCountry(countryService.getById(Long.parseLong(countryIdString)));

        //TODO cities
//        user.setCity(cityService.getById(Long.parseLong(cityIidString)));

        userService.update(user);
        LOGGER.info("User " + user + " successfully updated");
        LOGGER.info("Redirecting to profile of user with id='" + idString + "'");
        return "redirect:/users/profile/" + idString;
    }

    /**
     * Mapping for url ":/users"
     * Method collects data from database and sends it to page which shows all {@link User}
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of all {@link User} from database
     */
    @GetMapping
    public ModelAndView list() {
        ModelAndView users = new ModelAndView("users_list");
        users.addObject("users", userService.getAll());
        LOGGER.info("Building page with all users");
        return users;
    }
}

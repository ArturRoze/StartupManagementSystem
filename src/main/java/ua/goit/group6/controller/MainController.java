package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

import javax.annotation.PostConstruct;

/**
 * Main controller for system
 *
 * @author Boiko Ivan
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    private final AdminService adminService;

    private final StartupService startupService;

    /**
     * Constructor for controller
     *  @param userService  {@link UserService} bean
     * @param adminService {@link AdminService} bean
     * @param startupService {@link StartupService} bean
     */
    @Autowired
    public MainController(UserService userService, AdminService adminService, StartupService startupService) {
        LOGGER.info("Creating index controller");
        this.userService = userService;
        this.adminService = adminService;
        this.startupService = startupService;
    }

    /**
     * Mapping for url ":/"
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of last 10 startups from database
     */
    @GetMapping
    public ModelAndView index() {
        ModelAndView main = new ModelAndView("index");
//        main.addObject("startups", startupService.getAll());
        main.addObject("startups", startupService.getLastNDesc(2));
        LOGGER.info("Building index page");
        return main;
    }

    /**
     * Mapping for url ":/logout"
     *
     * @return redirect link to logout
     */
    @PostMapping("/logout")
    public String logout() {
        LOGGER.info("Redirecting to login?logout");
        return "redirect:/login?logout";
    }

    /**
     * /**
     * Mapping for url ":/registration/"
     * Method saves {@link User} to database
     *
     * @param login    login from the form
     * @param password password from the form
     * @return redirect link to login page
     */
    @PostMapping("registration/")
    public String registration(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userService.save(user);
        LOGGER.info("Redirecting to index page after registration");
        return "redirect:/login";
    }

    /**
     * Mapping for url ":/news"
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of //TODO startups and offers from database
     * sorted by registration date in descending order
     */
    @GetMapping("news")
    public ModelAndView news() {
        ModelAndView news = new ModelAndView("news");
        news.addObject("startups", startupService.getAllDescRegistration());
        //TODO add offers
        LOGGER.info("Building news page");
        return news;
    }

    /**
     * Method initialises default {@link User} with login = 'user' and password = 'user',
     * and default {@link Admin} with login = 'admin' and password = 'admin',
     * and writes they to database
     */
    @PostConstruct
    public void InitDefaultUsers() {
        if (adminService.getByLogin("admin") == null) {

            LOGGER.info("Initialising default admin with login = 'admin', and password = 'admin' ");
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setPassword("admin");
//            admin.setRole(User.Roles.ADMIN);
            adminService.save(admin);
        }

        if (userService.getByLogin("user") == null) {
            LOGGER.info("Initialising default user with login = 'user', and password = 'user' ");
            User user = new User();
            user.setLogin("user");
            user.setPassword("user");
//            user.setRole(User.Roles.USER);
            userService.save(user);
        }
    }
}

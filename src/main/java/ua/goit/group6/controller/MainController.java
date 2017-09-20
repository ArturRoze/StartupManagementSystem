package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Main controller for system
 *
 * @author Boiko Ivan
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private final UserService userService;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for controller
     *
     * @param userService     {@link UserService} bean
     * @param adminService    {@link AdminService} bean
     * @param passwordEncoder {@link PasswordEncoder} bean
     */
    @Autowired
    public MainController(UserService userService, AdminService adminService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating main controller");
        this.userService = userService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Mapping for url ":/"
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of last 10 //TODO startups from database
     */
    @GetMapping
    public ModelAndView main() {
        ModelAndView main = new ModelAndView("index");
        // TODO add startup list
        LOGGER.info("Building main page");
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
     * Mapping for url ":/registration/"
     * Method saves {@link User} to database
     *
     * @param user {@link User} from page form
     * @return redirect link //TODO where?
     * @throws IOException if registration failed
     */
    @PostMapping("registration/")
    public String registerUser(@ModelAttribute("user") User user) throws IOException {
        LOGGER.info("Encoding password");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            LOGGER.info("Trying to save user " + user + " to database");
            userService.save(user);
        } catch (Exception e) {
            LOGGER.info("Exception during saving user to database");
            throw new IOException("Exception during saving user to database");
        }
        LOGGER.info("Redirecting to main page after registration");
        return "redirect:/";
    }

    /**
     * Mapping for url ":/news"
     *
     * @return @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of //TODO startups and offers from database
     * sorted by descending registration date
     */
    @GetMapping("/news")
    public ModelAndView news() {
        ModelAndView news = new ModelAndView("news");
        // TODO add startup list
        return news;
    }

    /**
     * //TODO what does this method do?
     * @param ex some exception
     * @return something
     */
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }

    /**
     * Method initialises default {@link User} with login = 'user' and password = 'user',
     * and default {@link Admin} with login = 'admin' and password = 'admin',
     * and writes they to database
     */
    @PostConstruct
    public void InitDefaultUsers() {

        if (userService.getByLogin("user") == null) {
            LOGGER.info("Initialising default user with login = 'user', and password = 'user' ");
            User user = new User();
            user.setLogin("user");
            user.setPassword(passwordEncoder.encode("user"));
//            user.setRole(User.Roles.USER);
            userService.save(user);
        }
        if (adminService.getByLogin("admin") == null) {
            LOGGER.info("Initialising default admin with login = 'admin', and password = 'admin' ");
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
//            admin.setRole(User.Roles.ADMIN);
            adminService.save(admin);
        }
    }

}

package ua.goit.group6.controller;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.*;
import ua.goit.group6.service.*;

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

    private final NewsService newsService;

    private final PasswordEncoder passwordEncoder;

    private final CountryService countryService;

    private final IndustryService industryService;

    /**
     * Constructor for controller
     *
     * @param userService     {@link UserService} bean
     * @param passwordEncoder {@link PasswordEncoder} bean
     * @param startupService  {@link StartupService} bean
     * @param newsService     {@link NewsService} bean
     *                        <p>
     *                        <p> Need to initialize database </p>
     * @param countryService  {@link CountryService} bean
     * @param industryService {@link IndustryService} bean
     * @param adminService    {@link AdminService} bean
     */
    @Autowired
    public MainController(UserService userService, PasswordEncoder passwordEncoder,
                          StartupService startupService, NewsService newsService,
                          AdminService adminService,
                          CountryService countryService, IndustryService industryService) {
        LOGGER.info("Creating index controller");
        this.countryService = countryService;
        this.industryService = industryService;
        this.userService = userService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
        this.startupService = startupService;
        this.newsService = newsService;
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
        try {
            main.addObject("startups", startupService.getLastN(6));
        } catch (TransactionException e) {
            return new ModelAndView("error");
        }
        LOGGER.info("Building index page");
        return main;
    }

    /**
     * Mapping for url ":/register?"
     * Method saves {@link User} to database
     *
     * @param login    login from the form
     * @param password password from the form
     * @return redirect link to login page
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registration(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userService.save(user);
        } catch (TransactionException e){
            return "redirect:/error";
        }
        LOGGER.info("Redirecting to index page after registration");
        return "redirect:/login";
    }

    /**
     * Mapping for url ":/news"
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of startups and offers from database
     * sorted by registration date in descending order
     */
    @GetMapping("news")
    public ModelAndView news(@RequestParam(value = "page", required = false) String page) {

        ModelAndView news = new ModelAndView("news");
        int currentPage = 1;

        try {
            int pagesCount = newsService.getCountOfPages(6);

            if (page != null) {

                try {
                    currentPage = Integer.parseInt(page);
                } catch (NumberFormatException e) {
                    return new ModelAndView("error");
                }

                if (currentPage < 1) {
                    currentPage = 1;
                } else if (currentPage > pagesCount) {
                    currentPage = pagesCount;
                }
            }

            news.addObject("current_page", currentPage);
            news.addObject("pages_count", pagesCount);
            news.addObject("news_list", newsService.getNPageWithMNews(currentPage, 6));

        } catch (TransactionException | NumberFormatException e) {
            return new ModelAndView("error");
        }

        LOGGER.info("Building news page");
        return news;
    }

    /**
     * Method initialises default {@link User} with login = 'user' and password = 'user',
     * and default {@link Admin} with login = 'admin' and password = 'admin',
     * and writes they to database
     */
    @PostConstruct
    public void initDefaultDatabase() {

        try{
            if (countryService.getAll().isEmpty()) {
                Country country = new Country();
                country.setName("Ukraine");
                countryService.save(country);
                country.setName("USA");
                countryService.save(country);
            }

            if (industryService.getAll().isEmpty()) {
                Industry industry = new Industry();
                industry.setName("IT");
                industryService.save(industry);
                industry.setName("Finance");
                industryService.save(industry);
                industry.setName("Food");
                industryService.save(industry);
            }

            if (adminService.getByLogin("admin") == null) {

                LOGGER.info("Initialising default admin with login = 'admin', and password = 'admin' ");
                Admin admin = new Admin();
                admin.setLogin("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                adminService.save(admin);
            }

            if (userService.getByLogin("user") == null) {
                LOGGER.info("Initialising default user with login = 'user', and password = 'user' ");
                User user = new User();
                user.setLogin("user");
                user.setPassword(passwordEncoder.encode("user"));
                userService.save(user);
            }
        } catch (TransactionException e){
            LOGGER.warn("Fail. It can't even initialize database");
        }
    }
}

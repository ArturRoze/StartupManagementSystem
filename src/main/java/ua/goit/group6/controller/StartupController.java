package ua.goit.group6.controller;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.IndustryService;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

/**
 * @author Boiko Ivan
 */
@Controller
@RequestMapping("/startups")
public class StartupController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StartupService startupService;

    private final CountryService countryService;

    private final IndustryService industryService;

    private final UserService userService;

    /**
     * Constructor for controller
     *
     * @param startupService  {@link StartupService} bean
     * @param countryService  {@link CountryService} bean
     * @param industryService {@link IndustryService} bean
     * @param userService     {@link UserService} bean
     */
    @Autowired
    public StartupController(StartupService startupService, CountryService countryService, IndustryService industryService, UserService userService) {
        this.startupService = startupService;
        this.countryService = countryService;
        this.industryService = industryService;
        this.userService = userService;
    }

    /**
     * Mapping for url ":/startups"
     * Method collects data from database and sends it to page which shows all {@link Startup}
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of all {@link Startup} from database
     */
    @GetMapping
    public ModelAndView list() {
        ModelAndView startups = new ModelAndView("startups_list");

        try {
            startups.addObject("startups", startupService.getAllByDecreaseRegistration());
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        LOGGER.info("Building page with all startups");
        return startups;
    }

    /**
     * Mapping for url ":/startups/{id}"
     * Method collects data from database and sends it to {@link Startup} profile page
     *
     * @param idString the id of startup from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and startup from database
     */
    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") String idString) {

        ModelAndView startupInfo = new ModelAndView("startup_info");
        Startup startup;

        try {
            int id = Integer.parseInt(idString);
            startup = startupService.getById(id);
            startupInfo.addObject("startup", startup);

        } catch (Exception e) {
            return new ModelAndView("error");
        }

        LOGGER.info("Building info page for " + startup);
        return startupInfo;
    }

    /**
     * Mapping for url ":/startups/{id}/delete"
     * Method deletes {@link Startup} from database
     *
     * @param idString id of startup to delete
     * @return redirect link to news page
     */
    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        try {
            startupService.deleteById(Integer.parseInt(idString));
        } catch (Exception e) {
            return "redirect:/error";
        }
        LOGGER.info("Redirecting to news page after deleting startup with id='{}'", idString);
        return "redirect:/news";
    }

    /**
     * Mapping for url ":/startups/new/startup"
     * Method collects data from database and sends them to {@link Startup} create form
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * startuo to update, list of all {@link ua.goit.group6.model.Country}
     * and list of all {@link ua.goit.group6.model.Industry} from database
     */
    @GetMapping("/new/startup")
    public ModelAndView newStartup() {
        ModelAndView createForm = new ModelAndView("startup_add_form");
        try {
            createForm.addObject("countries", countryService.getAll());
            createForm.addObject("industries", industryService.getAll());
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        LOGGER.info("Building new startup form");
        return createForm;
    }

    /**
     * Mapping for url ":/startups/new/startup/?"
     * Method creates {@link Startup} with parameters from page form and saves it to database
     *
     * @param userIdString     id of user who creates startup
     * @param name             name of startup
     * @param budgetString     budget of startup
     * @param description      description of startup
     * @param countryIdString  id of startup country
     * @param industryIdString id of startup industry
     * @return redirect link to news page
     */
    @PostMapping(value = "/new/startup/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam("user_id") String userIdString,
                         @RequestParam("name") String name,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {
        LOGGER.info("Returning from startup create form");

        Startup startup = new Startup();
        try {
            startup.setUser(userService.getById(Integer.parseInt(userIdString)));
            startup.setName(name);
            startup.setDescription(description);
            startup.setBudget(Integer.parseInt(budgetString));

            if (countryIdString != null && !countryIdString.equals(""))
                startup.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

            if (industryIdString != null && !industryIdString.equals(""))
                startup.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));

            startupService.save(startup);
        } catch (Exception e) {
            return "redirect:/error";
        }

        LOGGER.info("Startup '{}' successfully created", startup);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

    /**
     * Mapping for url ":/startups/{id}/edit"
     * Method collects data from database and sends them to {@link Startup} update form
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * startuo to update, list of all {@link ua.goit.group6.model.Country}
     * and list of all {@link ua.goit.group6.model.Industry} from database
     */
    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("startup_update_form");
        try {
            updateForm.addObject("startup", startupService.getById(Integer.parseInt(idString)));
            updateForm.addObject("countries", countryService.getAll());
            updateForm.addObject("industries", industryService.getAll());
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        return updateForm;
    }

    /**
     * Mapping for url ":/srartups/{id}/update?"
     * Method updates {@link Startup} in database with parameters from page form
     *
     * @param idString         id of startup to update
     * @param name             new name of startup
     * @param budgetString     new budget
     * @param description      new description
     * @param countryIdString  new country
     * @param industryIdString new industry
     * @return redirect link to news pagew
     */
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String idString,
                         @RequestParam("name") String name,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {
        Startup startup;
        try {
            startup = startupService.getById(Integer.parseInt(idString));
            startup.setName(name);
            startup.setDescription(description);
            startup.setBudget(Integer.parseInt(budgetString));

            if (countryIdString != null && !countryIdString.equals(""))
                startup.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

            if (industryIdString != null && !industryIdString.equals(""))
                startup.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));
            startupService.update(startup);

        } catch (Exception e) {
            return "redirect:/error";
        }

        return "redirect:/news";
    }

}

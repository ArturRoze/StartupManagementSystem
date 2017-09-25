package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

@Controller
@RequestMapping("/startups")
public class StartupController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StartupService startupService;

    private final CountryService countryService;

    private final UserService userService;

    public StartupController(StartupService startupService, CountryService countryService, UserService userService) {
        this.startupService = startupService;
        this.countryService = countryService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView startups = new ModelAndView("startups_list");
        startups.addObject("startups", startupService.getAllDescRegistration());
        LOGGER.info("Building page with all startups");
        return startups;
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") String idString) {
        ModelAndView startupInfo = new ModelAndView("startup_info");
        long id = Long.parseLong(idString);
        Startup startup = startupService.getById(id);
        startupInfo.addObject("startup", startup);
        LOGGER.info("Building info page for " + startup);
        return startupInfo;
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        startupService.deleteById(Long.parseLong(idString));
        LOGGER.info("Redirecting to news page after deleting startup with id='{}'", idString);
        return "redirect:/news";
    }

    @GetMapping("/new/startup")
    public ModelAndView create() {
        ModelAndView createForm = new ModelAndView("startup_add_form");
        createForm.addObject("countries", countryService.getAll());
        LOGGER.info("Building new startup form");
        return createForm;
    }

    @PostMapping(value = "/new/startup/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@RequestParam("user_id") String userIdString,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("country_id") String countryIdString) {
        LOGGER.info("Returning from startup create form");

        Startup startup = new Startup();
        startup.setUser(userService.getById(Long.parseLong(userIdString)));
        startup.setName(name);
        startup.setDescription(description);
        startup.setCountry(countryService.getById(Long.parseLong(countryIdString)));

        startupService.save(startup);
        LOGGER.info("Startup '{}' successfully created", startup);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

}

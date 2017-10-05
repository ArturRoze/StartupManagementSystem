package ua.goit.group6.controller;

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

@Controller
@RequestMapping("/startups")
public class StartupController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StartupService startupService;

    private final CountryService countryService;

    private final IndustryService industryService;

    private final UserService userService;

    @Autowired
    public StartupController(StartupService startupService, CountryService countryService, IndustryService industryService, UserService userService) {
        this.startupService = startupService;
        this.countryService = countryService;
        this.industryService = industryService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView startups = new ModelAndView("startups_list");
        startups.addObject("startups", startupService.getAllDesc());
        LOGGER.info("Building page with all startups");
        return startups;
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") String idString) {
        ModelAndView startupInfo = new ModelAndView("startup_info");
        int id = Integer.parseInt(idString);
        Startup startup = startupService.getById(id);
        startupInfo.addObject("startup", startup);
        LOGGER.info("Building info page for " + startup);
        return startupInfo;
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        startupService.deleteById(Integer.parseInt(idString));
        LOGGER.info("Redirecting to news page after deleting startup with id='{}'", idString);
        return "redirect:/news";
    }

    @GetMapping("/new/startup")
    public ModelAndView newStartup() {
        ModelAndView createForm = new ModelAndView("startup_add_form");
        createForm.addObject("countries", countryService.getAll());
        createForm.addObject("industries", industryService.getAll());
        LOGGER.info("Building new startup form");
        return createForm;
    }

    @PostMapping(value = "/new/startup/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam("user_id") String userIdString,
                         @RequestParam("name") String name,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {
        LOGGER.info("Returning from startup create form");

        Startup startup = new Startup();
        startup.setUser(userService.getById(Integer.parseInt(userIdString)));
        startup.setName(name);
        startup.setDescription(description);
        startup.setBudget(Integer.parseInt(budgetString));

        if (countryIdString != null)
            startup.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

        if (industryIdString != null)
            startup.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));

        startupService.save(startup);
        LOGGER.info("Startup '{}' successfully created", startup);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("startup_update_form");
        updateForm.addObject("startup", startupService.getById(Integer.parseInt(idString)));
        updateForm.addObject("countries", countryService.getAll());
        updateForm.addObject("industries", industryService.getAll());
        return updateForm;
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String idString,
                         @RequestParam("name") String name,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {
        Startup startup = startupService.getById(Integer.parseInt(idString));
        startup.setName(name);
        startup.setDescription(description);
        startup.setBudget(Integer.parseInt(budgetString));

        if (countryIdString != null)
            startup.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

        if (industryIdString != null)
            startup.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));
        startupService.update(startup);

        return "redirect:/news";
    }

}

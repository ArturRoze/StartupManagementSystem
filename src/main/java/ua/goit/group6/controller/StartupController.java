package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public StartupController(StartupService startupService, CountryService countryService, IndustryService industryService, UserService userService) {
        this.startupService = startupService;
        this.countryService = countryService;
        this.industryService = industryService;
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
                         @RequestParam("country_id") String countryIdString,
                         @RequestParam("industry_id") String industryIdString) {
        LOGGER.info("Returning from startup create form");

        Startup startup = new Startup();
        startup.setUser(userService.getById(Long.parseLong(userIdString)));
        startup.setName(name);
        startup.setDescription(description);
        startup.setBudget(Integer.parseInt(budgetString));

        if (!countryIdString.isEmpty())
            startup.setCountry(countryService.getById(Long.parseLong(countryIdString)));

        if (!industryIdString.isEmpty())
            startup.setIndustry(industryService.getById(Long.parseLong(industryIdString)));

        startupService.save(startup);
        LOGGER.info("Startup '{}' successfully created", startup);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("startup_update_form");
        updateForm.addObject("startup", startupService.getById(Long.parseLong(idString)));
        updateForm.addObject("countries", countryService.getAll());
        updateForm.addObject("industries", industryService.getAll());
        return updateForm;
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String idString,
                         @RequestParam("name") String name,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam("country_id") String countryIdString,
                         @RequestParam("industry_id") String industryIdString) {
        Startup startup = startupService.getById(Long.parseLong(idString));
        startup.setName(name);
        startup.setDescription(description);
        startup.setBudget(Integer.parseInt(budgetString));

        if (!countryIdString.isEmpty())
            startup.setCountry(countryService.getById(Long.parseLong(countryIdString)));

        if (!industryIdString.isEmpty())
            startup.setIndustry(industryService.getById(Long.parseLong(industryIdString)));
        startupService.update(startup);

        return "redirect:/news";
    }

}

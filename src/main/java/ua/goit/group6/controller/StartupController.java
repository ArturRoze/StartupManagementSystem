package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.StartupService;

@Controller
@RequestMapping("/startups")
public class StartupController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StartupService startupService;

    public StartupController(StartupService startupService) {
        this.startupService = startupService;
    }

    @GetMapping("/{id}")
    public ModelAndView profile(@PathVariable("id") String idString) {
        ModelAndView profile = new ModelAndView("startup_info");
        long id = Long.parseLong(idString);
        Startup startup = startupService.getById(id);
        profile.addObject("startup", startup);
        LOGGER.info("Building profile page for " + startup);
        return profile;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView startups = new ModelAndView("startups_list");
        startups.addObject("startups", startupService.getAll());
        LOGGER.info("Building page with all startups");
        return startups;
    }


}

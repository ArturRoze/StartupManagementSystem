package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Offer;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.IndustryService;
import ua.goit.group6.service.OfferService;
import ua.goit.group6.service.UserService;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final OfferService offerService;

    private final CountryService countryService;

    private final IndustryService industryService;

    private final UserService userService;

    @Autowired
    public OfferController(OfferService offerService, CountryService countryService, IndustryService industryService, UserService userService) {
        this.offerService = offerService;
        this.countryService = countryService;
        this.industryService = industryService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView offers = new ModelAndView("startups_list");
        offers.addObject("offers", offerService.getAllDesc());
        LOGGER.info("Building page with all offers");
        return offers;
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") String idString) {
        ModelAndView offerInfo = new ModelAndView("offer_info");
        int id = Integer.parseInt(idString);
        Offer offer = offerService.getById(id);
        offerInfo.addObject("offer", offer);
        LOGGER.info("Building info page for " + offer);
        return offerInfo;
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        offerService.deleteById(Integer.parseInt(idString));
        LOGGER.info("Redirecting to news page after deleting offer with id='{}'", idString);
        return "redirect:/news";
    }

    @GetMapping("/new/offer")
    public ModelAndView newOffer() {
        ModelAndView createForm = new ModelAndView("offer_add_form");
        createForm.addObject("countries", countryService.getAll());
        createForm.addObject("industries", industryService.getAll());
        LOGGER.info("Building new offer form");
        return createForm;
    }

    @PostMapping(value = "/new/offer/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam("user_id") String userIdString,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {

        LOGGER.info("Returning from startup create form");

        Offer offer = new Offer();
        offer.setUser(userService.getById(Integer.parseInt(userIdString)));
        offer.setDescription(description);
        offer.setBudget(Integer.parseInt(budgetString));

        if (countryIdString != null)
            offer.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

        if (industryIdString != null)
            offer.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));

        offerService.save(offer);
        LOGGER.info("Offer '{}' successfully created", offer);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("offer_update_form");
        updateForm.addObject("offer", offerService.getById(Integer.parseInt(idString)));
        updateForm.addObject("countries", countryService.getAll());
        updateForm.addObject("industries", industryService.getAll());
        return updateForm;
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String idString,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id",required = false) String industryIdString) {
        Offer offer = offerService.getById(Integer.parseInt(idString));
        offer.setDescription(description);
        offer.setBudget(Integer.parseInt(budgetString));

        if (countryIdString != null)
            offer.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

        if (industryIdString != null)
            offer.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));
        offerService.update(offer);

        return "redirect:/news";
    }
}

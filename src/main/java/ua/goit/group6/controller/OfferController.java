package ua.goit.group6.controller;

import org.hibernate.TransactionException;
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

/**
 * Controller for {@link Offer}
 *
 * @author Artyr
 */
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

    /**
     * Mapping for url ":/offers/{id}"
     * Method collects data from database and sends it to {@link Offer} info page
     *
     * @param idString the id of offer from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and offer from database
     */
    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") String idString) {
        ModelAndView offerInfo = new ModelAndView("offer_info");
        Offer offer;
        try {
            int id = Integer.parseInt(idString);
            offer = offerService.getById(id);
            offerInfo.addObject("offer", offer);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not correct id.");
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }
        LOGGER.info("Building info page for " + offer);
        return offerInfo;
    }

    /**
     * Mapping for url ":/offers/{id}/delete"
     * Method deletes {@link Offer} with chosen id from database
     *
     * @param idString the id of offer to delete from url
     * @return redirect link to page news
     */
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        try {
            offerService.deleteById(Integer.parseInt(idString));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not correct id.");
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }
        LOGGER.info("Redirecting to news page after deleting offer with id='{}'", idString);
        return "redirect:/news";
    }

    /**
     * Mapping for url "/offers/new/offer"
     *
     * @return a {@link ModelAndView}
     * offer to add, list of all {@link ua.goit.group6.model.Country} and {@link ua.goit.group6.model.Industry}
     */
    @GetMapping("/new/offer")
    public ModelAndView newOffer() {
        ModelAndView createForm = new ModelAndView("offer_add_form");
        try {
            createForm.addObject("countries", countryService.getAll());
            createForm.addObject("industries", industryService.getAll());
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }
        LOGGER.info("Building new offer form");
        return createForm;
    }

    /**
     * Mapping for url "/orders/new/offer"
     * Method adds {@link Offer} in database with parameters which come from add page form
     *
     * @param userIdString     the id of user to add from url
     * @param budgetString     new budget for offer from request
     * @param description      new description for offer from request
     * @param countryIdString  new id of {@link ua.goit.group6.model.Country} for offer from request
     * @param industryIdString new id of {@link ua.goit.group6.model.Industry} for offer from request
     * @return redirect link to news
     */
    @PostMapping(value = "/new/offer/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam("user_id") String userIdString,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {

        LOGGER.info("Returning from offer create form");

        Offer offer = new Offer();
        try {
            offer.setUser(userService.getById(Integer.parseInt(userIdString)));
            offer.setDescription(description);
            offer.setBudget(Integer.parseInt(budgetString));

            if (countryIdString != null && !countryIdString.equals(""))
                offer.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

            if (industryIdString != null && !industryIdString.equals(""))
                offer.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));

            offerService.save(offer);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not correct id.");
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }
        LOGGER.info("Offer '{}' successfully created", offer);
        LOGGER.info("Redirecting to news page");
        return "redirect:/news";
    }

    /**
     * Mapping for url "/offers/{id}/edit"
     * Method collects data from database and sends it to {@link Offer} update form
     *
     * @param idString the id of offer to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * offer to update, list of all {@link ua.goit.group6.model.Country} and {@link ua.goit.group6.model.Industry}
     */
    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") String idString) {
        ModelAndView updateForm = new ModelAndView("offer_update_form");
        try {
            updateForm.addObject("offer", offerService.getById(Integer.parseInt(idString)));
            updateForm.addObject("countries", countryService.getAll());
            updateForm.addObject("industries", industryService.getAll());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not correct id.");
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }
        return updateForm;
    }

    /**
     * Mapping for url "/orders/{id}/update"
     * Method updates {@link Offer} in database with parameters which come from update page form
     *
     * @param idString         the id of user to update from url
     * @param budgetString     new budget for offer from request
     * @param description      new description for offer from request
     * @param countryIdString  new id of {@link ua.goit.group6.model.Country} for offer from request
     * @param industryIdString new id of {@link ua.goit.group6.model.Industry} for offer from request
     * @return redirect link to news
     */
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String idString,
                         @RequestParam("budget") String budgetString,
                         @RequestParam("description") String description,
                         @RequestParam(value = "country_id", required = false) String countryIdString,
                         @RequestParam(value = "industry_id", required = false) String industryIdString) {
        Offer offer;
        try {
            offer = offerService.getById(Integer.parseInt(idString));
            offer.setDescription(description);
            offer.setBudget(Integer.parseInt(budgetString));

            if (countryIdString != null && !countryIdString.equals(""))
                offer.setCountry(countryService.getById(Integer.parseInt(countryIdString)));

            if (industryIdString != null && !industryIdString.equals(""))
                offer.setIndustry(industryService.getById(Integer.parseInt(industryIdString)));
            offerService.update(offer);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not correct id.");
        } catch (Exception e) {
            throw new RuntimeException("Database malfunction. Please reload page.");
        }

        return "redirect:/news";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", e.getMessage());
        LOGGER.warn("Build new error page with message: " + e.getMessage());
        return view;
    }
}

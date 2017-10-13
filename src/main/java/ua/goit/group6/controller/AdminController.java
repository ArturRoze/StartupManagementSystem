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
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;

import java.io.IOException;

/**
 * Controller for {@link Admin}
 *
 * @author Artyr
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating admin controller");
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Mapping for url ":/admins/profile/{id}"
     * Method collects data from database and sends it to {@link Admin} profile page
     *
     * @param idString the id of admin from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and user from database
     */
    @GetMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable("id") String idString) {
        LOGGER.info("Get profile of Admin with id '{}'", idString);
        ModelAndView profile = new ModelAndView("admin_profile");
        try {
            int id = Integer.parseInt(idString);
            Admin admin = adminService.getById(id);
            profile.addObject("admin", admin);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        return profile;
    }

    /**
     * Mapping for url ":/admins/profile/{id}/delete"
     * Method deletes {@link Admin} with chosen id from database
     *
     * @param idString the id of user to delete from url
     * @return redirect link to logout if owner deletes himself, or to news page if admin deletes user
     */
    @GetMapping("/profile/{id}/delete")
    public String delete(@PathVariable("id") String idString) {
        LOGGER.info("delete admin with id");
        try {
            int id = Integer.parseInt(idString);
            adminService.deleteById(id);
        } catch (Exception e) {
            return "redirect:/error";
        }
        return "redirect:/logout";
    }

    /**
     * Mapping for url "/admins/new/admin/"
     * Method create {@link Admin} in database with parameters which come from page form
     *
     * @param login    the login of admin to create from url
     * @param password new password for admin from request
     * @param email    new email for admin from request
     * @return redirect link to this admin's list
     */
    @PostMapping(value = "/new/admin/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email) {

        LOGGER.info("Received admin from admin_add_form");

        Admin admin = new Admin();
        try {
            admin.setLogin(login);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setEmail(email);

            adminService.save(admin);
        } catch (Exception e) {
            return "redirect:/error";
        }

        LOGGER.info("Admin: '{}' created successfully", admin);

        return "redirect:/admins/list";
    }

    /**
     * Mapping for url "/admins/profile/{id}/update"
     * Method collects data from database and sends it to {@link Admin} update form
     *
     * @param idString the id of admin to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String}
     */
    @GetMapping("profile/{id}/update")
    public ModelAndView update(@PathVariable("id") String idString) {
        LOGGER.info("Update profile of Admin with id: '{}'", idString);
        ModelAndView updateForm = new ModelAndView("admin_update_form");
        Admin admin;
        try {
            int id = Integer.parseInt(idString);
            admin = adminService.getById(id);
            updateForm.addObject("admin", admin);

        } catch (Exception e) {
            return new ModelAndView("error");
        }
        LOGGER.info("Received admin from admin_add_form");
        return updateForm;
    }

    /**
     * Mapping for url "/admins/profile/{id}/update/"
     * Method updates {@link Admin} in database with parameters which come from page form
     *
     * @param idString the id of admin to update from url
     * @param password new password for admin from request
     * @param email    new email for admin from request
     * @return redirect link to this admin profile
     */
    @PostMapping(value = "/profile/{id}/update/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@PathVariable("id") String idString,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email)

            throws IOException {
        Admin admin;
        try {
            admin = adminService.getById(Integer.parseInt(idString));
            LOGGER.info("Received admin from form: '{}'", admin);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setEmail(email);
            adminService.update(admin);

        } catch (Exception e) {
            return "redirect:/error";
        }
        LOGGER.info("Admin: '{}' created successfully", admin);

        return "redirect:/admins/profile/" + admin.getId();
    }

    /**
     * Mapping for url ":/admins/list"
     * Method collects data from database and sends it to page which shows all {@link Admin}
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of all {@link Admin} from database
     */
    @GetMapping("/list")
    public ModelAndView listAdmins() {
        ModelAndView admins = new ModelAndView("admins_list");
        try {
            admins.addObject("admins", adminService.getAll());
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        return admins;
    }
}

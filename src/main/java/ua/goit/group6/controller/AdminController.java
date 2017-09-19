package ua.goit.group6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.group6.service.AdminService;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        LOGGER.info("Creating admin controller");
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }
}

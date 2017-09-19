package ua.goit.group6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserService userService, AdminService adminService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @PostMapping("registration/")
    public String registerUser(@ModelAttribute("user") User user) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            userService.save(user);
        }
        catch (Exception e){
            throw new IOException("Exception during saving user to database");
        }
        return "redirect:/";
    }

    @GetMapping("/news")
    public ModelAndView news(){
        ModelAndView news = new ModelAndView("news");
        // TODO add startup list
        return news;
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }

    @PostConstruct
    public void InitDefaultUsers() {
        if (userService.getByLogin("user") == null) {
            User user = new User();
            user.setLogin("user");
            user.setPassword(passwordEncoder.encode("user"));
//            user.setRole(User.Roles.USER);
            userService.save(user);
        }
        if (adminService.getByLogin("admin") == null) {
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
//            admin.setRole(User.Roles.ADMIN);
            adminService.save(admin);
        }
    }

}

package ua.goit.group6.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.group6.configuration.DatabaseConfiguration;
import ua.goit.group6.model.User;
import ua.goit.group6.service.UserService;

public class DatabaseTestApp {

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(DatabaseConfiguration.class)){
            System.out.println("-------------------------------------------------------------------------------------");

            UserService userService = context.getBean(UserService.class);

            User user = userService.getById(1L);
            System.out.println(user);

            user.setEmail("a");
            userService.save(user);

            long id = userService.readById(1L).getId();
            System.out.println(id);

            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

}

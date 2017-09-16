package ua.goit.group6.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.group6.configuration.DatabaseConfiguration;
import ua.goit.group6.service.UserService;

public class DatabaseTestApp {

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(DatabaseConfiguration.class)){
            System.out.println("-------------------------------------------------------------------------------------");

            UserService userService = context.getBean(UserService.class);

            System.out.println(userService.getById(1L));

            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

}

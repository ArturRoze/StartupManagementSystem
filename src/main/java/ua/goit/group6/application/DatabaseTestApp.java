package ua.goit.group6.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.group6.configuration.DatabaseConfiguration;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import java.util.List;

public class DatabaseTestApp {

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(DatabaseConfiguration.class)){
            System.out.println("-------------------------------------------------------------------------------------");

            UserService userService = context.getBean(UserService.class);
            AdminService adminService = context.getBean(AdminService.class);

//            Admin admin = new Admin();
//            admin.setLogin("admin1");
//            admin.setPassword("a1");
//            System.out.println("try save admin");
//            adminService.save(admin);
//
//            System.out.println("try get by login");
//            admin = adminService.getByLogin("admin1");
//            System.out.println(admin);
//
//            System.out.println("try get all admins");
//            List<Admin> admins = adminService.getAll();
//            admins.forEach(System.out::println);
//
//            System.out.println("try update admin to newAdmin");
//            Admin newAdmin = adminService.getById(40);
//            newAdmin.setPassword("newAdmin");
//            newAdmin.setLogin("newAdmin");
//            adminService.update(newAdmin);
//            System.out.println(adminService.getByLogin("newAdmin"));
//
//            System.out.println("try delete by id");
//            //adminService.deleteById(admin.getId());
//            System.out.println(adminService.getById(admin.getId()));
//
//            System.out.println("try delete admin");
//            adminService.delete(newAdmin);


            User user = userService.getById(1L);
            System.out.println(user);

            user = userService.getByLogin("b");
            System.out.println(user);

            userService.save(user);

            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

}

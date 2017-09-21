package ua.goit.group6.controller.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.CityService;
import ua.goit.group6.service.CountryService;
import ua.goit.group6.service.UserService;

import static org.mockito.Mockito.mock;

@Configuration
public class TestControllersConfiguration {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public AdminService adminService() {
        return mock(AdminService.class);
    }

    @Bean
    public CountryService countryService() {
        return mock(CountryService.class);
    }

    @Bean
    public CityService cityService() {
        return mock(CityService.class);
    }
}

package ua.goit.group6.controller.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.goit.group6.service.*;

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

    @Bean
    public StartupService startupService(){
        return mock(StartupService.class);
    }

    @Bean
    public IndustryService industryService(){ return mock(IndustryService.class);}

    }

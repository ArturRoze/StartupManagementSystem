package ua.goit.group6.controller.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public RegionService regionService() {
        return mock(RegionService.class);
    }

    @Bean
    public StartupService startupService(){
        return mock(StartupService.class);
    }

    @Bean public OfferService offerService(){return mock(OfferService.class);}

    @Bean public NewsService newsService(){
        return mock(NewsService.class);
    }

    @Bean
    public IndustryService industryService(){ return mock(IndustryService.class);}

    }

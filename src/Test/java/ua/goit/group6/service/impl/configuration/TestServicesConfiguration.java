package ua.goit.group6.service.impl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.dao.CityDao;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.dao.UserDao;

import static org.mockito.Mockito.mock;

@Configuration
public class TestServicesConfiguration {

    @Bean
    public AdminDao adminDao(){
        return mock(AdminDao.class);
    }

    @Bean
    public UserDao userDao(){
        return mock(UserDao.class);
    }

    @Bean
    public CountryDao countryDao(){
        return mock(CountryDao.class);
    }

    @Bean
    public CityDao cityDao(){
        return mock(CityDao.class);
    }
}

package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public SessionFactory sessionFactory(){
        return Mockito.mock(SessionFactory.class);
    }
}

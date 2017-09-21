package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.CityDao;
import ua.goit.group6.model.City;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City> implements CityDao {

    @Autowired
    public CityDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

package ua.goit.group6.dao.impl;

import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.CityDao;
import ua.goit.group6.model.City;

import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {

    //TODO implement dao

    @Override
    public City getById(long id) {
        return null;
    }

    @Override
    public void create(City value) {

    }

    @Override
    public List<City> readAll() {
        return null;
    }

    @Override
    public void update(City value) {

    }

    @Override
    public void delete(City value) {

    }
}

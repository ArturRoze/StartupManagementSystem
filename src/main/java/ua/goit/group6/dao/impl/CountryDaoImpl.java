package ua.goit.group6.dao.impl;

import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.model.Country;

import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    //TODO implement dao

    @Override
    public Country getById(long id) {
        return null;
    }

    @Override
    public void create(Country value) {

    }

    @Override
    public List<Country> readAll() {
        return null;
    }

    @Override
    public void update(Country value) {

    }

    @Override
    public void delete(Country value) {

    }
}

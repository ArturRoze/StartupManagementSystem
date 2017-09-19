package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.model.Country;
import ua.goit.group6.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country getById(long id) {
        return countryDao.getById(id);
    }

    @Override
    public List<Country> getAll() {
        return countryDao.readAll();
    }

    @Override
    public void save(Country country) {
        countryDao.create(country);
    }

    @Override
    public void update(Country country) {
        countryDao.update(country);
    }

    @Override
    public void delete(Country country) {
        countryDao.delete(country);
    }
}

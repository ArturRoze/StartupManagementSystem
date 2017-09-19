package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.CityDao;
import ua.goit.group6.model.City;
import ua.goit.group6.service.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public City getById(long id) {
        return cityDao.getById(id);
    }

    @Override
    public List<City> getAll() {
        return cityDao.readAll();
    }

    @Override
    public void save(City city) {
        cityDao.create(city);
    }

    @Override
    public void update(City city) {
        cityDao.update(city);
    }

    @Override
    public void delete(City city) {
        cityDao.delete(city);
    }
}

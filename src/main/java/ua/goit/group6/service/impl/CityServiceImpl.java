package ua.goit.group6.service.impl;

import org.springframework.stereotype.Service;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.City;
import ua.goit.group6.service.CityService;

@Service
public class CityServiceImpl extends AbstractBasicServiceImpl<City> implements CityService {

    public CityServiceImpl(GeneralDao<City> dao) {
        super(dao);
    }
}

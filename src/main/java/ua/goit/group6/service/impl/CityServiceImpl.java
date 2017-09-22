package ua.goit.group6.service.impl;

import org.springframework.stereotype.Service;
import ua.goit.group6.dao.CityDao;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.City;
import ua.goit.group6.service.CityService;

/**
 * Service for managing {@link City} in repository
 *
 * @author Boiko Ivan
 * @see AbstractBasicServiceImpl
 * @see CityService
 * @see CityDao
 */
@Service
public class CityServiceImpl extends AbstractBasicServiceImpl<City> implements CityService {

    public CityServiceImpl(CityDao cityDao) {
        super(cityDao);
    }
}

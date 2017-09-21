package ua.goit.group6.service.impl;

import org.springframework.stereotype.Service;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.Country;
import ua.goit.group6.service.CountryService;

@Service
public class CountryServiceImpl extends AbstractBasicServiceImpl<Country> implements CountryService {

    public CountryServiceImpl(GeneralDao<Country> dao) {
        super(dao);
    }
}

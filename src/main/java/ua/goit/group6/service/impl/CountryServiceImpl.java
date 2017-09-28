package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.model.Country;
import ua.goit.group6.service.CountryService;

/**
 * Service for managing {@link Country} in repository
 *
 * @author Boiko Ivan
 * @see AbstractBasicServiceImpl
 * @see CountryService
 * @see CountryDao
 */
@Service
public class CountryServiceImpl extends AbstractBasicServiceImpl<Country> implements CountryService {

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        super(countryDao);
    }
}

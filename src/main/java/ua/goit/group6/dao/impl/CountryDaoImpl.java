package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.model.Country;

/**
 * @author Artyr, Ivan
 */
@Repository
public class CountryDaoImpl extends AbstractDaoImpl<Country> implements CountryDao {

    @Autowired
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

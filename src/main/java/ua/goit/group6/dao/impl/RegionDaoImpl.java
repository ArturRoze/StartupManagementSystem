package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.RegionDao;
import ua.goit.group6.model.Region;

@Repository
public class RegionDaoImpl extends AbstractDaoImpl<Region> implements RegionDao {

    @Autowired
    public RegionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

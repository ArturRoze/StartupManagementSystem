package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.model.Offer;

@Repository
@Transactional
public class OfferDaoImpl extends AbstractDaoImpl<Offer> implements OfferDao {

    @Autowired
    public OfferDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}

package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.IndustryDao;
import ua.goit.group6.model.Industry;

/**
 * @author Ivan
 */
@Repository
public class IndustryDaoImpl extends AbstractDaoImpl<Industry> implements IndustryDao {

    @Autowired
    public IndustryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

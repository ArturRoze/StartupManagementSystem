package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.Startup;

/**
 * @author Artyr
 */
@Repository
@Transactional
public class StartupDaoImpl extends AbstractDaoImpl<Startup> implements StartupDao {

    @Autowired
    public StartupDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

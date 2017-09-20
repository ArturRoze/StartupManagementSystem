package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.model.Admin;

/**
 * @param <>
 * @author Artyr
 */

@Repository
public class AdminDaoImpl extends AbstractDaoImpl<Admin> implements AdminDao {

    @Autowired
    public AdminDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Admin getByLogin(String login) {
        Query query = getSession().createQuery("from Admin A where A.login like :login");
        query.setParameter("login", login);
        if (!query.list().isEmpty()) {
            return (Admin) query.list().get(0);
        } else {
            return null;
        }
    }
}

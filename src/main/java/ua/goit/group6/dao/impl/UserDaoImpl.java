package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;

/**
 * @param <>
 * @author Artyr
 */

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getByLogin(String login) {
        Query query = getSession().createQuery("from User U where U.login like :login");
        query.setParameter("login", login);
        if (!query.list().isEmpty()) {
            return (User) query.list().get(0);
        } else {
            return null;
        }
    }
}

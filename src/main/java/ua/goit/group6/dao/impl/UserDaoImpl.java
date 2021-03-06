package ua.goit.group6.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;

/**
 * @author Artyr
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public User getByLogin(String login) {
        Query query = getSession().createQuery("from User U where U.login like :login");
        query.setParameter("login", login);
        if (!query.list().isEmpty()) {
            LOGGER.info("Get user by login='{}' from repository", login);
            return (User) query.list().get(0);
        } else {
            return null;
        }
    }
}

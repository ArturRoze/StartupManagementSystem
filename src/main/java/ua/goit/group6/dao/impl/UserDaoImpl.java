package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.User;

import java.util.List;

/**
 * @author Artyr
 * @param <>
 */

@Repository
public class UserDaoImpl implements GeneralDao<User> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        logger.info("create user");
    }

    @Override
    public User getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, login);
    }

    @Override
    public User getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        logger.info("user: {} created", user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> readAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for(User user : userList){
            logger.info("User List:" + user);
        }
        return userList;
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("user: {} updated", user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
        logger.info("user: {} deleted", user);
    }
}

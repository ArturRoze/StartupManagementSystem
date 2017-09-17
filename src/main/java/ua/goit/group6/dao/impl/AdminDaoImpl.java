package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.Admin;

import java.util.List;

/**
 * @author Artyr
 * @param <>
 *
 *
 */

@Repository
public class AdminDaoImpl implements AdminDao {

    private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public AdminDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        logger.info("create admin");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Admin getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Admin A where A.login like :login");
        query.setParameter("login", login);
        return (Admin) query.list().get(0);
    }

    @Override
    public Admin getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Admin.class, id);
    }

    @Override
    public void create(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        session.save(admin);
        logger.info("admin: {} created", admin);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Admin> readAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Admin> adminList = session.createQuery("from Admin").list();
        for(Admin admin : adminList){
            logger.info("admin List:" + admin);
        }
        return adminList;
    }

    @Override
    public void update(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        session.update(admin);
        logger.info("admin: {} updated", admin);
    }

    @Override
    public void delete(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(admin);
        logger.info("admin: {} deleted", admin);
    }
}

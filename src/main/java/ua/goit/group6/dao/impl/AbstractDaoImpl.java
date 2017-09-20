package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.group6.dao.GeneralDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDaoImpl<T> implements GeneralDao<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);

    private SessionFactory sessionFactory;

    Class<T> entityType = ((Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[1]);

    public AbstractDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(long id) {
        return getSession().get(entityType, id);
    }

    @Override
    public void create(T value) {
        getSession().save(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> readAll() {
        return (List<T>) getSession().getNamedQuery("getAll" + getCleanEntityClass(entityType) + "s").list();
    }

    @Override
    public void update(T value) {
        getSession().update(value);
    }

    @Override
    public void delete(T value) {
        getSession().remove(value);
    }

    @Override
    public void deleteById(long id) {
        Query deleteByIdQuery = getSession().createQuery("from User where id=:" + id);
        deleteByIdQuery.executeUpdate();
    }

    private String getCleanEntityClass(Class<?> entityClass) {
        String[] split = entityClass.toString().split("\\.");
        return split[split.length - 1];
    }
}

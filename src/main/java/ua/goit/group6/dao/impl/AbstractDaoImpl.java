package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.GeneralDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@NoRepositoryBean
public abstract class AbstractDaoImpl<T> implements GeneralDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoImpl.class);

    private SessionFactory sessionFactory;

    Class<T> entityType = ((Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0]);

    @Autowired
    public AbstractDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public T getById(int id) {
        LOGGER.info("Get user by id='{}' from repository", id);
        return getSession().get(entityType, id);
    }

    @Override
    @Transactional
    public void create(T value) {
        getSession().save(value);
        LOGGER.info("Save user:{} to repository", value);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> readAll() {
        LOGGER.info("Get all users from repository");
        return (List<T>) getSession().createQuery("from " + getCleanEntityClass(entityType)).list();
    }

    @Override
    @Transactional
    public void update(T value) {
        getSession().update(value);
        LOGGER.info("Update user:{} in repository", value);
    }

    @Override
    @Transactional
    public void delete(T value) {
        getSession().remove(value);
        LOGGER.info("delete user:{} from repository", value);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query deleteByIdQuery = getSession().createQuery("delete from " + getCleanEntityClass(entityType) + " where id = :id");
        deleteByIdQuery.setParameter("id", id);
        deleteByIdQuery.executeUpdate();
        LOGGER.info("delete user with id:{} from repository", id);
    }

    private String getCleanEntityClass(Class<?> entityClass) {
        String[] split = entityClass.toString().split("\\.");
        return split[split.length - 1];
    }
}

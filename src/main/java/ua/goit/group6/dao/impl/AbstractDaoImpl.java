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

/**
 * @author Artyr
 */
@NoRepositoryBean
public abstract class AbstractDaoImpl<T> implements GeneralDao<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

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
        LOGGER.info("Get {} by id='{}' from repository", entityType.getName(), id);
        return getSession().get(entityType, id);
    }

    @Override
    @Transactional
    public void create(T value) {
        LOGGER.info("Save entity:{} to repository", value);
        getSession().save(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> readAll() {
        LOGGER.info("Get all entities from repository");
        return (List<T>) getSession().createQuery("from " + getCleanEntityClass(entityType)).list();
    }

    @Override
    @Transactional
    public void update(T value) {
        LOGGER.info("Update entity:{} in repository", value);
        getSession().update(value);
    }

    @Override
    @Transactional
    public void delete(T value) {
        LOGGER.info("delete entity:{} from repository", value);
        getSession().remove(value);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query deleteByIdQuery = getSession().createQuery("delete from " + getCleanEntityClass(entityType) + " where id = :id");
        deleteByIdQuery.setParameter("id", id);
        LOGGER.info("delete entity with id:{} from repository", id);
        deleteByIdQuery.executeUpdate();
    }

    private String getCleanEntityClass(Class<?> entityClass) {
        String[] split = entityClass.toString().split("\\.");
        return split[split.length - 1];
    }
}

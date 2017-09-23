package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.service.BasicService;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract service for managing entities in repositories
 *
 * @param <T> generic for entities
 * @author Boiko Ivan
 * @see BasicService
 */
public abstract class AbstractBasicServiceImpl<T extends Serializable> implements BasicService<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final GeneralDao<T> dao;

    AbstractBasicServiceImpl(GeneralDao<T> dao) {
        this.dao = dao;
        LOGGER.info(this.getClass().toString() + " created");
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(long id) {
        LOGGER.info("Get entity by id='{}' from " + dao.getClass(), id);
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        LOGGER.info("Get all users from " + dao.getClass());
        return dao.readAll();
    }

    @Override
    @Transactional
    public void save(T value) {
        LOGGER.info("Save entity:{} to " + dao.getClass(), value);
        dao.create(value);
    }

    @Override
    @Transactional
    public void update(T value) {
        LOGGER.info("Update entity:{} in " + dao.getClass(), value);
        dao.update(value);
    }

    @Override
    @Transactional
    public void delete(T value) {
        LOGGER.info("Delete entity:{} from " + dao.getClass(), value);
        dao.delete(value);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        LOGGER.info("Delete entity with id:{} from " + dao.getClass(), id);
        dao.deleteById(id);
    }
}

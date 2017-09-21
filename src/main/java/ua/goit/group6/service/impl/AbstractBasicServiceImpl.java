package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.service.BasicService;

import java.io.Serializable;
import java.util.List;

public class AbstractBasicServiceImpl<T extends Serializable> implements BasicService<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final GeneralDao<T> dao;

    public AbstractBasicServiceImpl(GeneralDao<T> dao) {
        this.dao = dao;
    }

    private GeneralDao<T> getDao(){
        return dao;
    }

    @Override
    public T getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.readAll();
    }

    @Override
    public void save(T value) {
        dao.create(value);
    }

    @Override
    public void update(T value) {
        dao.update(value);
    }

    @Override
    public void delete(T value) {
        dao.delete(value);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}

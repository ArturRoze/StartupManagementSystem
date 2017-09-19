package ua.goit.group6.service;

import java.util.List;

/**
 * @param <T>
 * @author Boiko Ivan
 */
public interface BasicService<T> {

    T getById(long id);

    List<T> getAll();

    void save(T user);

    void update(T user);

    void delete(T user);

}

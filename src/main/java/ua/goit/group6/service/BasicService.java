package ua.goit.group6.service;

import java.util.List;

/**
 * @param <T>
 * @author Boiko Ivan
 */
public interface BasicService<T> {

    T getById(long id);

    List<T> getAll();

    void save(T value);

    void update(T value);

    void delete(T value);

}

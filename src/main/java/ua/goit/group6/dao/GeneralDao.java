package ua.goit.group6.dao;

import java.util.List;

/**
 * @author Artyr
 * @param <T>
 */

public interface GeneralDao<T> {

    T getById(long id);

    void create(T value);

    List<T> readAll();

    void update(T value);

    void delete(T value);
}

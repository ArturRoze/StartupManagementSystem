package ua.goit.group6.dao;

import java.util.List;

/**
 * @author Artyr
 */

public interface GeneralDao<T> {

    T getById(int id);

    void create(T value);

    List<T> readAll();

    void update(T value);

    void delete(T value);

    void deleteById(int id);
}

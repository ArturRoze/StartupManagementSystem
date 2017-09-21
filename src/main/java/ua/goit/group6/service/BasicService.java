package ua.goit.group6.service;

import java.util.List;

/**
 * Basic interface of all services
 * @param <T> generic for entities extends {@link Object}
 * @author Boiko Ivan
 */
public interface BasicService<T> {

    /**
     * Method searches entity in repository
     * @param id {@code long} id of entity to search
     * @return entity from repository
     */
    T getById(long id);

    /**
     * Method searches all entities in repository
     * @return {@link List} of all entities
     */
    List<T> getAll();

    /**
     * Method saves entity to repository
     * @param value entity to save
     */
    void save(T value);

    /**
     * Method updates entity in repository
     * @param value entity to update
     */
    void update(T value);

    /**
     * Method deletes entity from repository
     * @param value entity to delete
     */
    void delete(T value);

}

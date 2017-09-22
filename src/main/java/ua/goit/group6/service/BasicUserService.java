package ua.goit.group6.service;

import ua.goit.group6.model.BasicUser;

/**
 * Basic interface of basic users services
 *
 * @param <T> generic for entities extents {@link BasicUser}
 * @author Boiko Ivan
 * @see BasicService
 */
public interface BasicUserService<T extends BasicUser> extends BasicService<T> {

    /**
     * Method searches entity with given login in repository
     *
     * @param login string login to search in repository
     * @return child of {@link BasicUser} from repository
     */
    T getByLogin(String login);
}

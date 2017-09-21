package ua.goit.group6.service;

import ua.goit.group6.model.BasicUser;

/**
 * Basic interface of users services
 * @param <T> generic for entities extents {@link BasicUser}
 * @author Boiko Ivan
 */
public interface BasicUserService<T extends BasicUser> extends BasicService<T> {

    /**
     * @param login string login to search in repository
     * @return child of {@link BasicUser} from repository
     */
    T getByLogin(String login);
}

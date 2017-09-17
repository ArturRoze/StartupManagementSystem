package ua.goit.group6.service;

import ua.goit.group6.model.User;

import java.util.List;

/**
 * @author Boiko Ivan
 */
public interface UserService {

    User getById(long id);

    User getByLogin(String login);

    List<User> getAll();

    void save(User user);

    void update(User user);

    void delete(User user);
}

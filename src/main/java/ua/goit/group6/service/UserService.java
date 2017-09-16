package ua.goit.group6.service;

import ua.goit.group6.model.User;

import java.util.List;


public interface UserService {

    User getById(long id);

    List<User> getAll();

    User save(User user);

    User update(User user);

    void delete(User user);
}

package ua.goit.group6.dao;

import ua.goit.group6.model.User;

public interface UserDao extends GeneralDao<User> {

    User getByLogin(String login);
}

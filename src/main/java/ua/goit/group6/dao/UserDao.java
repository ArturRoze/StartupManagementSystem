package ua.goit.group6.dao;

import ua.goit.group6.model.User;

public interface UserDao{

    User getUserByLogin(String login);

}

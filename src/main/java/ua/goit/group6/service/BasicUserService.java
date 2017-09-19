package ua.goit.group6.service;

import ua.goit.group6.model.BasicUser;

public interface BasicUserService<T extends BasicUser> extends BasicService<T> {

    T getByLogin(String login);
}

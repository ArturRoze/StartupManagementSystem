package ua.goit.group6.dao;

import ua.goit.group6.model.Admin;

public interface AdminDao extends GeneralDao<Admin> {

    Admin getByLogin(String login);
}

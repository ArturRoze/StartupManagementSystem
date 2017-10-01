package ua.goit.group6.dao;

import ua.goit.group6.model.Admin;

/**
 * @author Artyr
 */
public interface AdminDao extends GeneralDao<Admin> {

    Admin getByLogin(String login);
}

package ua.goit.group6.service;

import ua.goit.group6.model.Admin;

import java.util.List;

/**
 * @author Boiko Ivan
 */
public interface AdminService {

    Admin getById(long id);

    Admin getByLogin(String login);

    List<Admin> getAll();

    void save(Admin admin);

    void update(Admin admin);

    void delete(Admin admin);
}

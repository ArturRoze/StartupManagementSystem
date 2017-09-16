package ua.goit.group6.service;

import ua.goit.group6.model.Admin;

import java.util.List;

public interface AdminService {

    Admin getById(long id);

    List<Admin> getAll();

    Admin save(Admin admin);

    Admin update(Admin admin);

    void delete(Admin admin);
}

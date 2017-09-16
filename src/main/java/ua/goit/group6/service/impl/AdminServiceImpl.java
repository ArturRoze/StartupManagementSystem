package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getById(long id) {
        return adminDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Admin save(Admin admin) {
        return null;
    }

    @Override
    @Transactional
    public Admin update(Admin admin) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Admin admin) {

    }
}

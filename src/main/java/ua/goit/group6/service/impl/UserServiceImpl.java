package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;
import ua.goit.group6.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}

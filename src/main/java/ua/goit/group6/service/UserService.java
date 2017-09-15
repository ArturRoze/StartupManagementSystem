package ua.goit.group6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;

@Service
public class UserService {

    //TODO User CRUD

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public User readById(long id){
        return userDao.findOne(id);
    }
}

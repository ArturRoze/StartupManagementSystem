package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import java.util.List;


/**
 * @author Boiko Ivan
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final AdminService adminService;

    @Autowired
    public UserServiceImpl(UserDao userDao, AdminService adminService) {
        LOGGER.info("UserServiceImpl created");
        this.userDao = userDao;
        this.adminService = adminService;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        LOGGER.info("Get user by id='{}' from repository", id);
        return userDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        LOGGER.info("Get user by login='{}' from repository", login);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        LOGGER.info("Get all users from repository");
        return null;
    }

    @Override
    @Transactional
    public User save(User user) {

        if (adminService.getByLogin(user.getLogin()) == null) {
            LOGGER.info("Save user:{} to repository", user);
            return userDao.saveAndFlush(user);
        } else {
            LOGGER.info("User with login:'{}' already exists", user.getLogin());
            //TODO Which exception should be thrown
            throw new RuntimeException("User with login:'" + user.getLogin() + "' already exists");
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        LOGGER.info("Update user:{} in repository", user);
        return null;
    }

    @Override
    @Transactional
    public void delete(User user) {
        LOGGER.info("Delete user:{} from repository", user);
    }
}

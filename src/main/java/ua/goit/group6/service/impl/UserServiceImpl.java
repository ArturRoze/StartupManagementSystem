package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;
import ua.goit.group6.service.UserService;

import java.util.List;


/**
 * Service for managing {@link User} in repository
 *
 * @author Boiko Ivan
 * @see UserService
 * @see UserDao
 * @see AdminDao
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    private final AdminDao adminDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, AdminDao adminDao) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        LOGGER.info("UserServiceImpl created");
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        LOGGER.info("Get user by id='{}' from repository", id);
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        LOGGER.info("Get user by login='{}' from repository", login);
        return userDao.getByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        LOGGER.info("Get all users from repository");
        return userDao.readAll();
    }

    /**
     * Method saves {@link User} to repository if no {@link ua.goit.group6.model.Admin}
     * with such login exists
     *
     * @param user User to save
     */
    @Override
    @Transactional
    public void save(User user) {
        if (adminDao.getByLogin(user.getLogin()) == null) {
            LOGGER.info("Save user:{} to repository", user);
            userDao.create(user);
        } else {
            LOGGER.info("User with login:'{}' already exists", user.getLogin());
            //TODO Which exception should be thrown?
            throw new RuntimeException("User with login:'" + user.getLogin() + "' already exists");
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        LOGGER.info("Update user:{} in repository", user);
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        LOGGER.info("Delete user:{} from repository", user);
        userDao.delete(user);
    }
}

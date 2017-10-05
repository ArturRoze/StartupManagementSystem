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


/**
 * Service for managing {@link User} in repository
 *
 * @author Boiko Ivan
 * @see AbstractBasicServiceImpl
 * @see UserService
 * @see UserDao
 * @see AdminDao
 */
@Service
public class UserServiceImpl extends AbstractBasicServiceImpl<User> implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserDao userDao;

    private final AdminDao adminDao;

    @Autowired
    public UserServiceImpl(AdminDao adminDao, UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
        this.adminDao = adminDao;
    }

    /**
     * Method reads user from repository
     *
     * @param login string login to search in repository
     * @return {@link User} from repository with given login
     */
    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        LOGGER.info("Get user with login:'{}'", login);
        return userDao.getByLogin(login);
    }

    /**
     * Method saves {@link User} to repository if no {@link ua.goit.group6.model.Admin}
     * or user with such login exists
     *
     * @param user User to save
     */
    @Override
    @Transactional
    public void save(User user) {

        if (adminDao.getByLogin(user.getLogin()) != null) {
            LOGGER.info("Admin with login:'{}' already exists", user.getLogin());

        } else if (userDao.getByLogin(user.getLogin()) != null) {
            LOGGER.info("User with login:'{}' already exists", user.getLogin());

        } else {
            super.save(user);
        }
    }
}

package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;
import ua.goit.group6.service.UserService;

import java.util.List;


/**
 * @author Boiko Ivan
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminDao adminDao;
    private final UserService userService;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, UserService userService) {
        LOGGER.info("AdminServiceImpl created");
        this.adminDao = adminDao;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getById(long id) {
        LOGGER.info("Get admin by id='{}' from repository", id);
        return adminDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getByLogin(String login) {
        LOGGER.info("Get admin by login='{}' from repository", login);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAll() {
        LOGGER.info("Get all users from repository");
        return null;
    }

    @Override
    @Transactional
    public Admin save(Admin admin) {
        if (userService.getByLogin(admin.getLogin()) == null) {
            LOGGER.info("Save admin:{} to repository", admin);
            return adminDao.saveAndFlush(admin);
        } else {
            LOGGER.info("User with login:'{}' already exists", admin.getLogin());
            //TODO Which exception should be thrown
            throw new RuntimeException("User with login:'" + admin.getLogin() + "' already exists");
        }
    }

    @Override
    @Transactional
    public Admin update(Admin admin) {
        LOGGER.info("Update admin:{} in repository", admin);
        return null;
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        LOGGER.info("Delete admin:{} from repository", admin);
    }
}

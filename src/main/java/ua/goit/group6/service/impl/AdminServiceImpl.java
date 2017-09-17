package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.dao.impl.AdminDaoImpl;
import ua.goit.group6.dao.impl.UserDaoImpl;
import ua.goit.group6.model.Admin;
import ua.goit.group6.service.AdminService;

import java.util.List;


/**
 * @author Boiko Ivan
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminDaoImpl adminDao;
    private final UserDaoImpl userDao;

    @Autowired
    public AdminServiceImpl(AdminDaoImpl adminDao, UserDaoImpl userDao) {
        LOGGER.info("AdminServiceImpl created");
        this.adminDao = adminDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getById(long id) {
        LOGGER.info("Get admin by id='{}' from repository", id);
        return adminDao.getById(id);
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
    public void save(Admin admin) {
        if (userDao.getByLogin(admin.getLogin()) == null) {
            LOGGER.info("Save admin:{} to repository", admin);
             adminDao.create(admin);
        } else {
            LOGGER.info("User with login:'{}' already exists", admin.getLogin());
            //TODO Which exception should be thrown?
            throw new RuntimeException("User with login:'" + admin.getLogin() + "' already exists");
        }
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        LOGGER.info("Update admin:{} in repository", admin);
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        LOGGER.info("Delete admin:{} from repository", admin);
    }
}

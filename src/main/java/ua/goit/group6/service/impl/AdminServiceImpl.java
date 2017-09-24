package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.Admin;
import ua.goit.group6.model.User;
import ua.goit.group6.service.AdminService;


/**
 * Service for managing {@link Admin} in repository
 *
 * @author Boiko Ivan
 * @see AbstractBasicServiceImpl
 * @see AdminService
 * @see AdminDao
 * @see UserDao
 */
@Service
public class AdminServiceImpl extends AbstractBasicServiceImpl<Admin> implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdminDao adminDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AdminServiceImpl(AdminDao adminDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        super(adminDao);
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        LOGGER.info("AdminServiceImpl created");
    }

    /**
     * Method reads user from repository
     * @param login string login to search in repository
     * @return {@link User} from repository with given login
     */
    @Override
    @Transactional(readOnly = true)
    public Admin getByLogin(String login) {
        return adminDao.getByLogin(login);
    }

    /**
     * Method saves {@link Admin} to repository if no {@link User}
     * with such login exists
     *
     * @param admin Admin to save
     */
    @Override
    @Transactional
    public void save(Admin admin) {

        if (userDao.getByLogin(admin.getLogin()) != null) {
            LOGGER.info("User with login:'{}' already exists", admin.getLogin());

        } else if (adminDao.getByLogin(admin.getLogin()) != null){
            LOGGER.info("Admin with login:'{}' already exists", admin.getLogin());

        }else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            super.save(admin);
        }
    }

    @Override
    public void update(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        super.update(admin);
    }
}

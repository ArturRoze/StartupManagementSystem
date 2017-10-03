package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartupServiceImpl extends AbstractBasicServiceImpl<Startup> implements StartupService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserDao userDao;

    @Autowired
    StartupServiceImpl(StartupDao dao, UserDao userDao) {
        super(dao);
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getLastNDesc(int n) {
        LOGGER.info("Returning last {} startups from repository", n);
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Startup::getRegistrationDate)
                        .reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getAllDesc() {
        LOGGER.info("Sorting startups by registration date in descending order");
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Startup::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getAllByUserIdDesc(int id) {
        return userDao.getById(id)
                .getStartups()
                .stream()
                .sorted(Comparator.comparing(Startup::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Startup> getAllByUserId(int id) {
        return new ArrayList<>(userDao.getById(id).getStartups());
    }
}

package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartupServiceImpl extends AbstractBasicServiceImpl<Startup> implements StartupService {

    private final UserDao userDao;

    @Autowired
    StartupServiceImpl(StartupDao dao, UserDao userDao) {
        super(dao);
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getLastNDesc(int n) {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Startup::getId)
                        .reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getAllDesc() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Startup::getId)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getAllByUserIdDesc(Long id) {
        return userDao.getById(id)
                .getStartups()
                .stream()
                .sorted(Comparator.comparing(Startup::getId)
                        .reversed())
                .collect(Collectors.toList());
    }
}

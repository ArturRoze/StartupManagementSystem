package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.StartupService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartupServiceImpl extends AbstractBasicServiceImpl<Startup> implements StartupService {

    @Autowired
    StartupServiceImpl(StartupDao dao) {
        super(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Startup> getLastN(int n) {
        //TODO complete
        return getAll();
//                .stream().sorted(Comparator.comparing(Startup::getRegistrationDate)).limit(n).collect(Collectors.toList());
    }
}

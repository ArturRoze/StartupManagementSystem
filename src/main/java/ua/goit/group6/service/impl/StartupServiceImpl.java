package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.Startup;
import ua.goit.group6.service.StartupService;
import ua.goit.group6.service.impl.AbstractBasicServiceImpl;

@Service
public class StartupServiceImpl extends AbstractBasicServiceImpl<Startup> implements StartupService {
@Autowired
    StartupServiceImpl(StartupDao dao) {
        super(dao);
    }
}

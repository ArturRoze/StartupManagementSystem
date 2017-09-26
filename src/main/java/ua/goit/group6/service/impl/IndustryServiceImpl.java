package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.IndustryDao;
import ua.goit.group6.model.Industry;
import ua.goit.group6.service.IndustryService;

@Service
public class IndustryServiceImpl extends AbstractBasicServiceImpl<Industry> implements IndustryService {

    @Autowired
    IndustryServiceImpl(IndustryDao dao) {
        super(dao);
    }
}

package ua.goit.group6.service.impl;

import ua.goit.group6.dao.IndustryDao;
import ua.goit.group6.model.Industry;
import ua.goit.group6.service.IndustryService;

public class IndustryServiceImpl extends AbstractBasicServiceImpl<Industry> implements IndustryService {

    IndustryServiceImpl(IndustryDao dao) {
        super(dao);
    }
}

package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group6.dao.GeneralDao;
import ua.goit.group6.model.Region;
import ua.goit.group6.service.RegionService;

@Service
public class RegionServiceImpl extends AbstractBasicServiceImpl<Region> implements RegionService {

    @Autowired
    RegionServiceImpl(GeneralDao<Region> dao) {
        super(dao);
    }
}

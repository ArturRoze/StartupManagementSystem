package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.Offer;
import ua.goit.group6.service.OfferService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl extends AbstractBasicServiceImpl<Offer> implements OfferService {

    private final UserDao userDao;

    @Autowired
    OfferServiceImpl(OfferDao dao, UserDao userDao) {
        super(dao);
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> getAllByUserId(int id) {
        return new ArrayList<>(userDao.getById(id).getOffers());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> getAllDesc() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Offer::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }
}

package ua.goit.group6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.News;
import ua.goit.group6.service.NewsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final StartupDao startupDao;
    private final OfferDao offerDao;
    private final UserDao userDao;

    @Autowired
    public NewsServiceImpl(StartupDao startupDao, OfferDao offerDao, UserDao userDao) {
        this.startupDao = startupDao;
        this.offerDao = offerDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAll() {
        List<News> news = new ArrayList<>();
        news.addAll(startupDao.readAll());
        news.addAll(offerDao.readAll());
        return news;
    }

    @Override
    public List<News> getAllDesc() {

        return getAll().stream()
                .sorted(Comparator.comparing(News::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAllByUserIdDesc(int id) {
        List<News> news = new ArrayList<>();
        news.addAll(userDao.getById(id).getStartups());
        news.addAll(userDao.getById(id).getOffers());

        return news.stream()
                .sorted(Comparator.comparing(News::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    public int getCountOfPages(int newsPerPage) {
        return (getAll().size() % newsPerPage) == 0 ? (getAll().size() / newsPerPage) : (getAll().size() / newsPerPage) + 1;
    }

    @Override
    public List<News> getNPageWithMNews(int pageNumber, int newsAmount) {
        return getAllDesc()
                .stream()
                .skip((pageNumber - 1) * newsAmount)
                .limit(newsAmount)
                .collect(Collectors.toList());
    }
}

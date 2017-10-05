package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.News;
import ua.goit.group6.service.NewsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for all news
 *
 * @author Boiko Ivan
 * @see NewsService
 * @see StartupDao
 * @see OfferDao
 */
@Service
public class NewsServiceImpl implements NewsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StartupDao startupDao;
    private final OfferDao offerDao;

    @Autowired
    public NewsServiceImpl(StartupDao startupDao, OfferDao offerDao) {
        LOGGER.info("Creating " + getClass().getSimpleName());
        this.startupDao = startupDao;
        this.offerDao = offerDao;
    }

    /**
     * Method collects all news from repositories
     *
     * @return list of all {@link News} from repositories
     */
    @Override
    @Transactional(readOnly = true)
    public List<News> getAll() {
        LOGGER.info("Reading all news from repositories");
        List<News> news = new ArrayList<>();
        news.addAll(startupDao.readAll());
        news.addAll(offerDao.readAll());
        return news;
    }

    /**
     * Method sorts list of news by date of addition in decrease order
     *
     * @return list of news sorted by registration date
     */
    @Override
    public List<News> getAllByRegistration() {
        LOGGER.info("Sorting all news by decreasing registration date");
        return getAll().stream()
                .sorted(Comparator.comparing(News::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    /**
     * Method calculates number of pages with given amount of news on each
     *
     * @param newsPerPage number of {@link News} to display on every page
     * @return count of pages with given amount of news on each page
     */
    @Override
    public int getCountOfPages(int newsPerPage) {
        LOGGER.info("Calculating number of pages with {} news on them", newsPerPage);
        return (getAll().size() % newsPerPage) == 0
                ? (getAll().size() / newsPerPage)
                : (getAll().size() / newsPerPage) + 1;
    }

    /**
     * Method skips all previous pages and returns up to given amount of news
     *
     * @param pageNumber number of page to return
     * @param newsAmount number of {@link News} to display on page
     * @return list of news on given page
     */
    @Override
    public List<News> getNPageWithMNews(int pageNumber, int newsAmount) {
        LOGGER.info("Returning {} page with {} news on it", pageNumber, newsAmount);
        return getAllByRegistration()
                .stream()
                .skip((pageNumber - 1) * newsAmount)
                .limit(newsAmount)
                .collect(Collectors.toList());
    }
}

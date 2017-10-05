package ua.goit.group6.service;

import ua.goit.group6.model.News;

import java.util.List;

/**
 * Interface for {@link News} service
 *
 * @author Boiko Ivan
 */
public interface NewsService {

    List<News> getAll();

    List<News> getAllByRegistration();

    int getCountOfPages(int newsPerPage);

    List<News> getNPageWithMNews(int pageNumber, int newsAmount);
}

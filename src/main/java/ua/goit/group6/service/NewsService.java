package ua.goit.group6.service;

import ua.goit.group6.model.News;

import java.util.List;

public interface NewsService {

    List<News> getAll();

    List<News> getAllDesc();

    List<News> getAllByUserIdDesc(int id);

    int getCountOfPages(int newsPerPage);

    List<News> getNPageWithMNews(int pageNumber, int newsAmount);
}

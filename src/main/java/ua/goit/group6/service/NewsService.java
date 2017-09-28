package ua.goit.group6.service;

import ua.goit.group6.model.News;
import ua.goit.group6.model.User;

import java.util.List;

public interface NewsService {

    List<News> getAllDesc();

    List<News> getAllByUserIdDesc(int id);

}

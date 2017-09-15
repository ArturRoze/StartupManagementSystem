package ua.goit.group6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.group6.model.User;

public interface UserDao extends JpaRepository<User, Long> {



}

package ua.goit.group6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.group6.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Long> {



}

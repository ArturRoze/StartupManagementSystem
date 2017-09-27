package ua.goit.group6.service;

import ua.goit.group6.model.Startup;

import java.util.List;

public interface StartupService extends BasicService<Startup> {
    
    List<Startup> getLastNDesc(int n);
    List<Startup> getAllDesc();

    List<Startup> getAllByUserIdDesc(Long id);

}

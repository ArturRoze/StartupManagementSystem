package ua.goit.group6.service;

import ua.goit.group6.model.Startup;

import java.util.List;

public interface StartupService extends BasicService<Startup> {
    
    List<Startup> getLastN(int n);
    
}

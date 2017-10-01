package ua.goit.group6.service;

import ua.goit.group6.model.Offer;

import java.util.List;

public interface OfferService extends BasicService<Offer> {

    List<Offer> getAllByUserId(int id);
    List<Offer> getAllDesc();

}

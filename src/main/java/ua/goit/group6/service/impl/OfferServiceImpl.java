package ua.goit.group6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.model.Offer;
import ua.goit.group6.service.OfferService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing {@link Offer} in repository
 *
 * @author Artur
 * @see AbstractBasicServiceImpl
 * @see OfferService
 * @see OfferDao
 */
@Service
public class OfferServiceImpl extends AbstractBasicServiceImpl<Offer> implements OfferService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    OfferServiceImpl(OfferDao dao) {
        super(dao);
    }

    /**
     * Method sorts list of offers by date of addition in decrease order
     *
     * @return list of offers sorted by registration date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Offer> getAllDesc() {
        LOGGER.info("Sorting offers by registration date in descending order");
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Offer::getRegistrationDate)
                        .reversed())
                .collect(Collectors.toList());
    }
}

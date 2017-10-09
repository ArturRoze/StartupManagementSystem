package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.model.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        MockitoAnnotations.initMocks(OfferServiceImpl.class);
    }

    @InjectMocks
    private OfferServiceImpl offerService;

    @Mock
    private OfferDao offerDao;

    @Mock
    private Offer offer;

    @Test
    public void getAllDescTest_not_null() throws Exception {

        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        assertNotNull(offerService.getAllDesc());
    }

    @Test
    public void getAllDesc_noNews() throws Exception {

        when(offerDao.readAll()).thenReturn(Collections.emptyList());

        List<Offer> newsExpected = new ArrayList<>();
        newsExpected.addAll(offerDao.readAll());

        assertEquals(0, offerService.getAllDesc().size());
        assertEquals(newsExpected, offerService.getAllDesc());
    }
}
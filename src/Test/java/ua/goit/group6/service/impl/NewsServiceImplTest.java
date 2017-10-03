package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.News;
import ua.goit.group6.model.Offer;
import ua.goit.group6.model.Startup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        MockitoAnnotations.initMocks(NewsServiceImpl.class);
    }

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private StartupDao startupDao;

    @Mock
    private OfferDao offerDao;

    @Mock
    private Startup startup;

    @Mock
    private Offer offer;

    private List<News> news;

    @Test
    public void getAllNotNull() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        assertNotNull(newsService.getAll());
    }

    @Test
    public void getAll() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        news = new ArrayList<>();
        news.addAll(startupDao.readAll());
        news.addAll(offerDao.readAll());

        assertEquals(news, newsService.getAll());
    }

    @Test
    public void getAllDesc() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        Timestamp startupReqDate = mock(Timestamp.class);
        Timestamp offerReqDate = mock(Timestamp.class);

        when(startup.getRegistrationDate()).thenReturn(startupReqDate);
        when(offer.getRegistrationDate()).thenReturn(offerReqDate);

        when(startupReqDate.compareTo(offerReqDate)).thenReturn(1);

        news = new ArrayList<>();
        news.addAll(startupDao.readAll());
        news.addAll(offerDao.readAll());

        assertEquals(news, newsService.getAllDesc());

    }

    @Test
    public void getCountOfPages() throws Exception {

        int newsPerPage = 2;
        int expectedCount = 1;

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        assertEquals(expectedCount, newsService.getCountOfPages(newsPerPage));
    }



    @Test
    public void getNPageWithMNews() throws Exception {
    }

}
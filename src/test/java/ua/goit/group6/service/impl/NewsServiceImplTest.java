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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Boiko Ivan
 */
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

    private List<News> newsExpected;

    @Test
    public void getAll_NotNull() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        assertNotNull(newsService.getAll());
    }

    @Test
    public void getAll_return() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        newsExpected = new ArrayList<>();
        newsExpected.addAll(startupDao.readAll());
        newsExpected.addAll(offerDao.readAll());

        assertEquals(newsExpected, newsService.getAll());
    }

    @Test
    public void getAll_noNews() throws Exception {

        when(startupDao.readAll()).thenReturn(Collections.emptyList());
        when(offerDao.readAll()).thenReturn(Collections.emptyList());

        newsExpected = new ArrayList<>();
        newsExpected.addAll(startupDao.readAll());
        newsExpected.addAll(offerDao.readAll());

        assertEquals(0, newsService.getAll().size());

        assertEquals(newsExpected, newsService.getAll());
    }

    @Test
    public void getAllByRegistration() throws Exception {

        Timestamp startupReqDate = mock(Timestamp.class);
        Timestamp offerReqDate = mock(Timestamp.class);

        when(startup.getRegistrationDate()).thenReturn(startupReqDate);
        when(offer.getRegistrationDate()).thenReturn(offerReqDate);

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup));
        when(offerDao.readAll()).thenReturn(Collections.singletonList(offer));

        assertEquals(1, startupReqDate.compareTo(offerReqDate));

        newsExpected = new ArrayList<>();
        newsExpected.addAll(startupDao.readAll());
        newsExpected.addAll(offerDao.readAll());

        assertEquals(newsExpected, newsService.getAllByRegistration());

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
    public void getNPageWithMNews_onePageCase() throws Exception {

        int newsPerPage = 6;
        int pageNumber = 1;

        Timestamp startupReqDate = mock(Timestamp.class);
        Timestamp offerReqDate = mock(Timestamp.class);

        when(startup.getRegistrationDate()).thenReturn(startupReqDate);
        when(offer.getRegistrationDate()).thenReturn(offerReqDate);

        when(startupDao.readAll()).thenReturn(Arrays.asList(startup, startup));
        when(offerDao.readAll()).thenReturn(Arrays.asList(offer, offer));

        newsExpected = new ArrayList<>();
        newsExpected.addAll(startupDao.readAll());
        newsExpected.addAll(offerDao.readAll());

        assertEquals(newsExpected, newsService.getNPageWithMNews(pageNumber, newsPerPage));
    }

    @Test
    public void getNPageWithMNews_moreThanOnePageCase() throws Exception {

        int newsPerPage = 3;
        int pageNumber = 2;

        Timestamp startupRegDate = mock(Timestamp.class);
        Timestamp offerRegDate = mock(Timestamp.class);

        when(startup.getRegistrationDate()).thenReturn(startupRegDate);
        when(offer.getRegistrationDate()).thenReturn(offerRegDate);

        when(startupDao.readAll()).thenReturn(Arrays.asList(startup, startup));
        when(offerDao.readAll()).thenReturn(Arrays.asList(offer, offer));

        newsExpected = new ArrayList<>();
        newsExpected.add(offerDao.readAll().get(1));

        assertEquals(newsExpected, newsService.getNPageWithMNews(pageNumber, newsPerPage));
    }

    @Test
    public void getNPageWithMNews_noNewsCase() throws Exception {

        int newsPerPage = 6;
        int pageNumber = 1;


        when(startupDao.readAll()).thenReturn(Collections.emptyList());
        when(offerDao.readAll()).thenReturn(Collections.emptyList());

        newsExpected = new ArrayList<>();
        newsExpected.addAll(startupDao.readAll());
        newsExpected.addAll(offerDao.readAll());

        assertEquals(0, newsService.getAll().size());

        assertEquals(newsExpected, newsService.getNPageWithMNews(pageNumber, newsPerPage));
    }
}
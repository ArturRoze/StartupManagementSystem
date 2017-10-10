package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.group6.dao.OfferDao;
import ua.goit.group6.model.Offer;
import ua.goit.group6.model.Startup;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class OfferDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private OfferDao offerDao;

    private Offer offer;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        offerDao = new OfferDaoImpl(sessionFactory);
        offer = mock(Offer.class);
    }

    @Test
    public void sessionTest() {
        when(session.get(Offer.class, 1)).thenReturn(offer);

        assertEquals(offer, offerDao.getById(1));
        verify(session, timeout(100)).get(Offer.class, 1);
    }

    @Test
    public void nullTest() {
        when(session.get(Startup.class, 1)).thenReturn(null);

        assertNull(offerDao.getById(1));
        verify(session, timeout(100)).get(Offer.class, 1);
    }

    @Test(expected = Exception.class)
    public void exceptionTest() {
        when(session.get(Offer.class, -1)).thenThrow(new Exception());

        offerDao.getById(-1);
    }
}
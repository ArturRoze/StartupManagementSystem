package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.group6.dao.CountryDao;
import ua.goit.group6.model.Country;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class CountryDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private CountryDao countryDao;

    private Country country;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        countryDao = new CountryDaoImpl(sessionFactory);
        country = mock(Country.class);
    }

    @Test
    public void sessionTest() {
        when(session.get(Country.class, 1)).thenReturn(country);

        assertEquals(country, countryDao.getById(1));
        verify(session, timeout(100)).get(Country.class, 1);
    }

    @Test
    public void nullTest() {
        when(session.get(Country.class, 1)).thenReturn(null);

        assertNull(countryDao.getById(1));
        verify(session, timeout(100)).get(Country.class, 1);
    }

    @Test(expected = Exception.class)
    public void exceptionTest() {
        when(session.get(Country.class, -1)).thenThrow(new Exception());

        countryDao.getById(-1);
    }
}
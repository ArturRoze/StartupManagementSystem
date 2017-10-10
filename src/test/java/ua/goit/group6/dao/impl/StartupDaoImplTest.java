package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.group6.dao.StartupDao;
import ua.goit.group6.model.Startup;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class StartupDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private StartupDao startupDao;

    private Startup startup;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        startupDao = new StartupDaoImpl(sessionFactory);
        startup = mock(Startup.class);
    }

    @Test
    public void sessionTest() {
        when(session.get(Startup.class, 1)).thenReturn(startup);

        assertEquals(startup, startupDao.getById(1));
        verify(session, timeout(100)).get(Startup.class, 1);
    }

    @Test
    public void nullTest() {
        when(session.get(Startup.class, 1)).thenReturn(null);

        assertNull(startupDao.getById(1));
        verify(session, timeout(100)).get(Startup.class, 1);
    }

    @Test(expected = Exception.class)
    public void exceptionTest() {
        when(session.get(Startup.class, -1)).thenThrow(new Exception());

        startupDao.getById(-1);
    }
}
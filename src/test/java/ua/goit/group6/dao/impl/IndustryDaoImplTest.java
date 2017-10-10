package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.group6.dao.IndustryDao;
import ua.goit.group6.model.Industry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class IndustryDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private IndustryDao industryDao;

    private Industry industry;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        industryDao = new IndustryDaoImpl(sessionFactory);
        industry = mock(Industry.class);
    }

    @Test
    public void sessionTest() {
        when(session.get(Industry.class, 1)).thenReturn(industry);

        assertEquals(industry, industryDao.getById(1));
        verify(session, timeout(100)).get(Industry.class, 1);
    }

    @Test
    public void nullTest() {
        when(session.get(Industry.class, 1)).thenReturn(null);

        assertNull(industryDao.getById(1));
        verify(session, timeout(100)).get(Industry.class, 1);
    }

    @Test(expected = Exception.class)
    public void exceptionTest() {
        when(session.get(Industry.class, -1)).thenThrow(new Exception());

        industryDao.getById(-1);
    }
}
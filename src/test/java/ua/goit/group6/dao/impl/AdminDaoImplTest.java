package ua.goit.group6.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.model.Admin;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class AdminDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Query query;

    private AdminDao adminDao;

    private Admin admin;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        query = mock(Query.class);
        admin = mock(Admin.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        adminDao = new AdminDaoImpl(sessionFactory);
    }

    @Test
    public void getByLoginAdminTest() throws Exception {
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "userLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.singletonList(admin));

        assertEquals(admin, adminDao.getByLogin("userLogin"));

        verify(session, times(1)).createQuery(anyString());
        verify(query, atLeast(1)).list();
    }

    @Test
    public void getByLoginAdminTest_returned_null() throws Exception {
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "userLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        assertEquals(null, adminDao.getByLogin("userLogin"));

        verify(session, times(1)).createQuery(anyString());
        verify(query, atLeast(1)).list();
    }

    @Test
    public void getById() throws Exception {
        when(session.get(Admin.class, 1)).thenReturn(admin);
        when(admin.getId()).thenReturn(1);

        assertEquals(admin, adminDao.getById(1));
        assertEquals(1, admin.getId());

        verify(session, atLeast(1)).get(Admin.class, 1);
        verify(admin, times(1)).getId();
    }

    @Test(expected = NullPointerException.class)
    public void getById_returned_null() throws Exception {
        when(session.get(Admin.class, 1)).thenReturn(null);
        when(admin.getId()).thenThrow(NullPointerException.class);

        assertEquals(null, adminDao.getById(1));

        verify(session, atMost(1)).get(Admin.class, 0);
        admin.getId();
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void readAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }
}
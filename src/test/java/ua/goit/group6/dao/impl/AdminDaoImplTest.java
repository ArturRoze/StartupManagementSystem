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
import java.util.List;

import static org.junit.Assert.*;
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

    private Integer id = 1;

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
        //arrange
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "userLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.singletonList(admin));

        //action
        adminDao.getByLogin("userLogin");

        //assert
        assertEquals(admin, adminDao.getByLogin("userLogin"));
        verify(session, times(2)).createQuery(anyString());
        verify(query, atLeast(2)).list();
    }

    @Test
    public void getByLoginAdminTest_returned_null() throws Exception {
        //arrange
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "userLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        //action
        adminDao.getByLogin("userLogin");

        //assert
        assertEquals(null, adminDao.getByLogin("userLogin"));
        verify(session, times(2)).createQuery(anyString());
        verify(query, atLeast(2)).list();
    }

    @Test
    public void getById() throws Exception {
        //arrange
        when(session.get(Admin.class, 1)).thenReturn(admin);
        when(admin.getId()).thenReturn(1);

        //action
        adminDao.getById(1);

        //assert
        assertEquals(admin, adminDao.getById(1));
        assertEquals(1, admin.getId());
        verify(session, atLeast(1)).get(Admin.class, 1);
        verify(admin, times(1)).getId();
    }

    @Test(expected = NullPointerException.class)
    public void getById_returned_null() throws Exception {
        //arrange
        when(session.get(Admin.class, 1)).thenReturn(null);
        when(admin.getId()).thenThrow(NullPointerException.class);

        //action
        adminDao.getById(1);

        //assert
        assertEquals(null, adminDao.getById(1));
        verify(session, atMost(1)).get(Admin.class, 0);
        admin.getId();
    }

    @Test
    public void create() throws Exception {
        //arrange
        when(admin.getLogin()).thenReturn("test");
        doAnswer(invocation -> null).when(session).save(admin);

        //action
        adminDao.create(admin);

        //assert
        verify(session, atLeastOnce()).save(admin);
    }

    @Test
    public void testCreateSpy() throws Exception {
        //arrange
        AdminDao dao = new AdminDaoImpl(sessionFactory);
        dao = spy(dao);
        doAnswer(invocation -> null).when(dao).create(admin);

        //action
        dao.create(admin);

        //assert
        verify(dao, atLeastOnce()).create(admin);
    }

    @Test
    public void readAll() throws Exception {
        //arrange
        List<Admin> mockList = mock(List.class);
        when(mockList.get(0)).thenReturn(admin);
        when(mockList.get(1)).thenReturn(null);

        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(mockList);

        //action
        adminDao.readAll();

        //assert
        assertEquals(mockList, adminDao.readAll());
        assertFalse(mockList.isEmpty());
        assertNull("must be null", mockList.get(1));
        assertNotNull("return admin", mockList.get(0));
        assertEquals(admin, mockList.get(0));

        verify(query, times(2)).list();
        verify(mockList, atLeast(2)).get(0);
    }

    @Test
    public void update() throws Exception {
        //arrange
        doAnswer(invocation -> null).when(session).update(admin);

        //action
        adminDao.update(admin);

        //assert
        verify(session, timeout(100)).update(admin);
    }

    @Test
    public void delete() throws Exception {
        //arrange
        doAnswer(invocation -> null).when(session).remove(admin);

        //action
        adminDao.delete(admin);

        //assert
        verify(session, times(1)).remove(any());
    }

    @Test
    public void deleteById() throws Exception {
        //arrange
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("id", id)).thenReturn(query);
        doAnswer(invocation -> null).when(query).executeUpdate();

        //action
        adminDao.deleteById(id);

        //assert
        verify(session, times(1)).createQuery(anyString());
    }
}
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
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.User;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UserDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Query query;

    private UserDao userDao;

    private User user;

    private Integer id = 1;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        query = mock(Query.class);
        user = mock(User.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDao = new UserDaoImpl(sessionFactory);
    }


    @Test
    public void getByLoginUserTest() throws Exception {
        //arrange
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "userLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.singletonList(user));

        //action
        userDao.getByLogin("userLogin");

        //assert
        assertEquals(user, userDao.getByLogin("uLogin"));
        verify(session, times(2)).createQuery(anyString());
        verify(query, atLeast(2)).list();
    }

    @Test
    public void getByLoginUserTest_returned_null() throws Exception {
        //arrange
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter("login", "uLogin")).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        //action
        userDao.getByLogin("uLogin");

        //assert
        assertEquals(null, userDao.getByLogin("uLogin"));
        verify(session, times(2)).createQuery(anyString());
        verify(query, atLeast(2)).list();
    }
}
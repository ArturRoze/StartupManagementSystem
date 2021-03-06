package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.goit.group6.dao.AdminDao;
import ua.goit.group6.dao.UserDao;
import ua.goit.group6.model.Admin;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        MockitoAnnotations.initMocks(AdminServiceImpl.class);
    }

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminDao adminDao;

    @Mock
    private UserDao userDao;

    @Test
    public void getByLogin_returns_admin_from_systemTest() throws Exception {
        // arrange
        Admin expectedAdmin = configureAdminDaoToGetLogin();
        when(adminDao.getByLogin("login")).thenReturn(expectedAdmin);

        // action
        Admin actualAdmin = adminService.getByLogin("login");

        //assert
        assertNotNull("get by login from UserServiceImpl returned not Null object", actualAdmin);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void getById() throws Exception {
        // arrange
        Admin expectedAdmin = configureAdminDaoToGetLogin();
        when(adminDao.getById(expectedAdmin.getId())).thenReturn(expectedAdmin);

        // action
        Admin actualAdmin = adminService.getById(1);

        //assert
        assertNotNull("get by id from UserServiceImpl returned not Null object", actualAdmin);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void getByLogin_returns_null_if_such_login_does_not_existTest() throws Exception {
        // arrange
        configureAdminDaoToGetLogin();

        // action
        Admin actualAdmin = adminService.getByLogin("invalidLogin");

        //assert
        assertNull("returned null object if such login does not exist", actualAdmin);
    }

    @Test
    public void saveTest() throws Exception {
        // arrange
        Admin admin = configureAdminDaoToGetLogin();
        doAnswer(invocation -> null).when(adminDao).create(admin);

        // action
        adminService.save(admin);

        //assert
        verify(adminDao, times(1)).create(admin);
    }

    @Test
    public void updateTest() throws Exception {
        // arrange
        Admin admin = configureAdminDaoToGetLogin();
        doAnswer(invocation -> null).when(adminDao).update(admin);

        // action
        adminService.update(admin);

        //assert
        verify(adminDao, times(1)).update(admin);
    }

    @Test
    public void deleteTest() throws Exception {
        // arrange
        Admin admin = configureAdminDaoToGetLogin();
        when(adminService.getAll()).thenReturn(Arrays.asList(admin, admin));
        doAnswer(i -> null).when(adminDao).delete(admin);

        // action
        adminService.delete(admin);

        //assert
        verify(adminDao, times(1)).delete(admin);
    }

    @Test
    public void delete_empty_adminListTest() throws Exception {

        //arrange
        Admin admin = configureAdminDaoToGetLogin();
        when(adminService.getAll()).thenReturn(Collections.emptyList());

        //action
        adminService.delete(admin);

        //assert
        verify(adminDao, times(0)).delete(admin);
    }

    @Test
    public void deleteByIdTest() throws Exception {

        //arrange
        Admin admin = configureAdminDaoToGetLogin();
        when(adminService.getAll()).thenReturn(Arrays.asList(admin, admin));
        doAnswer(i -> null).when(adminDao).deleteById(1);

        //action
        adminService.deleteById(1);

        //assert
        verify(adminDao, times(1)).deleteById(1);
    }

    @Test
    public void deleteById_empty_adminListTest() throws Exception {

        //arrange
        when(adminService.getAll()).thenReturn(Collections.emptyList());

        //action
        adminService.deleteById(1);

        //assert
        verify(adminDao, times(0)).deleteById(1);
    }

    private Admin configureAdminDaoToGetLogin() {

        String adminLogin = "login";

        Admin admin = new Admin();
        admin.setId(1);
        admin.setLogin(adminLogin);
        admin.setPassword("123");
        admin.setEmail("admin@gmail.com");

        return admin;
    }
}
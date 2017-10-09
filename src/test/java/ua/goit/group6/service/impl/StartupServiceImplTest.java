package ua.goit.group6.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.goit.group6.dao.StartupDao;
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
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Boiko Ivan
 */
@RunWith(MockitoJUnitRunner.class)
public class StartupServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        initMocks(StartupServiceImpl.class);
    }

    @InjectMocks
    private StartupServiceImpl startupService;

    @Mock
    private StartupDao startupDao;

    @Mock
    private Startup startup_1;

    @Mock
    private Startup startup_2;

    @Test
    public void getLastN_notNull() {

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup_1));

        assertNotNull(startupService.getLastN(6));
    }

    @Test
    public void getLastN() {

        Timestamp startupRegDate_1 = new Timestamp(1);
        Timestamp startupRegDate_2 = new Timestamp(2);

        when(startup_1.getRegistrationDate()).thenReturn(startupRegDate_1);
        when(startup_2.getRegistrationDate()).thenReturn(startupRegDate_2);

        List<Startup> expected = Collections.singletonList(startup_2);

        when(startupDao.readAll()).thenReturn(Arrays.asList(startup_1, startup_2));

        assertEquals(expected, startupService.getLastN(1));
    }

    @Test
    public void getAllByDecreaseRegistration_notNull(){
        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup_1));

        assertNotNull(startupService.getAllByDecreaseRegistration());
    }

    @Test
    public void getAllByDecreaseRegistration_noStartups(){

        List<Startup> expected = new ArrayList<>();

        when(startupDao.readAll()).thenReturn(Collections.emptyList());

        assertEquals(expected, startupService.getAllByDecreaseRegistration());
    }

    @Test
    public void getAllByDecreaseRegistration_oneStartup(){

        List<Startup> expected = new ArrayList<>();
        expected.add(startup_1);

        when(startupDao.readAll()).thenReturn(Collections.singletonList(startup_1));

        assertEquals(expected, startupService.getAllByDecreaseRegistration());
    }

}

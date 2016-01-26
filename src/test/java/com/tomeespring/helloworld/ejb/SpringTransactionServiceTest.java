package com.tomeespring.helloworld.ejb;

import com.tomeespring.helloworld.jaxb.SpringJAXBHelloWorld;
import com.tomeespring.helloworld.jpa.dao.SpringJPASalutationDAO;
import com.tomeespring.helloworld.jpa.dao.SpringJPATechnologyDAO;
import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import com.tomeespring.helloworld.jpa.entity.SpringJPATechnologyEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by valerie on 1/13/16.
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringTransactionServiceTest {

    @Mock
    private SpringJPASalutationDAO springJpaSalutationDAO;

    @Mock
    private SpringJPATechnologyDAO springJpaTechnologyDAO;

    private SpringTransactionService springTransactionService = new SpringTransactionService();

    @Before
    public void setUp() throws Exception {

        springTransactionService.setSpringJpaSalutationDAO(springJpaSalutationDAO);
        springTransactionService.setSpringJpaTechnologyDAO(springJpaTechnologyDAO);
    }

    @After
    public void tearDown() throws Exception {
        springJpaSalutationDAO = null;
        springJpaTechnologyDAO = null;
        springTransactionService = null;
    }

    @Test
    public void testAddSalutation() throws Exception {

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutation("Hello Mockito!");

        springTransactionService.addSalutation(springJaxbHelloWorld);

        Mockito.verify(springJpaSalutationDAO, Mockito.times(1)).persist(any(SpringJPASalutationEntity.class));
    }

    @Test
    public void testUpdateSalutation() throws Exception {

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutationId(new Long(1));

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation("Hi Mockito!!");
        springJpaSalutationEntity.setId(new Long(1));

        when(this.springJpaSalutationDAO.find(new Long(1))).thenReturn(springJpaSalutationEntity);

        springTransactionService.updateSalutation(springJaxbHelloWorld);

        Mockito.verify(springJpaSalutationDAO, Mockito.times(1)).update(any(SpringJPASalutationEntity.class));

    }

    @Test
    public void testGetSalutation() throws Exception {

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation("Hi Mockito!!");
        springJpaSalutationEntity.setId(new Long(1));

        when(this.springJpaSalutationDAO.find(new Long(1))).thenReturn(springJpaSalutationEntity);

        SpringJAXBHelloWorld springJaxbHelloWorld = springTransactionService.getSalutation(new Long(1));
        assertTrue(springJaxbHelloWorld.getSalutation().equals("Hi Mockito!!"));
    }

    @Test
    public void testAddTechnology() throws Exception {

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutation("Hello Mockito!");
        springJaxbHelloWorld.setTechnology("EJB");
        springJaxbHelloWorld.setDescription("Unit testing my EJB");

        springTransactionService.addTechnology(springJaxbHelloWorld);

        Mockito.verify(springJpaSalutationDAO, Mockito.times(1)).persist(any(SpringJPASalutationEntity.class));
        Mockito.verify(springJpaTechnologyDAO, Mockito.times(1)).persist(any(SpringJPATechnologyEntity.class));
    }

    @Test
    public void testAddTechnologyCascadePersist() throws Exception {

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutation("Hello Mockito!");
        springJaxbHelloWorld.setTechnology("EJB");
        springJaxbHelloWorld.setDescription("Unit testing my EJB");

        springTransactionService.addTechnologyCascadePersist(springJaxbHelloWorld);

        Mockito.verify(springJpaSalutationDAO, Mockito.times(0)).persist(any(SpringJPASalutationEntity.class));
        Mockito.verify(springJpaTechnologyDAO, Mockito.times(1)).persist(any(SpringJPATechnologyEntity.class));
    }

    @Test
    public void testUpdateTechnology() throws Exception {

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation("Hi Mockito!!");
        springJpaSalutationEntity.setId(new Long(1));

        SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
        springJpaTechnologyEntity.setTechnology("Mockito");
        springJpaTechnologyEntity.setDescription("Mockito is a test framework!");
        springJpaTechnologyEntity.setId(new Long(1));

        springJpaTechnologyEntity.setSpringJpaSalutationEntity(springJpaSalutationEntity);

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setTechnologyId(new Long(1));
        springJaxbHelloWorld.setSalutationId(new Long(1));
        springJaxbHelloWorld.setSalutation("Hello Mockito!");
        springJaxbHelloWorld.setTechnology("EJB");
        springJaxbHelloWorld.setDescription("Unit testing my EJB");

        when(this.springJpaTechnologyDAO.find(new Long(1))).thenReturn(springJpaTechnologyEntity);

        springTransactionService.updateTechnology(springJaxbHelloWorld);

        Mockito.verify(springJpaTechnologyDAO, Mockito.times(1)).update(any(SpringJPATechnologyEntity.class));
    }

    @Test
    public void testUpdateTechnologyCascadeUpdate() throws Exception {

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation("Hi Mockito!!");
        springJpaSalutationEntity.setId(new Long(1));

        SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
        springJpaTechnologyEntity.setTechnology("Mockito");
        springJpaTechnologyEntity.setDescription("Mockito is a test framework!");
        springJpaTechnologyEntity.setId(new Long(1));

        springJpaTechnologyEntity.setSpringJpaSalutationEntity(springJpaSalutationEntity);

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setTechnologyId(new Long(1));
        springJaxbHelloWorld.setSalutationId(new Long(1));
        springJaxbHelloWorld.setSalutation("Hello Mockito!");
        springJaxbHelloWorld.setTechnology("EJB");
        springJaxbHelloWorld.setDescription("Unit testing my EJB");

        when(this.springJpaTechnologyDAO.find(new Long(1))).thenReturn(springJpaTechnologyEntity);

        springTransactionService.updateTechnologyCascadeUpdate(springJaxbHelloWorld);

        Mockito.verify(springJpaTechnologyDAO, Mockito.times(1)).update(any(SpringJPATechnologyEntity.class));
    }

    @Test
    public void testGetTechnology() throws Exception {

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation("Hi Mockito!!");
        springJpaSalutationEntity.setId(new Long(1));

        SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
        springJpaTechnologyEntity.setTechnology("Mockito");
        springJpaTechnologyEntity.setDescription("Mockito is a test framework!");
        springJpaTechnologyEntity.setId(new Long(1));
        springJpaTechnologyEntity.setSpringJpaSalutationEntity(springJpaSalutationEntity);

        when(this.springJpaTechnologyDAO.find(new Long(1))).thenReturn(springJpaTechnologyEntity);

        SpringJAXBHelloWorld springJaxbHelloWorld = springTransactionService.getTechnology(new Long(1));
        assertTrue(springJaxbHelloWorld.getSalutation().equals("Hi Mockito!!"));
    }

    @Test
    public void testFindAll() throws Exception {

        SpringJPASalutationEntity springJpaSalutationEntityOne = new SpringJPASalutationEntity();
        springJpaSalutationEntityOne.setSalutation("Hi Mockito!!");
        springJpaSalutationEntityOne.setId(new Long(1));

        SpringJPATechnologyEntity springJpaTechnologyEntityOne = new SpringJPATechnologyEntity();
        springJpaTechnologyEntityOne.setTechnology("Mockito");
        springJpaTechnologyEntityOne.setDescription("Mockito is a test framework!");
        springJpaTechnologyEntityOne.setId(new Long(1));
        springJpaTechnologyEntityOne.setSpringJpaSalutationEntity(springJpaSalutationEntityOne);

        SpringJPASalutationEntity springJpaSalutationEntityTwo = new SpringJPASalutationEntity();
        springJpaSalutationEntityTwo.setSalutation("Hi Mockito!!");
        springJpaSalutationEntityTwo.setId(new Long(1));

        SpringJPATechnologyEntity springJpaTechnologyEntityTwo = new SpringJPATechnologyEntity();
        springJpaTechnologyEntityTwo.setTechnology("Mockito");
        springJpaTechnologyEntityTwo.setDescription("Mockito is a test framework!");
        springJpaTechnologyEntityTwo.setId(new Long(1));
        springJpaTechnologyEntityTwo.setSpringJpaSalutationEntity(springJpaSalutationEntityOne);

        List<SpringJPATechnologyEntity> entityList = new ArrayList<>();
        entityList.add(springJpaTechnologyEntityOne);
        entityList.add(springJpaTechnologyEntityTwo);

        when(this.springJpaTechnologyDAO.findAll()).thenReturn(entityList);

        List<SpringJAXBHelloWorld> helloWorldList = springTransactionService.findAll();
        assertTrue(helloWorldList.size() == 2);

    }
}
package com.tomeespring.helloworld.jpa.dao;

import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by valerie on 1/13/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringJPASalutationDAOTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworldH2Test");

    private static SpringJPASalutationDAO springJpaSalutationDAO = new SpringJPASalutationDAO();

    @BeforeClass
    public static void setUp() throws Exception {
        springJpaSalutationDAO.setEntityManager(emf.createEntityManager());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        emf = null;
        springJpaSalutationDAO = null;
    }

    @Test
    public void test01Persist() throws Exception {

     try{
            SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
            springJpaSalutationEntity.setSalutation("test");

            springJpaSalutationDAO.getEntityManager().getTransaction().begin();
            springJpaSalutationDAO.persist(springJpaSalutationEntity);
            springJpaSalutationDAO.getEntityManager().getTransaction().commit();

            springJpaSalutationEntity = springJpaSalutationDAO.find(new Long(1));

            assertNotNull(springJpaSalutationEntity.getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void test02Find() throws Exception {
        try{
            springJpaSalutationDAO.getEntityManager().getTransaction().begin();
            SpringJPASalutationEntity springJpaSalutationEntity = springJpaSalutationDAO.find(new Long(1));
            springJpaSalutationDAO.getEntityManager().getTransaction().commit();

            assertNotNull(springJpaSalutationEntity.getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test03Update() throws Exception {
        try{
            springJpaSalutationDAO.getEntityManager().getTransaction().begin();
            SpringJPASalutationEntity springJpaSalutationEntity = springJpaSalutationDAO.find(new Long(1));

            String oldName = springJpaSalutationEntity.getSalutation();

            springJpaSalutationEntity.setSalutation("val");

            springJpaSalutationDAO.update(springJpaSalutationEntity);
            springJpaSalutationDAO.getEntityManager().getTransaction().commit();

            springJpaSalutationDAO.getEntityManager().getTransaction().begin();
            springJpaSalutationEntity = springJpaSalutationDAO.find(new Long(1));
            springJpaSalutationDAO.getEntityManager().getTransaction().commit();

            boolean assertFlag = springJpaSalutationEntity.getSalutation().equals(oldName);
            assertFalse(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

}
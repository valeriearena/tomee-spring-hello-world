package com.tomeespring.helloworld.jpa.dao;

import com.tomeespring.helloworld.jpa.entity.SpringJPATechnologyEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by valerie on 1/13/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringJPATechnologyDAOTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworldH2Test");

    private static SpringJPATechnologyDAO springJpaTechnologyDAO = new SpringJPATechnologyDAO();

    @BeforeClass
    public static void setUpClass(){
        springJpaTechnologyDAO.setEntityManager(emf.createEntityManager());
    }

    @AfterClass
    public static void tearDown(){
        emf = null;
        springJpaTechnologyDAO = null;
    }

    @Test
    public void test01Persist(){
        try{
            SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
            springJpaTechnologyEntity.getSpringJpaSalutationEntity().setSalutation("test");
            springJpaTechnologyEntity.setTechnology("test");
            springJpaTechnologyEntity.setDescription("test");

            springJpaTechnologyDAO.getEntityManager().getTransaction().begin();
            springJpaTechnologyDAO.persist(springJpaTechnologyEntity);
            springJpaTechnologyDAO.getEntityManager().getTransaction().commit();

            springJpaTechnologyEntity = springJpaTechnologyDAO.find(new Long(1));

            assertNotNull(springJpaTechnologyEntity.getId());
            assertNotNull(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test02Find(){
        try{
            springJpaTechnologyDAO.getEntityManager().getTransaction().begin();
            SpringJPATechnologyEntity springJpaTechnologyEntity = springJpaTechnologyDAO.find(new Long(1));
            springJpaTechnologyDAO.getEntityManager().getTransaction().commit();

            assertNotNull(springJpaTechnologyEntity.getId());
            assertNotNull(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test03Update(){
        try{
            springJpaTechnologyDAO.getEntityManager().getTransaction().begin();
            SpringJPATechnologyEntity springJpaTechnologyEntity = springJpaTechnologyDAO.find(new Long(1));

            String oldName = springJpaTechnologyEntity.getTechnology();

            springJpaTechnologyEntity.setTechnology("jpa");

            springJpaTechnologyDAO.update(springJpaTechnologyEntity);
            springJpaTechnologyDAO.getEntityManager().getTransaction().commit();

            springJpaTechnologyDAO.getEntityManager().getTransaction().begin();
            springJpaTechnologyEntity = springJpaTechnologyDAO.find(new Long(1));
            springJpaTechnologyDAO.getEntityManager().getTransaction().commit();

            boolean assertFlag = springJpaTechnologyEntity.getTechnology().equals(oldName);
            assertFalse(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test04FindAll(){
        try{
            springJpaTechnologyDAO.getEntityManager().getTransaction().begin();
            List<SpringJPATechnologyEntity> list = springJpaTechnologyDAO.findAll();
            springJpaTechnologyDAO.getEntityManager().getTransaction().commit();
            boolean assertFlag = (list.size() == 1);

            assertTrue(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }

    }

}
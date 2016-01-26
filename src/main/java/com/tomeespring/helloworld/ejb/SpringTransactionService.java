package com.tomeespring.helloworld.ejb;

import com.tomeespring.helloworld.jaxb.SpringJAXBHelloWorld;
import com.tomeespring.helloworld.jpa.dao.SpringJPASalutationDAO;
import com.tomeespring.helloworld.jpa.dao.SpringJPATechnologyDAO;
import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import com.tomeespring.helloworld.jpa.entity.SpringJPATechnologyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringTransactionService {

    private static final Logger logger = Logger.getLogger(SpringSingletonService.class.getName());

    @Autowired
    private SpringJPASalutationDAO springJpaSalutationDAO;

    @Autowired
    private SpringJPATechnologyDAO springJpaTechnologyDAO;

    @Transactional
    public void addSalutation(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE,"----------------PERSIST SALUTATION [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation(springJaxbHelloWorld.getSalutation());

        springJpaSalutationDAO.persist(springJpaSalutationEntity);

    }

    @Transactional
    public void updateSalutation(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE SALUTATION [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPASalutationEntity springJpaSalutationEntity = springJpaSalutationDAO.find(springJaxbHelloWorld.getSalutationId());

        springJpaSalutationEntity.setSalutation(springJaxbHelloWorld.getSalutation());
        springJpaSalutationDAO.update(springJpaSalutationEntity);

    }

    @Transactional
    public SpringJAXBHelloWorld getSalutation(Long id){

        SpringJPASalutationEntity springJpaSalutationEntity = springJpaSalutationDAO.find(id);

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutation(springJpaSalutationEntity.getSalutation());
        springJaxbHelloWorld.setSalutationId(springJpaSalutationEntity.getId());

        logger.log(Level.FINE, "----------------GET SALUTATION [{0}]!!!----------------", springJaxbHelloWorld);

        return springJaxbHelloWorld;
    }

    @Transactional
    public void addTechnology(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY and SALUTATION SEPARATELY [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation(springJaxbHelloWorld.getSalutation());

        SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
        springJpaTechnologyEntity.setTechnology(springJaxbHelloWorld.getTechnology());
        springJpaTechnologyEntity.setDescription(springJaxbHelloWorld.getDescription());

        springJpaSalutationDAO.persist(springJpaSalutationEntity);
        springJpaTechnologyDAO.persist(springJpaTechnologyEntity);

    }

    @Transactional
    public void addTechnologyCascadePersist(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY CASCADE PERSIST TO SALUTATION [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation(springJaxbHelloWorld.getSalutation());

        SpringJPATechnologyEntity springJpaTechnologyEntity = new SpringJPATechnologyEntity();
        springJpaTechnologyEntity.setTechnology(springJaxbHelloWorld.getTechnology());
        springJpaTechnologyEntity.setDescription(springJaxbHelloWorld.getDescription());

        springJpaTechnologyEntity.setSpringJpaSalutationEntity(springJpaSalutationEntity);

        springJpaTechnologyDAO.persist(springJpaTechnologyEntity);

    }

    @Transactional
    public void updateTechnology(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPATechnologyEntity springJpaTechnologyEntity = springJpaTechnologyDAO.find(springJaxbHelloWorld.getTechnologyId());

        springJpaTechnologyEntity.setTechnology(springJaxbHelloWorld.getTechnology());
        springJpaTechnologyEntity.setDescription(springJaxbHelloWorld.getDescription());

        springJpaTechnologyDAO.update(springJpaTechnologyEntity);
    }

    @Transactional
    public void updateTechnologyCascadeUpdate(SpringJAXBHelloWorld springJaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY CASCADE UPDATE TO SALUTATION [{0}]!!!----------------", springJaxbHelloWorld);

        SpringJPATechnologyEntity springJpaTechnologyEntity = springJpaTechnologyDAO.find(springJaxbHelloWorld.getTechnologyId());

        springJpaTechnologyEntity.setTechnology(springJaxbHelloWorld.getTechnology());
        springJpaTechnologyEntity.setDescription(springJaxbHelloWorld.getDescription());

        springJpaTechnologyEntity.getSpringJpaSalutationEntity().setSalutation(springJaxbHelloWorld.getSalutation());

        springJpaTechnologyDAO.update(springJpaTechnologyEntity);
    }

    @Transactional
    public SpringJAXBHelloWorld getTechnology(Long id){

        SpringJPATechnologyEntity springJpaTechnologyEntity = springJpaTechnologyDAO.find(id);

        SpringJAXBHelloWorld springJaxbHelloWorld = new SpringJAXBHelloWorld();
        springJaxbHelloWorld.setSalutation(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getSalutation());
        springJaxbHelloWorld.setSalutationId(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getId());
        springJaxbHelloWorld.setTechnologyId(springJpaTechnologyEntity.getId());
        springJaxbHelloWorld.setTechnology(springJpaTechnologyEntity.getTechnology());
        springJaxbHelloWorld.setDescription(springJpaTechnologyEntity.getDescription());

        logger.log(Level.FINE, "----------------GET TECHNOLOGY [{0}]!!!----------------", springJaxbHelloWorld);

        return springJaxbHelloWorld;
    }

    @Transactional
    public List<SpringJAXBHelloWorld> findAll(){

        List<SpringJPATechnologyEntity> springJpaTechnologyEntityList = springJpaTechnologyDAO.findAll();

        SpringJAXBHelloWorld springJaxbHelloWorld = null;
        List<SpringJAXBHelloWorld> jaxbGreetingsList = new ArrayList<>();

        for(SpringJPATechnologyEntity springJpaTechnologyEntity : springJpaTechnologyEntityList){
            springJaxbHelloWorld = new SpringJAXBHelloWorld();

            springJaxbHelloWorld.setSalutationId(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getId());
            springJaxbHelloWorld.setSalutation(springJpaTechnologyEntity.getSpringJpaSalutationEntity().getSalutation());

            springJaxbHelloWorld.setTechnologyId(springJpaTechnologyEntity.getId());
            springJaxbHelloWorld.setTechnology(springJpaTechnologyEntity.getTechnology());
            springJaxbHelloWorld.setDescription(springJpaTechnologyEntity.getTechnology());
            jaxbGreetingsList.add(springJaxbHelloWorld);
        }

        logger.log(Level.FINE, "----------------GET ALL TECHNOLOGIES [{0}]!!!----------------", jaxbGreetingsList.size());

        return jaxbGreetingsList;
    }

    public SpringJPASalutationDAO getSpringJpaSalutationDAO() {
        return springJpaSalutationDAO;
    }

    public void setSpringJpaSalutationDAO(SpringJPASalutationDAO springJpaSalutationDAO) {
        this.springJpaSalutationDAO = springJpaSalutationDAO;
    }

    public SpringJPATechnologyDAO getSpringJpaTechnologyDAO() {
        return springJpaTechnologyDAO;
    }

    public void setSpringJpaTechnologyDAO(SpringJPATechnologyDAO springJpaTechnologyDAO) {
        this.springJpaTechnologyDAO = springJpaTechnologyDAO;
    }
}

package com.tomeespring.helloworld.jpa.dao;

import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Scope(value = "singleton")
public class SpringJPASalutationDAO {

    private static final Logger logger = LoggerFactory.getLogger(SpringJPASalutationDAO.class);

    @PersistenceContext(unitName = "helloworldDB")
    private EntityManager entityManager;

    public void persist(SpringJPASalutationEntity springJpaSalutationEntity){
        entityManager.persist(springJpaSalutationEntity);
    }

    public SpringJPASalutationEntity find(Long id){

        return entityManager.find(SpringJPASalutationEntity.class, id);
    }

    public SpringJPASalutationEntity update(SpringJPASalutationEntity springJpaSalutationEntity){
        return entityManager.merge(springJpaSalutationEntity);
    }

    public void delete(Long id){
        entityManager.remove(id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

package com.tomeespring.helloworld.jpa.dao;

import com.tomeespring.helloworld.jpa.entity.SpringJPATechnologyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Scope(value = "singleton")
public class SpringJPATechnologyDAO {

    private static final Logger logger = LoggerFactory.getLogger(SpringJPATechnologyDAO.class);

    @PersistenceContext(unitName="helloworldDB")
    private EntityManager entityManager;

    public void persist(SpringJPATechnologyEntity springJpaTechnologyEntity){
        entityManager.persist(springJpaTechnologyEntity);
    }

    public SpringJPATechnologyEntity find(Long id){

        return entityManager.find(SpringJPATechnologyEntity.class, id);
    }

    public SpringJPATechnologyEntity update(SpringJPATechnologyEntity springJpaTechnologyEntity){
        return entityManager.merge(springJpaTechnologyEntity);
    }

    public void delete(Long id){
        entityManager.remove(id);
    }

    public List<SpringJPATechnologyEntity> findAll() {
        TypedQuery<SpringJPATechnologyEntity> query  = entityManager.createNamedQuery("SpringJPATechnologyEntity.findAll", SpringJPATechnologyEntity.class);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

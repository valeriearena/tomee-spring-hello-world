package com.tomeespring.helloworld.ejb;

import com.tomeespring.helloworld.jaxb.SpringJAXBHelloWorld;
import com.tomeespring.helloworld.jms.SpringJMSProducer;
import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringQueueService {

    private static final Logger logger = Logger.getLogger(SpringQueueService.class.getName());

    @Autowired
    private SpringJMSProducer springJmsProducer;

    public void send(SpringJAXBHelloWorld springJaxbHelloWorld){

        SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();
        springJpaSalutationEntity.setSalutation(springJaxbHelloWorld.getSalutation());

        springJmsProducer.send(springJpaSalutationEntity);

    }
}

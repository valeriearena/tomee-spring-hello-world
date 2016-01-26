package com.tomeespring.helloworld.jms;

import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringJMSConsumer implements MessageListener {

    private static final Logger logger = Logger.getLogger(SpringJMSConsumer.class.getName());

    public void onMessage(Message message) {


        try{Thread.sleep(60000);}catch(Exception e){}

        SpringJPASalutationEntity springJpaSalutationEntity = null;
        try {
            ObjectMessage objectMessage = (ObjectMessage)message;
            springJpaSalutationEntity = (SpringJPASalutationEntity)objectMessage.getObject();

            logger.log(Level.FINE,"REPLICATION - CONSUMER JMS MESSAGE: [{0}]", springJpaSalutationEntity);
        }
        catch (Exception e) {
            logger.log(Level.FINE,"REPLICATION - ERROR SENDING JMS MESSAGE {0}.", e);
            e.printStackTrace();
        }

    }

    }

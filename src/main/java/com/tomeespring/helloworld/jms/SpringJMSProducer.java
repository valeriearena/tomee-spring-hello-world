package com.tomeespring.helloworld.jms;

import com.tomeespring.helloworld.jpa.entity.SpringJPASalutationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringJMSProducer {

    private static final Logger logger = Logger.getLogger(SpringJMSProducer.class.getName());

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private ConnectionFactory connectionFactory;

    public void send(final SpringJPASalutationEntity springJpaSalutationEntity){

        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                ObjectMessage message = session.createObjectMessage();
                message.setObject(springJpaSalutationEntity);
                return message;
            }
        });

    }

}

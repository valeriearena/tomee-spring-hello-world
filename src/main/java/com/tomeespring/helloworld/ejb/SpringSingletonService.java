package com.tomeespring.helloworld.ejb;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringSingletonService {

    private static final Logger logger = Logger.getLogger(SpringSingletonService.class.getName());

    private String salutation = "SINGLETON HELLO";

    public void setSalutation(String salutation){

        logger.log(Level.FINE,"----------------SET SpringSingletonService [{0}]!!!----------------", this.salutation);
        this.salutation = salutation;
    }

    public String getSalutation() {
        logger.log(Level.FINE,"----------------GET SpringSingletonService [{0}]!!!----------------", this.salutation);
        return salutation;
    }
}

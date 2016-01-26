package com.tomeespring.helloworld.ejb;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringAsynchronous {

    private static final Logger logger = Logger.getLogger(SpringAsynchronous.class.getName());

    @Async
    public void loop(){

        for(int i = 0; i < 10000; i++){

            if(i%1000 == 0){
                logger.log(Level.FINE,"*******************ASYNCHRONOUS TASK RUNNING: cnt [{0}]*******************", i);
                try{Thread.sleep(5000);}catch(Exception e){}
            }
        }
    }


}

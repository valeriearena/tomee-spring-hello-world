package com.tomeespring.helloworld.ejb;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(value = "singleton")
public class SpringScheduleService {

    private static final Logger logger = Logger.getLogger(SpringScheduleService.class.getName());

    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void doWork(){
        logger.log(Level.FINE,"----------------HELLO from SpringScheduleService!!!----------------");
    }

    @PostConstruct
    public void init(){
        logger.log(Level.FINE,"*******************INITIALIZING SpringScheduleService!!!*******************");
    }
}
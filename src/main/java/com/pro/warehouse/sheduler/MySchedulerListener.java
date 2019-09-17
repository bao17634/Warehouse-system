package com.pro.warehouse.sheduler;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class MySchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MyScheduler myScheduler;

    @Autowired
    MyJobFactory myJobFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            myScheduler.schedulerJob();
            System.out.println("SynchronizedData job  start...");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean(name ="mySchedulerFactoryBean")
    public SchedulerFactoryBean mySchedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setStartupDelay(1);
        bean.setJobFactory(myJobFactory);
        return bean;
    }

}

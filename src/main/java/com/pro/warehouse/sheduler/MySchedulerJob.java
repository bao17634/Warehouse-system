package com.pro.warehouse.sheduler;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySchedulerJob implements Job {

    @Autowired
    DaliyComputeShedule daliyComputeShedule;

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        daliyComputeShedule.computeCount();
        System.out.println("每日统计数量 " + dateFormat().format(new Date()));
    }

}

package com.pro.warehouse.sheduler;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MySchedulerJob2 implements Job {

    @Autowired
    DaliyComputeShedule daliyComputeShedule;

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        daliyComputeShedule.sendMail();
        System.out.println("邮件已发送" + dateFormat().format(new Date()));
    }

}

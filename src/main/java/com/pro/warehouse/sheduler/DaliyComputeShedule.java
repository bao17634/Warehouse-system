package com.pro.warehouse.sheduler;

import com.alibaba.fastjson.JSON;
import com.pro.warehouse.Service.*;
import com.pro.warehouse.constant.MailReceiver;
import com.pro.warehouse.constant.Operation;
import com.pro.warehouse.dao.DaliyCountReposity;
import com.pro.warehouse.mail.MailService;
import com.pro.warehouse.pojo.DaliyCount;
import com.pro.warehouse.pojo.StockHUB;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@EnableAsync
@Component
public class DaliyComputeShedule {
    public final static long SECOND = 1*1000;
    @Autowired
    private ApplyEnterService applyEnterService;
    @Autowired
    private ApplyOutService applyOutService;
    @Autowired
    private EntrepotStatusService entrepotStatusService;
    @Autowired
    private DaliyCountReposity daliyCountReposity;

    Logger logger = LoggerFactory.getLogger(DaliyComputeShedule.class.getName());

    @Autowired
    MailService mailService;
    @Autowired
    ReportService reportService;
    @Autowired
    ExcelService excelService;
    @Autowired
    LogService logService;

    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 统计每天的入库数，出库数，库存数
     */
//    @Async
//    @Scheduled(cron = "0 55 23 * * ?" )
    public void computeCount(){
        System.out.println("定时任务demo1开始......");
        long begin = System.currentTimeMillis();
        int sizeOfIn = applyEnterService.getNumberOfTodayApplyEnter();
        int sizeOfOut = applyOutService.getNumberOfTodayApplyEnter();
        int sizeOfEntre = entrepotStatusService.getAllEntrepotCount();
        DaliyCount count = new DaliyCount();
        count.setSize(sizeOfIn);
        count.setType("入库数量");
        count.setComputeDate(new Date());
        daliyCountReposity.save(count);
        DaliyCount count1 = new DaliyCount();
        count1.setSize(sizeOfOut);
        count1.setType("出库数量");
        count1.setComputeDate(new Date());
        daliyCountReposity.save(count1);
        DaliyCount count2 = new DaliyCount();
        count2.setSize(sizeOfEntre);
        count2.setType("库存数量");
        count2.setComputeDate(new Date());
        daliyCountReposity.save(count2);

        System.out.println("数量分别未"+sizeOfEntre+" "+sizeOfIn+" " +sizeOfOut);
        long end = System.currentTimeMillis();
        System.out.println("定时任务demo1结束，共耗时：[" + (end-begin)+ "]毫秒");

        logService.saveOpLog("系统执行", Operation.AUTO_COMPUTE_NUMBER.getOperation(),"成功", JSON.toJSONString(new ArrayList<DaliyCount>(){{
            add(count);
            add(count1);
            add(count2);
        }}));
    }

    /**
     *
     */
//    @Async
//    @Scheduled(cron = "0 40 20 * * ?" )
    public void sendMail(){
        try {
            logger.debug("发送邮件");
            List<StockHUB> stockHUBS = reportService.generateStoreReoport(new Date());
            if(stockHUBS==null||stockHUBS.size()==0){
                stockHUBS.add(new StockHUB());
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(new Date());
            String filePath = "files"+ File.separator+System.currentTimeMillis()+".xlsx";
            excelService.ExportEcel(stockHUBS,"库存报表["+format+"]","库存报表",filePath,StockHUB.class);
            String subject = format+"的库存报表";
            String content = "系统自动发送库存报表，"+" 报表生成日期："+format+",邮件为系统发送，请勿回复！";
            mailService.sendAttachmentsMail(MailReceiver.receiver, "库存报表", content,filePath);
            mailService.sendAttachmentsMail("1249505842@qq.com", "库存报表", content,filePath);
            logService.saveOpLog("系统",Operation.SEND_EMAIL_REPORT.getOperation(),"成功",content+"，存储位置："+filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

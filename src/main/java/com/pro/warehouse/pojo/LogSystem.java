package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LogSystem {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int logRid;//id

    @Column(length = 1024)
    private String logMessage;//日志内容

    private Date logDate;//日志记录时间

    public int getLogRid() {
        return logRid;
    }

    public void setLogRid(int logRid) {
        this.logRid = logRid;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}

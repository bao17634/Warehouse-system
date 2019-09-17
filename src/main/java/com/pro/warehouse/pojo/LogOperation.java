package com.pro.warehouse.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LogOperation {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int logRid;//id

    private String username;//用户
    private String operation;//操作

    private String result;//结果
    @Column(length = 1024)
    private String detail;//详情

    private Date date;

    public int getLogRid() {
        return logRid;
    }

    public void setLogRid(int logRid) {
        this.logRid = logRid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

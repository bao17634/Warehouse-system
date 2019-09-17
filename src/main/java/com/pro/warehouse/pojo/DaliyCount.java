package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 每天定时统计当天的入库数，出库数和库存数
 */
@Entity
public class DaliyCount {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int rowId;//id

    private int size;//数量

    private String type;//类型，包含入库，出库，库存

    private Date computeDate;//统计日期


    public DaliyCount() {
    }

    public DaliyCount(int size, String type, Date computeDate) {
        this.size = size;
        this.type = type;
        this.computeDate = computeDate;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getComputeDate() {
        return computeDate;
    }

    public void setComputeDate(Date computeDate) {
        this.computeDate = computeDate;
    }
}

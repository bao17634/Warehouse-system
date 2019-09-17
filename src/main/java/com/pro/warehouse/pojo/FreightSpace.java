package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 仓位表
 */
@Entity
public class FreightSpace {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;//id
    private String freightCode;//仓位代码
    private String remark;//备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

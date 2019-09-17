package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 料号对照表
 */
@Entity
public class MateriContrast {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;//id
    private String materialCode;//入库料号
    private String materialSpec;//入库规格
    private String demandName;//提货单位名称
    private String demandMaeriCode;//提货单位料号
    private String brandName;//品牌名
    private String supplyCode;//供应商代码
    private String demandCode;//提货商代码
    private String realId;//保留，第一个ID只做编号功能
    private String supplyMateriCode;//供应商料号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getDemandMaeriCode() {
        return demandMaeriCode;
    }

    public void setDemandMaeriCode(String demandMaeriCode) {
        this.demandMaeriCode = demandMaeriCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public String getSupplyMateriCode() {
        return supplyMateriCode;
    }

    public void setSupplyMateriCode(String supplyMateriCode) {
        this.supplyMateriCode = supplyMateriCode;
    }
}

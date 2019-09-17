package com.pro.warehouse.pojo;


import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * HUB货物库存报表
 */
public class StockHUB {
    @Excel(name = "供应商名称")
    private String supplierName;//供应商名称
    @Excel(name = "VendorCode")
    private String vendorCode;
    @Excel(name = "供应商料号")
    private String supplyMateriCode;//供应商料号
    @Excel(name = "维信料号")
    private String weixinCode;//维信料号
    @Excel(name = "生产日期")
    private String prodate; //生产日期
    @Excel(name = "良品库存")
    private String goodNum;
    @Excel(name = "入库日期")
    private String storageDate;

    public StockHUB(String supplierName, String vendorCode, String supplyMateriCode, String weixinCode, String prodate, String goodNum, String storageDate) {
        this.supplierName = supplierName;
        this.vendorCode = vendorCode;
        this.supplyMateriCode = supplyMateriCode;
        this.weixinCode = weixinCode;
        this.prodate = prodate;
        this.goodNum = goodNum;
        this.storageDate = storageDate;
    }

    public StockHUB() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getSupplyMateriCode() {
        return supplyMateriCode;
    }

    public void setSupplyMateriCode(String supplyMateriCode) {
        this.supplyMateriCode = supplyMateriCode;
    }

    public String getWeixinCode() {
        return weixinCode;
    }

    public void setWeixinCode(String weixinCode) {
        this.weixinCode = weixinCode;
    }

    public String getProdate() {
        return prodate;
    }

    public void setProdate(String prodate) {
        this.prodate = prodate;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }
}

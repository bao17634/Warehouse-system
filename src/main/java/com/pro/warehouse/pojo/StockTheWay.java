package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * HUB货物在途报表
 */
public class StockTheWay {
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
    @Excel(name = "在途库存")
    private String stockTheway;//在途库存

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

    public String getStockTheway() {
        return stockTheway;
    }

    public void setStockTheway(String stockTheway) {
        this.stockTheway = stockTheway;
    }

    @Override
    public String toString() {
        return "StockTheWay{" +
                "supplierName='" + supplierName + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", supplyMateriCode='" + supplyMateriCode + '\'' +
                ", weixinCode='" + weixinCode + '\'' +
                ", prodate='" + prodate + '\'' +
                ", stockTheway='" + stockTheway + '\'' +
                '}';
    }
}

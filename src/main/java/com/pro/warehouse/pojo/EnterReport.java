package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class EnterReport {
    @Excel(name="入库编号")
    private String enterCode;//入库编号,不唯一
    @Excel(name="供应商名称")
    private String goodsFrom;//供货商
    @Excel(name="品名")
    private String productName;//品名
    @Excel(name="料号")
    private String materialCode;//材料ID
    @Excel(name="规格")
    private String goodsSpec;//规格
    @Excel(name="生产日期")
    private String produceDate;//生产日期
    @Excel(name="数量")
    private int goodsNumber;//数量
    @Excel(name="单位")
    private String goodsUnit="PCS";//数量单位
    @Excel(name="件数")
    private int suitNumber;//件数
    @Excel(name="净重")
    private double suttleWeight;//净重（KGS）
    @Excel(name="毛重")
    private double roughWeight;//毛重
    @Excel(name="")
    private String billNumber;//发票号码
    @Excel(name="实际入库日期")
    private Date applyDate;//申请日期


    public String getEnterCode() {
        return enterCode;
    }

    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    public String getGoodsFrom() {
        return goodsFrom;
    }

    public void setGoodsFrom(String goodsFrom) {
        this.goodsFrom = goodsFrom;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }



    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public int getSuitNumber() {
        return suitNumber;
    }

    public void setSuitNumber(int suitNumber) {
        this.suitNumber = suitNumber;
    }

    public double getSuttleWeight() {
        return suttleWeight;
    }

    public void setSuttleWeight(double suttleWeight) {
        this.suttleWeight = suttleWeight;
    }

    public double getRoughWeight() {
        return roughWeight;
    }

    public void setRoughWeight(double roughWeight) {
        this.roughWeight = roughWeight;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
}

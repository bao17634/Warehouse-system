package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class OutReport {
    //出库编号
    @Excel(name="出库编号")
    private String outCode;
    // 提货商
    @Excel(name="提货单位")
    private String demandName;
    @Excel(name="供应商名称")
    private String goodsFrom;//供货商
    @Excel(name="入仓编号")
    private String enterCode;//入库编号,不唯一
    @Excel(name="入库品名")
    private String enterProductName;//入库品名
    @Excel(name="入库料号")
    private String materialCode;//材料ID

    @Excel(name="规格")
    private String goodsSpec;//规格
    @Excel(name="生产日期")
    private String produceDate;//生产日期,因为没有使用空间，先使用字符串表示
    @Excel(name="出库品名")
    private String outProductName;//出库品名
    @Excel(name="出库料号")
    //出库料号
    private String outMaterialCode;
    // 数量
    @Excel(name="数量")
    private int goodsSize;
    //数量单位PCS
    @Excel(name="数量单位")
    private String goodsUnit="PCS";
    //实际出库日期
    @Excel(name="实际出库日期")
    private String outDate;//对应出库的applyDate

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getGoodsFrom() {
        return goodsFrom;
    }

    public void setGoodsFrom(String goodsFrom) {
        this.goodsFrom = goodsFrom;
    }

    public String getEnterCode() {
        return enterCode;
    }

    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    public String getEnterProductName() {
        return enterProductName;
    }

    public void setEnterProductName(String enterProductName) {
        this.enterProductName = enterProductName;
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

    public String getOutProductName() {
        return outProductName;
    }

    public void setOutProductName(String outProductName) {
        this.outProductName = outProductName;
    }

    public String getOutMaterialCode() {
        return outMaterialCode;
    }

    public void setOutMaterialCode(String outMaterialCode) {
        this.outMaterialCode = outMaterialCode;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public int getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(int goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}

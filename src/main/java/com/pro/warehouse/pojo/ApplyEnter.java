package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ApplyEnter {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int enterId;//id
    @Excel(name = "入库编号")
    private String enterCode;//入库编号,不唯一
    @Excel(name = "料号")
    private String materialCode;//材料ID
    @Excel(name = "规格")
    private String spec;//规格
    @Excel(name = "数量")
    private int number;//数量
    @Excel(name = "单位")
    private String unit;//数量单位
    private double unitPrice;//单价
    private String coinType;//币制，和数量单位类似
    private double sumMoney;//金额
    @Excel(name = "发票号")
    private String billNumber;//发票号码
    @Excel(name = "净重")
    private double suttleWeight;//净重（KGS）
    @Excel(name = "毛重")
    private double roughWeight;//毛重
    @Excel(name = "品名")
    private String productName;//品名
    private String originCountry;//原产国
    private String dataCode;
    private String lotno;
    private String pono;
    private String poLoneno;
    private String position;//仓位
    private String treasury;//库别
    @Excel(name = "供应商")
    private String goodsFrom;//供货商
    @Excel(name="件数")
    private int suitNumber;//件数
    private String suitUnit;//件数单位
    private String status;//申请状态
    private String applyPersonId;//申请人ID,这里作为姓名传入吧，暂用这个名字不然修改很麻烦，
    private String ensurePersonId;//确认人ID，这里也一样
    private String remark;//备注
    @Excel(name = "生产日期")
    private String produceDate;//生产日期,因为没有使用空间，先使用字符串表示
    private Date applyDate;//申请日期

    public int getEnterId() {
        return enterId;
    }

    public void setEnterId(int enterId) {
        this.enterId = enterId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
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

    public String getSuitUnit() {
        return suitUnit;
    }

    public void setSuitUnit(String suitUnit) {
        this.suitUnit = suitUnit;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTreasury() {
        return treasury;
    }

    public void setTreasury(String treasury) {
        this.treasury = treasury;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }


    public void setSuttleWeight(int suttleWeight) {
        this.suttleWeight = suttleWeight;
    }


    public void setRoughWeight(int roughWeight) {
        this.roughWeight = roughWeight;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getLotno() {
        return lotno;
    }

    public void setLotno(String lotno) {
        this.lotno = lotno;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getPoLoneno() {
        return poLoneno;
    }

    public void setPoLoneno(String poLoneno) {
        this.poLoneno = poLoneno;
    }

    public int getSuitNumber() {
        return suitNumber;
    }

    public void setSuitNumber(int suitNumber) {
        this.suitNumber = suitNumber;
    }


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyPersonId() {
        return applyPersonId;
    }

    public void setApplyPersonId(String applyPersonId) {
        this.applyPersonId = applyPersonId;
    }

    public String getEnsurePersonId() {
        return ensurePersonId;
    }

    public void setEnsurePersonId(String ensurePersonId) {
        this.ensurePersonId = ensurePersonId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public String toString() {
        return "ApplyEnter{" +
                "enterId=" + enterId +
                ", sumMoney=" + sumMoney +
                ", originCountry='" + originCountry + '\'' +
                ", suitNumber=" + suitNumber +
                ", status='" + status + '\'' +
                '}';
    }
}

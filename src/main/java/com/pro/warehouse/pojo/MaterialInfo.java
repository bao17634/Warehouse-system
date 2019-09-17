package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 物料信息
 */
@Entity
public class MaterialInfo {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;//编号
    private String materialCode;//物料代码（可手输入，唯一约束）
    private String supplyCode;//供应商代码
    private String spec;//规格
    private String itemCodel;//项号
    private String productName;//品名
    private String productId;//商品编码
    private int unit;//数量单位
    private int minPackage;//最小包装

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

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getItemCodel() {
        return itemCodel;
    }

    public void setItemCodel(String itemCodel) {
        this.itemCodel = itemCodel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getMinPackage() {
        return minPackage;
    }

    public void setMinPackage(int minPackage) {
        this.minPackage = minPackage;
    }
}

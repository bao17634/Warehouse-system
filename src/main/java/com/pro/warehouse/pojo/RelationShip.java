package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class RelationShip {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;//作为编号
    private String supplyCode;//供货商代码
    private String supplyName;//供货商名称
    private String demandCode;//提货商代码
    private String demandName;//提货商名称
    private String groupMark;//群组标记
    private String relationId;//唯一ID，可手输

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getGroupMark() {
        return groupMark;
    }

    public void setGroupMark(String groupMark) {
        this.groupMark = groupMark;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    @Override
    public String toString() {
        return "RelationShip{" +
                "id=" + id +
                ", supplyCode='" + supplyCode + '\'' +
                ", supplyName='" + supplyName + '\'' +
                ", demandCode='" + demandCode + '\'' +
                ", demandName='" + demandName + '\'' +
                ", groupMark='" + groupMark + '\'' +
                ", relationId='" + relationId + '\'' +
                '}';
    }
}

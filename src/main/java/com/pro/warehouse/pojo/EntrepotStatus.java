package com.pro.warehouse.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 仓库库存情况表
 * 
 * @author dail
 *
 */
@Entity
public class EntrepotStatus {
	@Id
	//设置主键并且设置主键为自增
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	//入库编号
	private String enterCode;
	// 料号
	private String materialCode;
	// 供应商
	private String supplyName;
	// 发票号码
	private String taxCode;
    //货物状态情况：待验、良品、不良品
	private String goodsStatus;
	// 规格
	private String materialSpec;
	// 品名
	private String productName;
	// 仓位
	private  String  position;
	// 仓位库存
	private int totalSize;
	// 库别
	private String entrepotType;
	//生产日期
	private String produceDate;
	// 入库日期
	private Date entranceDate;
	// 变更日期
	private Date updateDate;

	public String getEnterCode() {
		return enterCode;
	}

	public void setEnterCode(String enterCode) {
		this.enterCode = enterCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public String getEntrepotType() {
		return entrepotType;
	}

	public void setEntrepotType(String entrepotType) {
		this.entrepotType = entrepotType;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	@Override
	public String toString() {
		return "EntrepotStatus{" +
				"enterCode='" + enterCode + '\'' +
				", taxCode='" + taxCode + '\'' +
				", entranceDate=" + entranceDate +
				", updateDate=" + updateDate +
				'}';
	}
}

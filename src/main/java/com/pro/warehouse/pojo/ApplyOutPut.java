package com.pro.warehouse.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 出库申请表
 * 
 * @author dail
 *
 */
@Entity
public class ApplyOutPut {
	@Id
	//设置主键并且设置主键为自增
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	//出库编号
	@Excel(name="出仓编号")
	private String outCode;
	//入库编号
	@Excel(name = "入仓编号")
	private String enterCode;
	// 入库料号
	@Excel(name = "入库料号")
	private String materialCode;
	//出库料号
	@Excel(name = "出库料号")
	private String outMaterialCode;
	// 规格
	@Excel(name = "规格")
	private String spec;
	// 数量
	@Excel(name = "数量")
	private int size;
	// 净重
	@Excel(name = "净重")
	private double suttleWeight;
	// 毛重
	@Excel(name = "毛重")
	private double roughWeight;
	// 单价
	@Excel(name = "单价")
	private double price;
	// 金额
	private double amout;
	// 提货商
	@Excel(name = "提货单位")
	private String demandName;
	// 申请人id,这里也使用用户名做标识，明明不改了，不然太麻烦
	private String applyPersonid;
	// 打包人
	private String packagePersonid;
	// 确认人
	private String ensurePersonid;
	//生产日期
	@Excel(name = "生产日期")
	private String produceDate;
	// 日期
	private Date applyDate;
	// 备注
	private String remark;
	//申请状态
	private String status;

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public int getSize() {
		return size;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public void setSize(int size) {
		this.size = size;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

	public String getDemandName() {
		return demandName;
	}

	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}

	public String getApplyPersonid() {
		return applyPersonid;
	}

	public void setApplyPersonid(String applyPersonid) {
		this.applyPersonid = applyPersonid;
	}

	public String getPackagePersonid() {
		return packagePersonid;
	}

	public void setPackagePersonid(String packagePersonid) {
		this.packagePersonid = packagePersonid;
	}

	public String getEnsurePersonid() {
		return ensurePersonid;
	}

	public void setEnsurePersonid(String ensurePersonid) {
		this.ensurePersonid = ensurePersonid;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnterCode() {
		return enterCode;
	}

	public void setEnterCode(String enterCode) {
		this.enterCode = enterCode;
	}

	public String getOutMaterialCode() {
		return outMaterialCode;
	}

	public void setOutMaterialCode(String outMaterialCode) {
		this.outMaterialCode = outMaterialCode;
	}

	@Override
	public String toString() {
		return "ApplyOutPut{" +
				"id=" + id +
				", materialCode='" + materialCode + '\'' +
				", spec='" + spec + '\'' +
				", size=" + size +

				", amout=" + amout +
				", demandName='" + demandName + '\'' +
				'}';
	}
}

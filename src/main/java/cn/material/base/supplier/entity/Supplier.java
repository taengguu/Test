package cn.material.base.supplier.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;

@Table(name="base_supplier")
@Entity
public class Supplier extends BaseEntity{

	

	private static final long serialVersionUID = 8250766781843084771L;
	/**
	 * 供应商编号
	 */
	@Column
	private String serialNo;
	/**
	 * 供应商名称
	 */
	@Column
	private String supplierName;
	/**
	 * 供应商地址
	 */
	@Column
	private String supplierAddress;
	/**
	 * 供应商联系电话
	 */
	@Column
	private String supplierTel;
	/**
	 * 供应商邮箱地址
	 */
	@Column
	private String supplierEmail;
	/**
	 * 供应商基本信息
	 */
	@Column
	private String supplierInfo;
	
	/**
	 * 备注
	 */
	@Column
	private String remark;
	/**
	 * 伪删标记   
	 */
	@Column
	private int delFlag=Constants.DIC_NO;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierTel() {
		return supplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public String getSupplierInfo() {
		return supplierInfo;
	}
	public void setSupplierInfo(String supplierInfo) {
		this.supplierInfo = supplierInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	

}

package cn.material.base.purchase.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.material.base.material.entity.Material;
import cn.material.common.base.BaseEntity;
import cn.material.common.utils.CreateOrderNoUtils;

@Table(name="purchase_order")
@Entity
public class PurchaseOrder extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7536675674167680564L;
	/**
	 * 订单编号(系统时间+六位随机生成数)
	 */
	@Column
	private String serialNo = CreateOrderNoUtils.getCreateOrderNo();  // 默认生成订单
	
	/**
	 * 订单材料
	 */
	@ManyToOne
	@JoinColumn(name="material_id")
//	@JoinTable(name="base_purchase_material",joinColumns={@JoinColumn(name="purchaseId",referencedColumnName="id")},
//	inverseJoinColumns={@JoinColumn(name="materialId",referencedColumnName="id")})
	private Material material;
	/**
	 * 采购量
	 */
	@Column
	private Integer orderAmount;
	/**
	 * 采购状态
	 */
	@Column
	private String orderStatus;
	/**
	 * 申请时间
	 */
	@Column
	private Date applicationDate;
	
	/**
	 * 审核时间
	 */
	@Column
	private Date auditDate;
	/**
	 * 申请人
	 */
	@Column
	private String applicant;
	
	/**
	 * 审核人
	 */
	@Column
	private String auditor;
	
	/**
	 * 订单价格
	 */
	@Column
	private double orderTotalPrice;
	
	/**
	 * 备注
	 */
	@Column
	private String remark;
	
	/**
	 * 删除标记
	 */
	@Column
	private int delFlag;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
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

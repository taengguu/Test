package cn.material.base.purchase.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.material.common.base.BaseEntity;
import cn.material.common.constants.Constants;

@Table(name="return_order")
@Entity
public class ReturnOrder extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8119757126953814505L;
	/**
	 * 已进货订单
	 */
	@OneToOne
	@JoinColumn(name="purchaseOrder_id")
	private PurchaseOrder purchaseOrder;
	/**
	 * 退货状态
	 */
	@Column
	private String returnStatus;
	/**
	 * 负责人
	 */
	@Column
	private String principal;
	/**
	 * 退货时间
	 */
	@Column
	private Date returnTime;
	/**
	 * 退货原因
	 */
	@Column
	private String returnReason;
	/**
	 * 伪删标记
	 */
	@Column
	private int delFlag = Constants.DIC_NO;
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
	
	
	
	
	
	

}

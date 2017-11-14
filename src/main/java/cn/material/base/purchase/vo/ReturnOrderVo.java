package cn.material.base.purchase.vo;

import java.util.Date;

import javax.persistence.Column;

import cn.material.base.purchase.entity.PurchaseOrder;

public class ReturnOrderVo {
	
	private String id;
	
	private String purchaseOrderId;
	
	private String purchaseOrderSerialNo;

	private String returnStatus;

	private String principal;
	
	private Date returnTime;

	private String returnReason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderSerialNo() {
		return purchaseOrderSerialNo;
	}

	public void setPurchaseOrderSerialNo(String purchaseOrderSerialNo) {
		this.purchaseOrderSerialNo = purchaseOrderSerialNo;
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
	
	
}

package cn.material.base.sale.vo;

import java.util.Date;

public class SaleVo {
	private String id;
	private String serialNo;
	private String materialId;
	private String materialName;
	private String memberId;
	
	private String memberName;
	private int amount;
	private float totalPrice;
	private String minTotalPrice;
	private String maxTotalPrice;
	
	private Date orderTime;
	private String saleStatus;
	private String payType;
	private Date payTime;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getMinTotalPrice() {
		return minTotalPrice;
	}
	public void setMinTotalPrice(String minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}
	public String getMaxTotalPrice() {
		return maxTotalPrice;
	}
	public void setMaxTotalPrice(String maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}
	
	

}
